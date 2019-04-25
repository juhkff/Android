package com.example.portmappingchat;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import tools.adapter.ChatAdapter;
import tools.adapter.ChatMessage;
import tools.internet.ReceiveThread;

public class ChatActivity extends AppCompatActivity {
    private String userName;
    private String anotherUserName;
    private String ip;
    private int port;
    private ListView listView;
    private ChatAdapter listAdapter;
    private List<ChatMessage> chatMessages;

    private EditText editText1;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        init();
    }

    @Override
    public void onBackPressed() {
        //ReceiveThread.removeChatHandler();
        super.onBackPressed();                                                                       /**?*/
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String message = data.getString("Message");
            chatMessages.add(new ChatMessage(false, message));
            listAdapter.notifyDataSetChanged(); //添加?
        }
    };

    private void init() {
        Intent intent = getIntent();
        if (intent != null) {
            this.userName = intent.getStringExtra("userName");
            this.anotherUserName = intent.getStringExtra("anotherUserName");
            String address = intent.getStringExtra("address");
            Log.i("获得另一者的address", address);
            this.ip = address.substring(1, address.length() - 1).split(":")[0];
            this.port = Integer.parseInt(address.split(":")[1]);
            Log.i("获得另一者的ip和port", ip + "\t" + port);
        }
        ((TextView) findViewById(R.id.textView1)).setText(anotherUserName); //输入联系人名称
        this.listView = findViewById(R.id.listView1);
        chatMessages = new ArrayList<ChatMessage>();
        this.listAdapter = new ChatAdapter(chatMessages, ChatActivity.this);
        ReceiveThread.setChatHandler(handler);
        editText1 = findViewById(R.id.editText1);
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(sendButtonClick);
    }

    private View.OnClickListener sendButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final String message = String.valueOf(button1.getText());
            if (message != null && message.length() > 0) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        byte[] bytes = ("Chat:" + userName + ":" + message).getBytes();
                        SocketAddress socketAddress = new InetSocketAddress(ip, port);
                        Log.i("给联系人发送信息时的socketAddress", socketAddress.toString());
                        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, socketAddress);
                        try {
                            ShowFriendActivity.getDatagramSocket().send(datagramPacket);
                            chatMessages.add(new ChatMessage(true, message));
                            listAdapter.notifyDataSetChanged();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                button1.setText("");
            } else {
                //没有信息发送
            }
        }
    };
}
