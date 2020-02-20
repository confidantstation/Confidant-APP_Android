package com.stratagile.pnrouter.ui.activity.main

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.google.gson.Gson
import com.pawegio.kandroid.toast
import com.stratagile.pnrouter.R
import com.stratagile.pnrouter.application.AppConfig
import com.stratagile.pnrouter.base.BaseActivity
import com.stratagile.pnrouter.constant.ConstantValue
import com.stratagile.pnrouter.db.QLCAccountDao
import com.stratagile.pnrouter.entity.BaseData
import com.stratagile.pnrouter.entity.LogOutReq
import com.stratagile.pnrouter.entity.PublishBlock
import com.stratagile.pnrouter.ui.activity.main.component.DaggerEncryptMsgComponent
import com.stratagile.pnrouter.ui.activity.main.contract.EncryptMsgContract
import com.stratagile.pnrouter.ui.activity.main.module.EncryptMsgModule
import com.stratagile.pnrouter.ui.activity.main.presenter.EncryptMsgPresenter
import com.stratagile.pnrouter.utils.FileMangerDownloadUtils
import com.stratagile.pnrouter.utils.LibsodiumUtil
import com.stratagile.pnrouter.utils.RxEncodeTool
import com.stratagile.pnrouter.utils.SpUtil
import com.stratagile.pnrouter.view.SweetAlertDialog
import com.stratagile.tox.toxcore.KotlinToxService
import com.stratagile.tox.toxcore.ToxCoreJni
import kotlinx.android.synthetic.main.emailname_bar.*
import kotlinx.android.synthetic.main.encrypt_reg_activity.*
import kotlinx.android.synthetic.main.encrypt_source.*

import javax.inject.Inject;
import qlc.rpc.impl.*
import qlc.network.QlcClient
import qlc.utils.Helper


/**
 * @author zl
 * @Package com.stratagile.pnrouter.ui.activity.main
 * @Description: $description
 * @date 2020/02/19 16:13:12
 */

class EncryptMsgActivity : BaseActivity(), EncryptMsgContract.View {

