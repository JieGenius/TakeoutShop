package com.example.genius.takeoutshop;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;

public class MyOrderFragmentAdapter extends RecyclerView.Adapter<MyOrderFragmentAdapter.ViewHolder> {

    List<AllOrder.AllOrderBean> list;

    public MyOrderFragmentAdapter(List<AllOrder.AllOrderBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_order_fragment_layout_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.orderInfoNum.setText(list.get(i).getOrderNum());
        viewHolder.orderInfoItemTime.setText(list.get(i).getOrderTime());
        StringBuilder stringBuilder = new StringBuilder();
        for(int j=0;j<list.get(i).getAllMeal().size();j++){
            stringBuilder.append(list.get(i).getAllMeal().get(j).getName());
            stringBuilder.append("X");
            stringBuilder.append(list.get(i).getAllMeal().get(j).getCount());
            if(j!=list.get(i).getAllMeal().size()-1){
                stringBuilder.append("\n");
            }
        }
        viewHolder.orderInfoItemOrderItem.setText(stringBuilder.toString());
        viewHolder.orderInfoItemDeliveryFee.setText(list.get(i).getOrderDeliveryFee());
        viewHolder.orderInfoItemBoxFee.setText(list.get(i).getOrderBoxFee());
        viewHolder.orderInfoItemSumFee.setText(list.get(i).getOrderSum());
        viewHolder.orderInfoItemDeliveryPhone.setText(list.get(i).getDeliveryPhone());
        viewHolder.orderInfoItemUserPhone.setText(list.get(i).getUserPhone());
        viewHolder.orderInfoItemUserAddress.setText(list.get(i).getUserAddress());
        viewHolder.orderInfoItemComment.setText(list.get(i).getCommentContent());
        viewHolder.orderInfoItemGrade.setText(list.get(i).getCommentGrade());
        viewHolder.orderInfoItemCommentTime.setText(list.get(i).getCommentTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView orderInfoNum;
        TextView orderInfoItemTime;
        TextView orderInfoItemOrderItem;
        TextView orderInfoItemDeliveryFee;
        TextView orderInfoItemBoxFee;
        TextView orderInfoItemSumFee;
        TextView orderInfoItemDeliveryPhone;
        TextView orderInfoItemUserPhone;
        TextView orderInfoItemUserAddress;
        TextView orderInfoItemComment;
        TextView orderInfoItemGrade;
        TextView orderInfoItemCommentTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderInfoNum = itemView.findViewById(R.id.order_info_num);
            orderInfoItemTime = itemView.findViewById(R.id.order_info_item_time);
            orderInfoItemOrderItem = itemView.findViewById(R.id.order_info_item_order_item);
            orderInfoItemDeliveryFee = itemView.findViewById(R.id.order_info_item_delivery_fee);
            orderInfoItemBoxFee = itemView.findViewById(R.id.order_info_item_box_fee);
            orderInfoItemSumFee = itemView.findViewById(R.id.order_info_item_sum_fee);
            orderInfoItemDeliveryPhone = itemView.findViewById(R.id.order_info_item_delivery_phone);
            orderInfoItemUserPhone = itemView.findViewById(R.id.order_info_item_user_phone);
            orderInfoItemUserAddress = itemView.findViewById(R.id.order_info_item_user_address);
            orderInfoItemComment = itemView.findViewById(R.id.order_info_item_comment);
            orderInfoItemGrade = itemView.findViewById(R.id.order_info_item_grade);
            orderInfoItemCommentTime = itemView.findViewById(R.id.order_info_item_comment_time);
        }
    }
}
