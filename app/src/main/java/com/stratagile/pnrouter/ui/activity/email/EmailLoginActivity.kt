package com.stratagile.pnrouter.ui.activity.email

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import com.pawegio.kandroid.toast
import com.smailnet.eamil.Callback.GetConnectCallback
import com.smailnet.eamil.EmailExamine
import com.smailnet.islands.Islands
import com.stratagile.pnrouter.R
import com.stratagile.pnrouter.application.AppConfig
import com.stratagile.pnrouter.base.BaseActivity
import com.stratagile.pnrouter.db.*
import com.stratagile.pnrouter.entity.events.AddEmailConfig
import com.stratagile.pnrouter.ui.activity.email.component.DaggerEmailLoginComponent
import com.stratagile.pnrouter.ui.activity.email.contract.EmailLoginContract
import com.stratagile.pnrouter.ui.activity.email.module.EmailLoginModule
import com.stratagile.pnrouter.ui.activity.email.presenter.EmailLoginPresenter
import com.xiaomi.push.it
import kotlinx.android.synthetic.main.email_login_activity.*
import kotlinx.android.synthetic.main.emailname_bar.*
import kotlinx.android.synthetic.main.emailpassword_bar.*
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

/**
 * @author zl
 * @Package com.stratagile.pnrouter.ui.activity.email
 * @Description: $description
 * @date 2019/07/02 15:20:41
 */

class EmailLoginActivity : BaseActivity(), EmailLoginContract.View {

    @Inject
    internal lateinit var mPresenter: EmailLoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
       setContentView(R.layout.email_login_activity)
    }
    override fun initData() {
        title.text = getString(R.string.NewAccount)
        login.setOnClickListener {
            Islands
                    .circularProgress(this)
                    .setMessage("登录中...")
                    .setCancelable(false)
                    .show()
                    .run { progressDialog -> login(progressDialog) }
        }
    }

    override fun setupActivityComponent() {
       DaggerEmailLoginComponent
               .builder()
               .appComponent((application as AppConfig).applicationComponent)
               .emailLoginModule(EmailLoginModule(this))
               .build()
               .inject(this)
    }
    override fun setPresenter(presenter: EmailLoginContract.EmailLoginContractPresenter) {
            mPresenter = presenter as EmailLoginPresenter
        }

    override fun showProgressDialog() {
        progressDialog.show()
    }

    override fun closeProgressDialog() {
        progressDialog.hide()
    }

    /**
     * 登录邮箱
     *
     * @param progressDialog
     */
    private fun login(progressDialog: ProgressDialog) {
        //配置发件服务器
        if(account_editText.getText().toString().equals(""))
        {
            toast(R.string.Account)
            return;
        }
        if(password_editText.getText().toString().equals("")  )
        {
            toast(R.string.Password)
            return;
        }
        AppConfig.instance.emailConfig()
                .setSmtpHost(send_host_editText.getText().toString())
                .setSmtpPort(Integer.parseInt(send_port_editText.getText().toString()))
                .setPopHost(receive_host_editText.getText().toString())
                .setPopPort(Integer.parseInt(receive_port_editText.getText().toString()))
                .setImapHost(imap_host_editText.getText().toString())
                .setImapPort(Integer.parseInt(imap_port_editText.getText().toString()))
                .setAccount(account_editText.getText().toString())
                .setPassword(password_editText.getText().toString())

        val emailExamine = EmailExamine(AppConfig.instance.emailConfig())
        emailExamine.connectServer(this, object : GetConnectCallback {
            override fun loginSuccess() {
                progressDialog.dismiss()
                var account =  account_editText.getText().toString()
                var emailConfigEntityList = AppConfig.instance.mDaoMaster!!.newSession().emailConfigEntityDao.queryBuilder().where(EmailConfigEntityDao.Properties.Account.eq(account)).list()
                var hasVerify = false
                if(emailConfigEntityList.size > 0)
                {
                    var localemailConfigEntityList = AppConfig.instance.mDaoMaster!!.newSession().emailConfigEntityDao.loadAll()
                    for (j in localemailConfigEntityList) {
                       j.choose = false
                        AppConfig.instance.mDaoMaster!!.newSession().emailConfigEntityDao.update(j)
                    }
                    var emailConfigEntity: EmailConfigEntity = emailConfigEntityList.get(0);
                    emailConfigEntity.account = account
                    emailConfigEntity.password = password_editText.getText().toString()
                    emailConfigEntity.smtpHost = send_host_editText.getText().toString()
                    emailConfigEntity.smtpPort = Integer.parseInt(send_port_editText.getText().toString())
                    emailConfigEntity.popHost = receive_host_editText.getText().toString()
                    emailConfigEntity.popPort = Integer.parseInt(receive_port_editText.getText().toString())
                    emailConfigEntity.imapHost = imap_host_editText.getText().toString()
                    emailConfigEntity.imapPort = Integer.parseInt(imap_port_editText.getText().toString())
                    emailConfigEntity.choose = true
                    AppConfig.instance.mDaoMaster!!.newSession().emailConfigEntityDao.update(emailConfigEntity)
                }else{
                    var emailConfigEntity: EmailConfigEntity = EmailConfigEntity()
                    emailConfigEntity.account = account
                    emailConfigEntity.password = password_editText.getText().toString()
                    emailConfigEntity.smtpHost = send_host_editText.getText().toString()
                    emailConfigEntity.smtpPort = Integer.parseInt(send_port_editText.getText().toString())
                    emailConfigEntity.popHost = receive_host_editText.getText().toString()
                    emailConfigEntity.popPort = Integer.parseInt(receive_port_editText.getText().toString())
                    emailConfigEntity.imapHost = imap_host_editText.getText().toString()
                    emailConfigEntity.imapPort = Integer.parseInt(imap_port_editText.getText().toString())
                    emailConfigEntity.choose = true
                    AppConfig.instance.mDaoMaster!!.newSession().emailConfigEntityDao.insert(emailConfigEntity)
                }
                EventBus.getDefault().post(AddEmailConfig())
                startActivity(Intent(this@EmailLoginActivity, EmailMainActivity::class.java))
                finish()
            }

            override fun loginFailure(errorMsg: String) {
                progressDialog.dismiss()
                Islands.ordinaryDialog(this@EmailLoginActivity)
                        .setText(null, "登录失败 ：$errorMsg")
                        .setButton("关闭", null, null)
                        .click()
                        .show()
            }
        })
    }
}