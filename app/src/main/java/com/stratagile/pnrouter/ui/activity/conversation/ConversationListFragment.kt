package com.stratagile.pnrouter.ui.activity.conversation

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.message.Conversation
import com.message.MessageProvider
import com.noober.menu.FloatMenu
import com.pawegio.kandroid.runOnUiThread
import com.stratagile.pnrouter.R
import com.stratagile.pnrouter.application.AppConfig
import com.stratagile.pnrouter.base.BaseActivity
import com.stratagile.pnrouter.base.BaseFragment
import com.stratagile.pnrouter.data.web.PNRouterServiceMessageSender
import com.stratagile.pnrouter.ui.activity.conversation.component.DaggerConversationListComponent
import com.stratagile.pnrouter.ui.activity.conversation.contract.ConversationListContract
import com.stratagile.pnrouter.ui.activity.conversation.module.ConversationListModule
import com.stratagile.pnrouter.ui.activity.conversation.presenter.ConversationListPresenter
import com.stratagile.pnrouter.ui.activity.main.MainViewModel
import com.stratagile.pnrouter.ui.adapter.conversation.ConversationListAdapter
import kotlinx.android.synthetic.main.fragment_conversation_list.*
import javax.inject.Inject


/**
 * @author hzp
 * @Package com.stratagile.pnrouter.ui.activity.conversation
 * @Description: $description
 * @date 2018/09/10 17:25:57
 */

class ConversationListFragment : BaseFragment(), ConversationListContract.View, MessageProvider.ConversationChangeListener {
    override fun conversationChange(arrayList: ArrayList<Conversation>) {
        runOnUiThread {
            coversationListAdapter!!.notifyDataSetChanged()
        }
    }

    @Inject
    lateinit internal var mPresenter: ConversationListPresenter

    var  messageSender : PNRouterServiceMessageSender? = null

    var coversationListAdapter : ConversationListAdapter? = null

    lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_conversation_list, null)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        MessageProvider.getInstance().conversationChangeListener = this
        coversationListAdapter = ConversationListAdapter(MessageProvider.getInstance().conversationList)
        coversationListAdapter!!.setOnItemLongClickListener { adapter, view, position ->
            val floatMenu = FloatMenu(activity)
            floatMenu.items("菜单1", "菜单2", "菜单3")
            floatMenu.show((activity!! as BaseActivity).point,0,0)
            true
        }
        recyclerView.adapter = coversationListAdapter
        coversationListAdapter!!.setOnItemClickListener { adapter, view, position ->
            var intent = Intent(activity!!, ConversationActivity::class.java)
            intent.putExtra("user", coversationListAdapter!!.getItem(position)!!.userEntity)
            startActivity(intent)
        }
//        send.setOnClickListener {
//            if (messageSender == null) {
//                messageSender = AppConfig.instance.getPNRouterServiceMessageSender()
//            }
//            var login = LoginReq("Login", "F574DB6D9136090C75C8CD4132E70CA9938568A6308AD5B2AE00CA86C5E7CA3FF8345E8AE31F", "", 0)
//            var jsonStr = BaseData( login)
//            Log.i("MainActivity", jsonStr.baseDataToJson())
//            messageSender!!.send(jsonStr)
//        }
    }

    override fun setupFragmentComponent() {
        DaggerConversationListComponent
                .builder()
                .appComponent((activity!!.application as AppConfig).applicationComponent)
                .conversationListModule(ConversationListModule(this))
                .build()
                .inject(this)
    }

    override fun setPresenter(presenter: ConversationListContract.ConversationListContractPresenter) {
        mPresenter = presenter as ConversationListPresenter
    }

    override fun initDataFromLocal() {

    }

    override fun showProgressDialog() {
        progressDialog.show()
    }

    override fun closeProgressDialog() {
        progressDialog.hide()
    }
}