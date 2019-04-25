package com.example.portmappingchat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import tools.internet.Request;

public class RegisterActivity extends AppCompatActivity {
    private EditText editText1, editText2;
    private Button button1;
    private ProgressDialog progressDialog;
    private String userName,passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }


    private void init() {
        initProgressDialog();
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = String.valueOf(editText1.getText());
                passWord = String.valueOf(editText2.getText());
                new Thread(new RequestThread(userName, passWord)).start();
                progressDialog.show();
            }
        });
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setIndeterminate(false);  //循环滚动
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("注册中...");
        progressDialog.setCancelable(false);    //不能取消显示
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String resultStr = data.getString("resultStr");
            Log.i("ServerResultStr: ",resultStr);
            progressDialog.dismiss();
            if (resultStr != null && resultStr.equals("Success")) {
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                intent.putExtra("userName",userName);
                intent.putExtra("passWord",passWord);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(RegisterActivity.this, "注册出错...", Toast.LENGTH_LONG).show();
            }
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
                Request request = new Request("index/Register", handler);
                Map<String, String> params = new HashMap<String, String>();
                params.put("userName", userName);
                params.put("passWord", passWord);
                try {
                    request.get(params);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(RegisterActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
