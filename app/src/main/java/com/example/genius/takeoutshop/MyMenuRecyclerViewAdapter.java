package com.example.genius.takeoutshop;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MyMenuRecyclerViewAdapter extends RecyclerView.Adapter<MyMenuRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "MyMyMenuRecyclerViewAda";
    private final List<MyMenu.MenuBean> mValues;


    public MyMenuRecyclerViewAdapter(List<MyMenu.MenuBean> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_mymenu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.myMenuName.setText(mValues.get(position).getName());
        holder.myMenuIntroduce.setText(mValues.get(position).getIntroduce());
        holder.myMenuPrice.setText(mValues.get(position).getPrice());
        holder.myMenuDiscount.setText((String)mValues.get(position).getDiscount());
        holder.myMenuSales.setText(mValues.get(position).getSales());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myMenuName;
        TextView myMenuIntroduce;
        TextView myMenuPrice;
        TextView myMenuDiscount;
        TextView myMenuSales;

        public ViewHolder(View view) {
            super(view);
            myMenuName=view.findViewById(R.id.my_menu_name);
            myMenuIntroduce=view.findViewById(R.id.my_menu_introduce);
            myMenuPrice=view.findViewById(R.id.my_menu_price);
            myMenuDiscount=view.findViewById(R.id.my_menu_discount);
            myMenuSales=view.findViewById(R.id.my_menu_sales);
            myMenuName.setOnClickListener(this);
            myMenuIntroduce.setOnClickListener(this);
            myMenuPrice.setOnClickListener(this);
            myMenuSales.setOnClickListener(this);
            myMenuDiscount.setOnClickListener(this);

        }
        @Override
        public void onClick(final View v) {
            final EditText editText;
            AlertDialog.Builder inputDialog;
            switch (v.getId()) {
                case R.id.my_menu_name:
                    editText = new EditText(v.getContext());
                    editText.setText(myMenuName.getText());
                    inputDialog = new AlertDialog.Builder(v.getContext());
                    inputDialog.setTitle("请输入你想要修改的内容").setView(editText);
                    inputDialog.setPositiveButton("确定修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String temp = editText.getText().toString();
                            String sql = "update 菜单 set M_name = '"+temp+"'where C_num = "+mValues.get(getLayoutPosition()).getC_num()+"and M_num = "+mValues.get(getLayoutPosition()).getNum();
                            mValues.get(getLayoutPosition()).setName(temp);
                            update(sql,v.getContext());
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                    break;
                case R.id.my_menu_introduce:
                    editText = new EditText(v.getContext());
                    editText.setText(myMenuIntroduce.getText());
                    inputDialog = new AlertDialog.Builder(v.getContext());
                    inputDialog.setTitle("请输入你想要修改的内容").setView(editText);
                    inputDialog.setPositiveButton("确定修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String temp = editText.getText().toString();
                            String sql = "update 菜单 set M_introduce = '"+temp+"'where C_num = "+mValues.get(getLayoutPosition()).getC_num()+"and M_num = "+mValues.get(getLayoutPosition()).getNum();
                            mValues.get(getLayoutPosition()).setName(temp);
                            update(sql,v.getContext());
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                    break;
                case R.id.my_menu_price:
                    editText = new EditText(v.getContext());
                    editText.setText(myMenuPrice.getText());
                    inputDialog = new AlertDialog.Builder(v.getContext());
                    inputDialog.setTitle("请输入你想要修改的内容").setView(editText);
                    inputDialog.setPositiveButton("确定修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String temp = editText.getText().toString();
                            String sql = "update 菜单 set M_price = '"+temp+"'where C_num = "+mValues.get(getLayoutPosition()).getC_num()+"and M_num = "+mValues.get(getLayoutPosition()).getNum();
                            mValues.get(getLayoutPosition()).setName(temp);
                            update(sql,v.getContext());
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                    break;
                case R.id.my_menu_discount:
                    editText = new EditText(v.getContext());
                    editText.setText(myMenuDiscount.getText());
                    inputDialog = new AlertDialog.Builder(v.getContext());
                    inputDialog.setTitle("请输入你想要修改的内容").setView(editText);
                    inputDialog.setPositiveButton("确定修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String temp = editText.getText().toString();
                            String sql = "update 菜单 set M_discount = '"+temp+"'where C_num = "+mValues.get(getLayoutPosition()).getC_num()+"and M_num = "+mValues.get(getLayoutPosition()).getNum();
                            mValues.get(getLayoutPosition()).setName(temp);
                            update(sql,v.getContext());
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                    break;
            }
        }
    }
    static void update(String sql, final Context context) {
        final android.os.Handler handler = new android.os.Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 2) {
                    Looper.prepare();
                    String temp = (String) msg.obj;
                    Toast.makeText(context, temp, Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        };
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
                // Log.e(TAG, "onResponse: "+response.body().string() );
                Message message = new Message();
                message.obj = response.body().string();
                message.what = 2;
                handler.handleMessage(message);
            }
        });
    }
}
