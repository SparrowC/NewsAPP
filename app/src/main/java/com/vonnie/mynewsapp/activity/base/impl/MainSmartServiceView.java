package com.vonnie.mynewsapp.activity.base.impl;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.vonnie.mynewsapp.R;
import com.vonnie.mynewsapp.activity.base.BaseMainContent;
import com.vonnie.mynewsapp.beans.ChatInfo;
import com.vonnie.mynewsapp.global.Config;
import com.vonnie.mynewsapp.utils.DateUtils;
import com.vonnie.mynewsapp.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vonnie on 2016/2/26.
 */
public class MainSmartServiceView extends BaseMainContent {
    private ListView lv_chatList;
    private EditText et_chatInput;
    private Button btn_chatSend;
    private List<ChatDetails> mChatDetailsList;
    private ChatListAdapter mChatAdapter;
    final private int LOAD_FAILED = 3;
    final private int LOAD_FINISH = 4;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case LOAD_FINISH:
                    Bundle bundle = msg.getData();
                    mChatDetailsList.add(new ChatDetails(bundle.getString("answer"), false));
                    mChatAdapter.notifyDataSetChanged();
                    lv_chatList.setSelection(mChatAdapter.getCount() - 1);
                    break;
                case LOAD_FAILED:
                    Toast.makeText(mActivity, "请检查网络后再试", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    public MainSmartServiceView() {
        super();
    }

    @Override
    protected void initView() {
        super.initView();
        setTitle("智慧服务");
        View chatContent = View.inflate(mActivity, R.layout.main_smart_service_layout, null);
        lv_chatList = (ListView) chatContent.findViewById(R.id.lv_chatList);
        et_chatInput = (EditText) chatContent.findViewById(R.id.et_chatInput);
        btn_chatSend = (Button) chatContent.findViewById(R.id.btn_chatSend);

        fl_content.addView(chatContent);

    }

    @Override
    protected void initData() {
        setTitle("智慧服务");
        mChatDetailsList = new ArrayList<>();
        mChatDetailsList.add(new ChatDetails("你好，很高兴为你提供服务！", false));
        mChatAdapter = new ChatListAdapter();

        lv_chatList.setAdapter(mChatAdapter);

        btn_chatSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = et_chatInput.getText().toString();
                if (!text.equals("")) {
                    mChatDetailsList.add(new ChatDetails(text, true));
                    getDataFromServer(text);
                    et_chatInput.setText("");
                    mChatAdapter.notifyDataSetChanged();
                    lv_chatList.setSelection(mChatAdapter.getCount() - 1);
                }
            }
        });

        et_chatInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                et_chatInput.setFocusable(true);
//                et_chatInput.setFocusableInTouchMode(true);
//                et_chatInput.requestFocus();
                InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(et_chatInput, InputMethodManager.SHOW_FORCED);
            }
        });
    }

    private void getDataFromServer(final String text) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Looper.prepare();
                String result = NetUtils.LoadJsonDataFromServer(Config.getChatRobortUrl(text, DateUtils.getCurTimestamp()));
                String answer = getAnswer(result);
                Message msg = new Message();
                if (answer != null) {
                    msg.what = LOAD_FINISH;
                    Bundle bundle = new Bundle();
                    bundle.putString("answer", answer);
                    msg.setData(bundle);
                } else {
                    msg.what = LOAD_FAILED;
                }
                mHandler.sendMessage(msg);
            }
        }).start();
    }

    private String getAnswer(String result) {
        if (result != null && (!result.equals(""))) {
            ChatInfo info = ChatInfo.objectFromData(result);
            if (info != null) {
                ChatInfo.ShowapiResBodyEntity bodyEntity = info.getShowapi_res_body();
                if (bodyEntity != null)
                    return bodyEntity.getText();
            }
        }
        return null;
    }

    private class ChatListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mChatDetailsList.size();
        }

        @Override
        public Object getItem(int position) {
            return mChatDetailsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv;
//            if(convertView==null)
//            {
            if (mChatDetailsList.get(position).isRight) {
                convertView = View.inflate(mActivity, R.layout.chat_list_right_item_layout, null);
                tv = (TextView) convertView.findViewById(R.id.tv_rightChat);
            } else {
                convertView = View.inflate(mActivity, R.layout.chat_list_left_item_layout, null);
                tv = (TextView) convertView.findViewById(R.id.tv_leftChat);
            }
            convertView.setTag(tv);
//            }else {
//                tv= (TextView) convertView.getTag();
//            }

            tv.setText(mChatDetailsList.get(position).text);
            return convertView;
        }
    }

    private class ChatDetails {
        public String text;
        public boolean isRight;

        public ChatDetails(String text, boolean isRight) {
            this.text = text;
            this.isRight = isRight;
        }
    }
}
