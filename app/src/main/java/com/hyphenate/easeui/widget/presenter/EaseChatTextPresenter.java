package com.hyphenate.easeui.widget.presenter;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.model.EaseDingMessageHelper;
import com.hyphenate.easeui.ui.EaseDingAckUserListActivity;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.utils.EaseSmileUtils;
import com.hyphenate.easeui.widget.chatrow.EaseChatRow;
import com.hyphenate.easeui.widget.chatrow.EaseChatRowText;
import com.hyphenate.exceptions.HyphenateException;
import com.noober.menu.FloatMenu;
import com.socks.library.KLog;
import com.stratagile.pnrouter.R;
import com.stratagile.pnrouter.application.AppConfig;
import com.stratagile.pnrouter.constant.ConstantValue;
import com.stratagile.pnrouter.constant.UserDataManger;
import com.stratagile.pnrouter.entity.BaseData;
import com.stratagile.pnrouter.entity.DelMsgReq;
import com.stratagile.pnrouter.entity.GroupDelMsgReq;
import com.stratagile.pnrouter.entity.events.ReplyMsgEvent;
import com.stratagile.pnrouter.ui.activity.selectfriend.selectFriendActivity;
import com.stratagile.pnrouter.utils.SpUtil;
import com.stratagile.pnrouter.utils.StringUitl;
import com.stratagile.tox.toxcore.ToxCoreJni;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


/**
 * Created by zhangsong on 17-10-12.
 */

public class EaseChatTextPresenter extends EaseChatRowPresenter {
    private static final String TAG = "EaseChatTextPresenter";
    private Context context;
    private View viewRoot;
    private String phone;

    public static final int REQUEST_CALL_PERMISSION = 10111; //拨号请求码
    @Override
    protected EaseChatRow onCreateChatRow(Context cxt, EMMessage message, int position, BaseAdapter adapter) {
        context = cxt;
        return new EaseChatRowText(cxt, message, position, adapter);
    }

