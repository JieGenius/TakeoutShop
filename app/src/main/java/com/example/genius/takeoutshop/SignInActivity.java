package com.example.genius.takeoutshop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class SignInActivity extends AppCompatActivity {
    private static final String TAG = "SignInActivity";
    @BindView(R.id.sign_in_ed_tel)
    EditText signInEdTel;
    @BindView(R.id.sign_in_ed_name)
    EditText signInEdName;
    @BindView(R.id.sign_in_ed_address)
    EditText signInEdAddress;
    @BindView(R.id.sign_in_ed_time)
    EditText signInEdTime;
    @BindView(R.id.sign_in_bt_confirm)
    Button signInBtConfirm;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(SignInActivity.this);
            if(msg.what == 0){
                alertDialog.setTitle("Failed").setMessage("服务器未成功接受数据，请联系管理员")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(SignInActivity.this,LoginActivity.class));
                        finish();
                    }
                }).show();
            }
            else if(msg.what == 1){
                alertDialog.setTitle("Successful").setMessage("已成功发送请求，请耐心等待管理员审核")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(SignInActivity.this,LoginActivity.class));
                        finish();
                    }
                }).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
    }

    @OnClick(R.id.sign_in_bt_confirm)
    public void onViewClicked() {
        String tel = signInEdTel.getText().toString();
        String name = signInEdName.getText().toString();
        String address = signInEdAddress.getText().toString();
        String time = signInEdTime.getText().toString();
        if(tel.length()==11&&name.length()>1&&address.length()>2&&time.length()>5){
            signInBtConfirm.setEnabled(false);
            signIn(tel,name,address,time);
        }
    }
    public void signIn(final String tel, final String name, final String address, final String time){
        final String url=Services.signIn;
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(1,TimeUnit.MINUTES)
                        .writeTimeout(1,TimeUnit.MINUTES)
                        .readTimeout(1,TimeUnit.MINUTES).build();
                FormBody formBody = new  FormBody.Builder()
                        .add("tel",tel)
                        .add("name",name)
                        .add("address",address)
                        .add("time",time).build();
                Request request = new Request.Builder().url(url).post(formBody).build();
                String result=null;
                try {
                    result = client.newCall(request).execute().body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if(result!=null && result.equals("Successful")){
                        handler.sendEmptyMessage(1);
                    }
                    else{
                        handler.sendEmptyMessage(0);
                    }
                }
                Log.e(TAG, "run: "+result );

            }
        }).start();
    }
}