    @Inject
    internal lateinit var mPresenter: EncryptMsgPresenter
    var verifiers= arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        setContentView(R.layout.encrypt_reg_activity)
    }
    override fun initData() {

        title.text = "Encrypt And Decrypt"
        regeditBtn.setOnClickListener {
            var emaiLAccount = account_editText.text.toString()
            if(emaiLAccount =="")
            {
                toast(R.string.Account_cannot_be_empty)
                return@setOnClickListener
            }
            val qlcClient = QlcClient(ConstantValue.qlcNode)
            val rpc = DpkiRpc(qlcClient)
            verifiers= arrayListOf<String>()
            Thread(Runnable() {
                run() {
                    var getAllVerifiersResult =  rpc.getAllVerifiers(null)
                    if(getAllVerifiersResult != null)
                    {
                        var firstResult = getAllVerifiersResult.get("result") as JSONArray
                        for (i in 0..(firstResult.size - 1)){
                            if(i>4)
                            {
                                break;
                            }
                            var obj:JSONObject = firstResult.get(i) as JSONObject
                            var obj_account = obj.getString("account")
                            var obj_type = obj.getString("type")
                            var obj_id = obj.getString("id")
                            verifiers.add(obj_account)
                        }
                        var qlcAccountEntityList = AppConfig.instance.mDaoMaster!!.newSession().qlcAccountDao.queryBuilder().where(QLCAccountDao.Properties.IsCurrent.eq(true)).list()
                        if(qlcAccountEntityList != null && qlcAccountEntityList.size > 0)
                        {
                            var qlcAccount = qlcAccountEntityList.get(0)
                            val publicBlock = JSONArray()
                            var stateBlock = PublishBlock()
                            stateBlock.account = qlcAccount.address  //"id" -> "test2.dpk@qlc.com"
                            stateBlock.type ="email"
                            stateBlock.id = emaiLAccount
                            stateBlock.fee = "500000000"
                            var pubkey = Helper.byteToHexString(RxEncodeTool.base64Decode(ConstantValue.libsodiumpublicMiKey)).toLowerCase();
                            pubkey = pubkey.substring(0,64)
                            stateBlock.pubkey = pubkey;
                            stateBlock.verifiers = verifiers;
                            publicBlock.add(JSONObject.parseObject(Gson().toJson(stateBlock)))
                            val publicBlock2 = JSONArray()
                            publicBlock2.add(publicBlock)
                            publicBlock2.add(qlcAccount.privKey)
                            try {
                                var getPublishBlockResult =  rpc.getPublishBlockAndProcess(publicBlock2)
                                var aa =""
                            }catch (e:Exception)
                            {
                                e.printStackTrace()
                                return@Runnable
                            }

                            //var getPublishBlockResult =  rpc.getPublishBlock(publicBlock)
                            /* var firstResult = getPublishBlockResult.get("result")  as JSONObject
                             var verifiers = firstResult.get("verifiers")
                             var block = firstResult.get("block")*/

                            runOnUiThread {
                                showDialog("seed:",qlcAccount.seed)
                            }
                            /* val verifiersBlock = JSONArray()
                             verifiersBlock.add("email")
                             verifiersBlock.add(emaiLAccount)
                             var getPubKeyByTypeAndIDResult =  rpc.getPubKeyByTypeAndID(verifiersBlock)


                             val verifiersBlock22 = JSONArray()
                             verifiersBlock22.add(qlcAccount.address)
                             verifiersBlock22.add("email")
                             var getPubKeyByTypeAndIDResult2 =  rpc.getPublishInfosByAccountAndType(verifiersBlock22)
                             var bb = ""*/
                        }

                    }
                }
            }).start()


        }
        decryptBtn.setOnClickListener {
            var emaiLAccount = account_editText.text.toString()
            if(emaiLAccount =="")
            {
                toast(R.string.Account_cannot_be_empty)
                return@setOnClickListener
            }
            var password_editText = password_editText.text.toString()
            if(password_editText =="")
            {
                toast(R.string.Please_enter_content)
                return@setOnClickListener
            }
            var fixedKey = ""
            var libsodiumpublicTemKey = ""
            var encryptType = ""
            var random_nonce = ""
            var qlcchainAdress = ""
            var tokenCount = ""
            var tokenType = ""
            var miTxt = ""
            try {
                fixedKey = password_editText.substring(0,8)
                libsodiumpublicTemKey = password_editText.substring(8,52)
                encryptType = password_editText.substring(52,54)
                random_nonce = password_editText.substring(54,86)
                qlcchainAdress = ""
                tokenCount = ""
                tokenType = ""
                miTxt = ""
                if(encryptType == "00")
                {
                    miTxt = password_editText.substring(86,password_editText.length)
                }else{
                    qlcchainAdress = password_editText.substring(86,150)
                    tokenCount = password_editText.substring(150,160)
                    tokenType = password_editText.substring(160,162)
                    miTxt = password_editText.substring(162,password_editText.length)
                }
                var random = RxEncodeTool.base64Encode2String(random_nonce.toByteArray())
                var libsodiumpublicTemKeyByte = RxEncodeTool.base64Decode(libsodiumpublicTemKey)
                var aaaa = LibsodiumUtil.decrypt_data_symmetric_string(miTxt,random,libsodiumpublicTemKey)
                var msgMap = LibsodiumUtil.DecryptFriendMsg2(miTxt,random,ConstantValue.libsodiumprivateMiKey!!,libsodiumpublicTemKey)
                var aa =""
            }catch (e:Exception)
            {
                toast(R.string.Decryption_failed)
                return@setOnClickListener
            }
        }
        encryptBtn.setOnClickListener {
            var emaiLAccount = account_editText.text.toString()
            if(emaiLAccount =="")
            {
                toast(R.string.Account_cannot_be_empty)
                return@setOnClickListener
            }
            var password_editText = password_editText.text.toString()
            if(password_editText =="")
            {
                toast(R.string.Please_enter_content)
                return@setOnClickListener
            }

            val qlcClient = QlcClient(ConstantValue.qlcNode)
            val rpc = DpkiRpc(qlcClient)
            Thread(Runnable() {
                run() {
                    val publicBlock = JSONArray()
                    publicBlock.add("email")
                    publicBlock.add(emaiLAccount)
                    var getPubKeyByTypeAndIDResult =  rpc.getPubKeyByTypeAndID(publicBlock)
                    var pubKey = ""
                    if(getPubKeyByTypeAndIDResult != null)
                    {
                        var firstResult = getPubKeyByTypeAndIDResult.get("result")  as JSONArray
                        if(firstResult.size >0)
                        {
                            for (i in 0..(firstResult.size - 1)) {
                                var obj:JSONObject = firstResult.get(i) as JSONObject
                                var obj_type = obj.getString("type")
                                var obj_pubKey = obj.getString("pubKey")
                                if(obj_type =="email")
                                {
                                    pubKey = obj_pubKey;
                                    break
                                }
                            }
                            val random = org.libsodium.jni.crypto.Random()

                            var friendMiPublic = Helper.hexStringToBytes(pubKey)
                            var msgMap = LibsodiumUtil.EncryptSendMsg(password_editText,friendMiPublic,ConstantValue.libsodiumprivateSignKey!!,ConstantValue.libsodiumprivateTemKey!!,ConstantValue.libsodiumpublicTemKey!!,ConstantValue.libsodiumpublicMiKey!!)
                            var minTxt = msgMap.get("encryptedBase64") as String
                            var NonceBase64 =  msgMap.get("NonceBase64") as String
                            var msgSouce = LibsodiumUtil.DecryptMyMsg(minTxt, NonceBase64, msgMap.get("dst_shared_key_Mi_My64") as String, ConstantValue.libsodiumpublicMiKey!!, ConstantValue.libsodiumprivateMiKey!!)
                            var miStrBegin ="UUxDSUQ="+ConstantValue.libsodiumpublicTemKey+"00"+NonceBase64+minTxt
                            runOnUiThread {
                                showDialog("Ciphertext:",miStrBegin)
                            }
                        }else{
                            runOnUiThread {
                                toast(R.string.Decryption_failed)
                            }
                        }

                    }else{
                        runOnUiThread {
                            toast(R.string.Decryption_failed)
                        }

                    }
                }
            }).start()
        }
    }
    fun showDialog(content:String,copyContent:String) {
        SweetAlertDialog(this, SweetAlertDialog.BUTTON_NEUTRAL)
                .setContentText(content+copyContent)
                .setConfirmText(getString(R.string.copy))
                .setConfirmClickListener {
                    val cm = AppConfig.instance.applicationContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    var mClipData = ClipData.newPlainText(null, copyContent)
                    // 将ClipData内容放到系统剪贴板里。
                    cm.primaryClip = mClipData
                    toast(R.string.copy_success)
                }
                .show()

    }
    override fun setupActivityComponent() {
        DaggerEncryptMsgComponent
                .builder()
                .appComponent((application as AppConfig).applicationComponent)
                .encryptMsgModule(EncryptMsgModule(this))
                .build()
                .inject(this)
    }
    override fun setPresenter(presenter: EncryptMsgContract.EncryptMsgContractPresenter) {
        mPresenter = presenter as EncryptMsgPresenter
    }

    override fun showProgressDialog() {
        progressDialog.show()
    }

    override fun closeProgressDialog() {
        progressDialog.hide()
    }

}