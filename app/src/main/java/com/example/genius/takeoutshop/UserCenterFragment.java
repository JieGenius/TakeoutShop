package com.example.genius.takeoutshop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
public class UserCenterFragment extends Fragment {

    @BindView(R.id.shop_user_center_name)
    TextView shopUserCenterName;
    @BindView(R.id.shop_user_center_address)
    TextView shopUserCenterAddress;
    @BindView(R.id.shop_user_center_phone)
    TextView shopUserCenterPhone;
    @BindView(R.id.shop_user_center_time)
    TextView shopUserCenterTime;
    @BindView(R.id.shop_user_center_sales)
    TextView shopUserCenterSales;
    @BindView(R.id.shop_user_center_grade)
    TextView shopUserCenterGrade;
    Unbinder unbinder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_center, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    public void setParams(String name,String phone,String addresss,String grade,String time,String sales){
        if(shopUserCenterName!=null){
            shopUserCenterName.setText(name);
            shopUserCenterPhone.setText(phone);
            shopUserCenterAddress.setText(addresss);
            shopUserCenterGrade.setText(grade);
            shopUserCenterTime.setText(time);
            shopUserCenterSales.setText(sales);
        }

    }
}
