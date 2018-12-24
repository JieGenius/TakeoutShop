package com.example.genius.takeoutshop;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private UserCenterFragment userCenterFragment;
    private MyMenuFragment myMenuFragment;
    private MyOrderFragment myOrderFragment;
    private String phone;
    private String id ;
    private MyShopInfo myShopInfo;
    private MyMenu myMenu;
    private AllOrder allOrder;
    private Handler handler;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    jumpFragment(myMenuFragment);
                    handler.sendEmptyMessage(2);
                    return true;
                case R.id.navigation_dashboard:
                    jumpFragment(myOrderFragment);
                    handler.sendEmptyMessage(3);
                    return true;
                case R.id.navigation_notifications:
                    jumpFragment(userCenterFragment);
                    handler.sendEmptyMessage(1);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        phone = getIntent().getStringExtra("phone");
        id = getIntent().getStringExtra("id");
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
               switch (msg.what){
                   case 1:
                       if(myShopInfo!=null){
                           userCenterFragment.setParams(myShopInfo.getName(),myShopInfo.getPhonenum(),myShopInfo.getAddress(),
                                   myShopInfo.getGrade(),myShopInfo.getTime(),myShopInfo.getSales());
                       }
                       break;
                   case 2:
                       if(myMenu!=null){
                           myMenuFragment.updateData(myMenu.getMenu());
                       }
                       break;

                   case 3:
                       if(allOrder!=null){
                           myOrderFragment.updateData(allOrder.getAllOrder());
                       }
                       break;
               }
               return true;
            }
        });
        userCenterFragment = new UserCenterFragment();
        myMenuFragment = new MyMenuFragment();
        myOrderFragment=  new MyOrderFragment();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //navigation.setSelectedItemId(R.id.navigation_notifications);
        jumpFragment(myMenuFragment);
        createChildThread();
    }
    private void jumpFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }
    private void createChildThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                getMyShopInfo();
                getMyMenu();
                getAllOrder();
            }
        }).start();
    }
    private void getMyShopInfo(){
        String url = Services.getMyShopInfo;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .callTimeout(1,TimeUnit.MINUTES)
                .connectTimeout(1,TimeUnit.MINUTES)
                .readTimeout(1,TimeUnit.MINUTES)
                .writeTimeout(1,TimeUnit.MINUTES)
                .build();
        FormBody formBody = new FormBody.Builder().add("phone",phone).build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                myShopInfo = new Gson().fromJson(result,MyShopInfo.class);
                handler.sendEmptyMessage(1);
            }
        });
    }
    private void getMyMenu(){
        String url = Services.getMyMenu;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .callTimeout(1,TimeUnit.MINUTES)
                .connectTimeout(1,TimeUnit.MINUTES)
                .readTimeout(1,TimeUnit.MINUTES)
                .writeTimeout(1,TimeUnit.MINUTES)
                .build();
        FormBody formBody = new FormBody.Builder().add("id",id).build();
        Log.e(TAG, "getMyMenu: id"+id );
        Request request = new Request.Builder().url(url).post(formBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.e(TAG, "onResponse: "+result );
                myMenu = new Gson().fromJson(result,MyMenu.class);
                handler.sendEmptyMessage(2);
            }
        });
    }
    private void getAllOrder(){
        String url = Services.getMyOrder;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .callTimeout(1,TimeUnit.MINUTES)
                .connectTimeout(1,TimeUnit.MINUTES)
                .readTimeout(1,TimeUnit.MINUTES)
                .writeTimeout(1,TimeUnit.MINUTES)
                .build();
        FormBody formBody = new FormBody.Builder().add("id",id).build();
        Log.e(TAG, "getMyMenu: id"+id);
        Request request = new Request.Builder().url(url).post(formBody).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.e(TAG, "onResponse: "+result );
                allOrder = new Gson().fromJson(result,AllOrder.class);
                handler.sendEmptyMessage(3);
            }
        });
    }

}
