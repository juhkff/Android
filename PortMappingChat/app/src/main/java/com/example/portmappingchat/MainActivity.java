package com.example.portmappingchat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import tools.internet.Request;

public class MainActivity extends AppCompatActivity {
    private Button button1, button2;
    private EditText editText1, editText2;
    private TextView textView1, textView2, textView3;
    private ProgressDialog progressDialog;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    private void initProgressDialog() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setIndeterminate(false);  //循环滚动
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("登录中...");
        progressDialog.setCancelable(false);    //不能取消显示
    }

    private void init() {
        initProgressDialog();
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = String.valueOf(editText1.getText());
                String passWord = String.valueOf(editText2.getText());
                new Thread(new RequestThread(userName, passWord)).start();
                progressDialog.show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            String userName = intent.getStringExtra("userName");
            String passWord = intent.getStringExtra("passWord");
            if (userName != null && passWord != null && userName.length() > 0 && passWord.length() > 0) {
                editText1.setText(userName);
                editText2.setText(passWord);
            }
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String resultStr = data.getString("resultStr");
            System.out.println(resultStr);
            String toastStr = null;
            progressDialog.dismiss();
            if (resultStr != null) {
                switch (resultStr) {
                    case "none":
                        toastStr = "用户名不存在";
                        break;
                    case "Success":
                        toastStr = "登录成功!";
                        break;
                    case "Failed":
                        toastStr = "密码错误";
                        break;
                    default:
                        toastStr = "未知错误...";
                        break;
                }
            }
            Toast.makeText(MainActivity.this, toastStr, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, ShowFriendActivity.class);
            intent.putExtra("userName", userName);
            startActivity(intent);
            finish();
        }
    };

    private class RequestThread implements Runnable {
        private String userName, passWord;

        public RequestThread(String userName, String passWord) {
            this.userName = userName;
            this.passWord = passWord;
        }

        @Override
        public void run() {
            if (userName != null && userName.length() > 0 && passWord != null && passWord.length() > 0) {
                Request request = new Request("index/Login", handler);
                Map<String, String> params = new HashMap<String, String>();
                params.put("userName", userName);
                params.put("passWord", passWord);
                try {
                    request.post(params);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(MainActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
