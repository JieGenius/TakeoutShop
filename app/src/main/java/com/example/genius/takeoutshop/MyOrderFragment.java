package com.example.genius.takeoutshop;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyOrderFragment extends Fragment {
    private List<AllOrder.AllOrderBean> myList;
    private MyOrderFragmentAdapter myOrderFragmentAdapter;
    public MyOrderFragment() {
        // Required empty public constructor
        myList = new ArrayList<>();
        myOrderFragmentAdapter = new MyOrderFragmentAdapter(myList);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_order, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.my_order_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myOrderFragmentAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(container.getContext(),DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(container.getContext(),R.drawable.home_fragment_divider_line));
        recyclerView.addItemDecoration(dividerItemDecoration);
        return view;
    }

    public void updateData(List<AllOrder.AllOrderBean> list){
        if(list!=null){
            if(myList==null){
                myList = new ArrayList<>();
            }
            myList.clear();
            myList.addAll(list);
            myOrderFragmentAdapter.notifyDataSetChanged();
        }
    }

}
