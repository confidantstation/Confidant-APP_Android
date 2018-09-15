package com.stratagile.pnrouter.application

import android.support.multidex.MultiDexApplication
import com.hyphenate.chat.EMClient
import com.hyphenate.easeui.EaseUI
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMMessage
import com.hyphenate.easeui.EaseConstant
import com.hyphenate.easeui.utils.EaseCommonUtils
import com.socks.library.KLog
import com.stratagile.pnrouter.BuildConfig
import com.stratagile.pnrouter.data.service.MessageRetrievalService
import com.stratagile.pnrouter.data.web.*
import com.stratagile.pnrouter.db.DaoMaster
import com.stratagile.pnrouter.db.MySQLiteOpenHelper
import com.stratagile.pnrouter.entity.BaseData
import com.stratagile.pnrouter.entity.JPushMsgRsp
import com.stratagile.pnrouter.entity.PushMsgReq
import com.stratagile.pnrouter.utils.GlideCircleTransformMainColor

/**
 * 作者：Android on 2017/8/1
 * 邮箱：365941593@qq.com
 * 描述：
 */


class AppConfig : MultiDexApplication() {
    var applicationComponent: AppComponent? = null

    var messageReceiver: PNRouterServiceMessageReceiver? = null

    var messageSender: PNRouterServiceMessageSender? = null

    var mDaoMaster: DaoMaster? = null

    var isChatWithFirend:String? = null
    var options = RequestOptions()
            .centerCrop()
            .transform(GlideCircleTransformMainColor(this))
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .priority(Priority.HIGH)

    override fun onCreate() {
        super.onCreate()
        EaseUI.getInstance().init(this, null)
        EMClient.getInstance().setDebugMode(true);
        instance = this
        setupApplicationComponent()
        setDatabase()
    }

    fun getPNRouterServiceMessageReceiver() :  PNRouterServiceMessageReceiver{
        if (messageReceiver == null) {
            this.messageReceiver = PNRouterServiceMessageReceiver(SignalServiceNetworkAccess(this).getConfiguration(this),
                    APIModule.DynamicCredentialsProvider(this),
                    BuildConfig.USER_AGENT,
                    APIModule.PipeConnectivityListener())
        }
        MessageRetrievalService.registerActivityStarted(this)
        return messageReceiver!!
    }

    fun getPNRouterServiceMessageSender() :  PNRouterServiceMessageSender{
        if (messageSender == null) {
            messageSender = PNRouterServiceMessageSender(Optional.fromNullable(MessageRetrievalService.getPipe()), Optional.of(SecurityEventListener(this)))
        }
        return messageSender!!
    }

    protected fun setupApplicationComponent() {
        applicationComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .aPIModule(APIModule(this))
                .build()
        applicationComponent!!.inject(this)
    }

    companion object {
        lateinit var instance: AppConfig
    }

    @Synchronized
    fun getInstance(): AppConfig {
        if (null == instance) {
            instance = AppConfig()
        }
        return instance as AppConfig
    }

    /**
     * 设置greenDao
     */
    private fun setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        //        mHelper = new DaoMaster.DevOpenHelper(this, "qlink-db", null);
        val helper = MySQLiteOpenHelper(this, "qlink-db", null)
        mDaoMaster = DaoMaster(helper.getWritableDatabase())
        //        db = mHelper.getWritableDatabase();
        //        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        //        mDaoMaster = new DaoMaster(db);
    }
}
