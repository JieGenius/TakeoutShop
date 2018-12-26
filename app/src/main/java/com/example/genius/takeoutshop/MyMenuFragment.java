package com.example.genius.takeoutshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MyMenuFragment extends Fragment {
    private List<MyMenu.MenuBean> myList;
    private MyMenuRecyclerViewAdapter adapter;
    private Button button;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MyMenuFragment() {
        myList = new ArrayList<>();
        adapter = new MyMenuRecyclerViewAdapter(myList);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mymenu_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.my_menu_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(container.getContext(),DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(container.getContext(),R.drawable.home_fragment_divider_line));
        recyclerView.addItemDecoration(dividerItemDecoration);
        button = view.findViewById(R.id.my_menu_bt_add_menu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AddMenuActivity.class);
                intent.putExtra("id",MainActivity.getId());
                startActivity(intent);
            }
        });
        return view;
    }
    public void updateData(List<MyMenu.MenuBean> list){
        if(list!=null){
            if(myList==null){
                myList = new ArrayList<>();
            }
            myList.clear();
            myList.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

}
