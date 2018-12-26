package com.example.genius.takeoutshop;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AddMenuActivity extends AppCompatActivity {
    private static final String TAG = "AddMenuActivity";
    @BindView(R.id.add_menu_name)
    EditText addMenuName;
    @BindView(R.id.add_menu_intro)
    EditText addMenuIntro;
    @BindView(R.id.add_menu_price)
    EditText addMenuPrice;
    @BindView(R.id.add_menu_discount)
    EditText addMenuDiscount;
    @BindView(R.id.add_menu_enter)
    Button addMenuEnter;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id",0);
    }

    @OnClick(R.id.add_menu_enter)
    public void onViewClicked() {
        String menuName = addMenuName.getText().toString();
        String menuIntro = addMenuIntro.getText().toString();
        String menuPrice = addMenuPrice.getText().toString();
        String menuDiscount = addMenuDiscount.getText().toString();
        String sql = "insert into 菜单(M_name,M_introduce,M_price,M_discount,C_num) values ("
                +add2quote(menuName)+add2quote(menuIntro)+add2quote(menuPrice)+add2quote(menuDiscount)+id+")";
        Log.e(TAG, "onViewClicked: "+sql );
        update(AddMenuActivity.this,sql);
        Toast.makeText(AddMenuActivity.this,"受影响行数1",Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(AddMenuActivity.this)
                .setTitle("添加成功").setMessage("已成功添加").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        builder.show();
    }
    private static String add2quote(String temp){
        return "'"+temp+"',";
    }
    private void update(Context context,String sql){
        String url = Services.updateInfo;
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        requestBody.addFormDataPart("sql", sql);
        final Request request = new Request.Builder().url(url).post(requestBody.build()).tag(context).build();
        client.newBuilder().readTimeout(10, TimeUnit.MINUTES).connectTimeout(10, TimeUnit.MINUTES).writeTimeout(10, TimeUnit.MINUTES)
                .build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: "+response.body().string() );
            }
        });
    }
}
