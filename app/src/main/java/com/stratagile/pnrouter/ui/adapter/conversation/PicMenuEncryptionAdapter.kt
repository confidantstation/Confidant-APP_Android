package com.stratagile.pnrouter.ui.adapter.conversation

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mcxtzhang.swipemenulib.SwipeMenuLayout
import com.pawegio.kandroid.loadAnimation
import com.socks.library.KLog
import com.stratagile.pnrouter.R
import com.stratagile.pnrouter.constant.ConstantValue
import com.stratagile.pnrouter.db.LocalFileMenu
import com.stratagile.pnrouter.entity.JPullFileListRsp
import com.stratagile.pnrouter.entity.PicMenu
import com.stratagile.pnrouter.entity.ShareBean
import com.stratagile.pnrouter.utils.*
import com.stratagile.pnrouter.view.DrawableEnTextView
import com.stratagile.pnrouter.view.ImageButtonWithText
import com.stratagile.pnrouter.view.SmoothCheckBox
import java.util.*

class PicMenuEncryptionAdapter(arrayList: MutableList<LocalFileMenu>) : BaseQuickAdapter<LocalFileMenu, BaseViewHolder>(R.layout.pic_encryption_adapter, arrayList) {
    override fun convert(helper: BaseViewHolder?, item: LocalFileMenu?, payloads: MutableList<Any>) {
        KLog.i("")
    }

    var isChooseMode = false
    override fun convert(helper: BaseViewHolder, item: LocalFileMenu) {
        helper.addOnClickListener(R.id.menuItem)
        helper.addOnClickListener(R.id.btnDelete)
        helper.addOnClickListener(R.id.btnRename)
        var menuRoot = helper.getView<SwipeMenuLayout>(R.id.menuRoot)
        if(item.id == 1L)
        {
            menuRoot.isSwipeEnable = false;
        }else{
            menuRoot.isSwipeEnable = true;
        }
        var userAvatar = helper.getView<DrawableEnTextView>(R.id.menuItem)
        /*if(item.type.equals("0"))
        {
            userAvatar.setTitleText(item.fileName)
        }else{
            var souceName = String(Base58.decode(item.fileName))
            userAvatar.setTitleText(souceName)
        }*/
        userAvatar.setTitleText(item.fileName)
        userAvatar.setRightTitleText(item.fileNum.toString() +" "+mContext.getString(R.string.file_))
    }

}