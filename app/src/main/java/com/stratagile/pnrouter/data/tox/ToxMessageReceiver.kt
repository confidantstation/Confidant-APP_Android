package com.stratagile.pnrouter.data.tox

import android.util.Log
import chat.tox.antox.tox.MessageHelper
import chat.tox.antox.wrapper.FriendKey
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.stratagile.pnrouter.application.AppConfig
import com.stratagile.pnrouter.constant.ConstantValue
import com.stratagile.pnrouter.data.web.WebSocketConnection
import com.stratagile.pnrouter.entity.BaseData
import com.stratagile.pnrouter.entity.JHeartBeatRsp
import com.stratagile.pnrouter.utils.GsonUtil
import com.stratagile.pnrouter.utils.LogUtil
import com.stratagile.pnrouter.utils.baseDataToJson
import events.ToxMessageEvent
import im.tox.tox4j.core.enums.ToxMessageType
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class ToxMessageReceiver(){


    var segmentContent = ""
    init {
        EventBus.getDefault().register(this)
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onToxConnected(toxMessageEvent: ToxMessageEvent) {
        var text = toxMessageEvent.message
        if(text!!.indexOf("HeartBeat") < 0)
        {
            Log.w(ToxMessageReceiver.TAG, "onMessage(text)! " + text!!)
        }
        LogUtil.addLog("接收信息：${text}")
        try {
            val gson = GsonUtil.getIntGson()
            var baseData = gson.fromJson(text, BaseData::class.java)
            val newTextaa = gson.toJson(baseData)
            if(baseData.more == null)
            {
                if (JSONObject.parseObject((JSONObject.parseObject(text)).get("params").toString()).getString("Action").equals("HeartBeat")) {
                    val heartBeatRsp  = gson.fromJson(text, JHeartBeatRsp::class.java)
                    if (heartBeatRsp.params.retCode == 0) {
                        //KLog.i("心跳监测和服务器的连接正常~~~")
                    }
                } else {
                    AppConfig.instance.onToxMessageReceiveListener!!.onMessage(baseData, text)
                }
            }else{

                if(baseData.more == 1)
                {
                    segmentContent +=baseData.params.toString()
                    baseData.params = ""
                    baseData.offset =  baseData.offset!! + 1100
                    var aa = 110
                    var baseDataJson = baseData.baseDataToJson().replace("\\", "")
                    var friendKey: FriendKey = FriendKey(ConstantValue.currentRouterId.substring(0, 64))
                    MessageHelper.sendMessageFromKotlin(AppConfig.instance, friendKey, baseDataJson, ToxMessageType.NORMAL)
                }else{
                    segmentContent +=baseData.params;
                    if(segmentContent.equals(""))
                    {
                        AppConfig.instance.onToxMessageReceiveListener!!.onMessage(baseData, text)
                    }
                    else{
                        var test =  JSON.toJSONString(baseData)
                        baseData.params = segmentContent
                        baseData.params = segmentContent.replace("\\", "")
                        val newText = gson.toJson(baseData)
                        var newText2 = newText.replace("\\\"", "\"").replace("\"params\":\"","\"params\":").replace("\",\"timestamp\"",",\"timestamp\"")
                        segmentContent = ""
                        AppConfig.instance.onToxMessageReceiveListener!!.onMessage(baseData, newText2)
                    }

                }


            }

        } catch (e : Exception) {
            e.printStackTrace()
        }
    }
    companion object {

        private val TAG = ToxMessageReceiver::class.java.simpleName
        private val KEEPALIVE_TIMEOUT_SECONDS = 30
    }
    interface OnMessageReceiveListener {
        fun onMessage(message : BaseData, text: String?)
    }
}