    @Override
    public void onBubbleClick(EMMessage message) {
        super.onBubbleClick(message);
        String msg = ((EMTextMessageBody) message.getBody()).getMessage();
        if(StringUitl.isHomepage(msg))
        {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            if(!msg.contains("http://") && !msg.contains("https://"))
            {
                msg = "https://" + msg;
            }
            Uri url = Uri.parse(msg);
            intent.setData(url);
            getContext().startActivity(intent);
            return;
        }else if(StringUitl.isEmail(msg))
        {
            sendEmail2(getContext(),"","",msg);
            return;
        }else if(StringUitl.isPhoneNumber(msg))
        {
            phone = msg;
            AndPermission.with(AppConfig.instance)
                    .requestCode(101)
                    .permission(
                            Manifest.permission.CALL_PHONE
                    )
                    .callback(permission)
                    .start();
            return;
        }
        if (!EaseDingMessageHelper.get().isDingMessage(message)) {
            return;
        }

        // If this msg is a ding-type msg, click to show a list who has already read this message.
        Intent i = new Intent(getContext(), EaseDingAckUserListActivity.class);
        i.putExtra("msg", message);
        getContext().startActivity(i);
    }
    private PermissionListener permission = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {

            // 权限申请成功回调。
            if (requestCode == 101) {
                Intent intentPhone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone));
                getContext().startActivity(intentPhone);
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if (requestCode == 101) {
                KLog.i("权限申请失败");

            }
        }
    };
    @Override
    public void onBubbleLongClick(EMMessage message, View view) {
        super.onBubbleLongClick(message,view);
        /*if(!message.isAcked())
            return;*/
        String fromID = message.getFrom();
        viewRoot = view;
        String userId =   SpUtil.INSTANCE.getString(AppConfig.instance.getApplicationContext(), ConstantValue.INSTANCE.getUserId(), "");

        FloatMenu floatMenu = new  FloatMenu(AppConfig.instance.getApplicationContext(),view);
        if(fromID.equals(userId))
        {
            floatMenu.inflate(R.menu.popup_menu);
        }else{
            if(message.getChatType().equals( EMMessage.ChatType.GroupChat))
            {
                if(UserDataManger.currentGroupData.getGAdmin().equals(userId))//如果是群管理员
                {
                    floatMenu.inflate(R.menu.popup_text_menu);
                }else{
                    floatMenu.inflate(R.menu.friendpopup_menu);
                }
            }else {
                floatMenu.inflate(R.menu.friendpopup_menu);
            }

        }
        //floatMenu.items(AppConfig.instance.getResources().getString(R.string.withDraw), AppConfig.instance.getResources().getString(R.string.cancel));
        int[] loc1=new int[2];
        view.getLocationOnScreen(loc1);
        KLog.i(loc1[0]);
        KLog.i(loc1[1]);
        KLog.i(AppConfig.instance.getPoint().x + "");
        KLog.i(AppConfig.instance.getPoint().y + "");
        int offx = 0;
        floatMenu.show(new Point(loc1[0], loc1[1]),offx,65);
//        floatMenu.show(new Point(AppConfig.instance.getPoint().x, AppConfig.instance.getPoint().y),0,65);
        floatMenu.setOnItemClickListener(new FloatMenu.OnItemClickListener() {
            @Override
            public void onClick(View v, int position,String name) {
                switch (name)
                {
                    case "Copy":
                        ClipboardManager cm = (ClipboardManager) AppConfig.instance.getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                        // 创建普通字符型ClipData
                        ClipData mClipData = ClipData.newPlainText("Label", EaseSmileUtils.getSmiledTextInput(getContext(), EaseCommonUtils.getMessageDigest(message, getContext())));
                        // 将ClipData内容放到系统剪贴板里。
                        cm.setPrimaryClip(mClipData);
                        Toast.makeText(AppConfig.instance.getApplicationContext(), R.string.copy_success, Toast.LENGTH_SHORT).show();
                        break;
                    case "Forward":
                        Intent intent = new Intent(getContext(), selectFriendActivity.class);
                        intent.putExtra("fromId", message.getTo());
                        intent.putExtra("message",message);
                        getContext().startActivity(intent);
                        ((Activity) getContext()).overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
                        break;
                    case "Reply":
                        String  msgIdReply = message.getMsgId();
                        String msg = ((EMTextMessageBody) message.getBody()).getMessage();
                        String userIdTemp = message.getFrom();
                        EventBus.getDefault().post(new ReplyMsgEvent(msgIdReply,msg,userIdTemp));
                        break;
                    case "Withdraw":
                        if(message.getChatType().equals( EMMessage.ChatType.GroupChat))
                        {
                            int type = 0;
                            if(!fromID.equals(userId))
                            {
                                type = 1;
                            }
                            GroupDelMsgReq  msgData = new GroupDelMsgReq(type, userId, message.getTo(),Integer.valueOf(message.getMsgId()) ,"GroupDelMsg");
                            BaseData baseData = new BaseData(4,msgData);
                            if(ConstantValue.INSTANCE.isWebsocketConnected())
                            {
                                AppConfig.instance.getPNRouterServiceMessageSender().send(baseData);
                            }else if(ConstantValue.INSTANCE.isToxConnected())
                            {
                                   String baseDataJson = JSONObject.toJSON(baseData).toString().replace("\\", "");
                                if (ConstantValue.INSTANCE.isAntox()) {
                                   // FriendKey friendKey  = new FriendKey( ConstantValue.INSTANCE.getCurrentRouterId().substring(0, 64));
                                    //MessageHelper.sendMessageFromKotlin(AppConfig.instance, friendKey, baseDataJson, ToxMessageType.NORMAL);
                                }else{
                                    ToxCoreJni.getInstance().senToxMessage(baseDataJson, ConstantValue.INSTANCE.getCurrentRouterId().substring(0, 64));
                                }
                            }
                        }else{
                            DelMsgReq msgData = new DelMsgReq( message.getFrom(), message.getTo(),Integer.valueOf(message.getMsgId()) ,"DelMsg");
                            if(ConstantValue.INSTANCE.isWebsocketConnected())
                            {
                                AppConfig.instance.getPNRouterServiceMessageSender().send(new BaseData(msgData));
                            }else if(ConstantValue.INSTANCE.isToxConnected())
                            {
                                BaseData baseData = new BaseData(msgData);
                                String baseDataJson = JSONObject.toJSON(baseData).toString().replace("\\", "");
                                if (ConstantValue.INSTANCE.isAntox()) {
                                    //FriendKey friendKey  = new FriendKey( ConstantValue.INSTANCE.getCurrentRouterId().substring(0, 64));
                                    //MessageHelper.sendMessageFromKotlin(AppConfig.instance, friendKey, baseDataJson, ToxMessageType.NORMAL);
                                }else{
                                    ToxCoreJni.getInstance().senToxMessage(baseDataJson, ConstantValue.INSTANCE.getCurrentRouterId().substring(0, 64));
                                }
                            }
                        }


                        ConstantValue.INSTANCE.setDeleteMsgId(message.getMsgId());

                        break;
                    default:
                        break;
                }
            }
        });
    }
    /**
     * 判断是否有某项权限
     * @param string_permission 权限
     * @param request_code 请求码
     * @return
     */
    public boolean checkReadPermission(String string_permission,int request_code) {
        boolean flag = false;
        if (ContextCompat.checkSelfPermission(getContext(), string_permission) == PackageManager.PERMISSION_GRANTED) {//已有权限
            flag = true;
        } else {//申请权限
            //ActivityCompat.requestPermissions(getContext(), new String[]{string_permission}, request_code);
        }
        return flag;
    }
    /**
     * 邮件分享
     *
     * @param context 上下文
     * @param title   邮件主题
     * @param content 邮件内容
     * @param address 邮件地址
     */
    public void sendEmail(Context context, String title, String content, String address) {
        Uri uri = Uri.parse("mailto:" + address);
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, uri);
        // 设置对方邮件地址
        emailIntent.putExtra(Intent.EXTRA_EMAIL, address);
        // 设置标题内容
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, title);
        // 设置邮件文本内容
        emailIntent.putExtra(Intent.EXTRA_TEXT, content);
        context.startActivity(Intent.createChooser(emailIntent, "选择邮箱"));
    }
    /**
     * 邮件分享
     *
     * @param context 上下文
     * @param title   邮件主题
     * @param content 邮件内容
     * @param address 邮件地址
     */
    public void sendEmail2(Context context, String title, String content, String address) {
        // 调用系统发邮件
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // 设置文本格式
        emailIntent.setType("text/plain");
        // 设置对方邮件地址
        emailIntent.putExtra(Intent.EXTRA_EMAIL, address);
        // 设置标题内容
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, title);
        // 设置邮件文本内容
        emailIntent.putExtra(Intent.EXTRA_TEXT, content);
        context.startActivity(Intent.createChooser(emailIntent, "选择邮箱"));
    }
    @Override
    protected void handleReceiveMessage(EMMessage message) {
        if (!message.isAcked() && message.getChatType() == EMMessage.ChatType.Chat) {
            try {
                EMClient.getInstance().chatManager().ackMessageRead(message.getFrom(), message.getMsgId());
            } catch (HyphenateException e) {
                e.printStackTrace();
            }
            return;
        }

        // Send the group-ack cmd type msg if this msg is a ding-type msg.
        EaseDingMessageHelper.get().sendAckMessage(message);
    }
}
