package com.example.oblong.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Jupe Taek on 10/6/2017.
 */

public class ListKeuangan extends Fragment {

    private DatabaseReference mDatabase;
    KeuanganViewHolder OrderAdapter;
    ArrayList<Order> ArrayOrder = new ArrayList<>();
    private Query query;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private View view;
    RecyclerView recycler;
    TextView data_empty;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        query=mDatabase.child("order").orderByChild("isDone").equalTo(true);
        view = inflater.inflate(R.layout.reycler_keuangan, container, false);
        data_empty = (TextView) view.findViewById(R.id.data_empty);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recycler=(RecyclerView)view.findViewById(R.id.recyclerKeuangan);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        OrderAdapter =new KeuanganViewHolder(getActivity().getApplicationContext());
        recycler.setAdapter(OrderAdapter);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayOrder.clear();
                Log.d("COba","hahahahaha");
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    ArrayOrder.add(data.getValue(Order.class));
                }



                OrderAdapter.setDataBinding(ArrayOrder);
                OrderAdapter.notifyDataSetChanged();
                //ArrayTempatRental.clear();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }

//    private void setUpFirebaseDatabase() {
//        recycler = (RecyclerView) view.findViewById(R.id.recyclerKeuangan);
//
//        FirebaseRecyclerOptions<Order> options =
//                new FirebaseRecyclerOptions.Builder<Order>()
//                        .setQuery(query, Order.class)
//                        .build();
//        mFirebaseAdapter = new FirebaseRecyclerAdapter<Order, KeuanganViewHolder>(options) {
//
//            @Override
//            public KeuanganViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.item_list_keuangan, parent, false);
//
//                return new KeuanganViewHolder(view);
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull KeuanganViewHolder holder, int position, @NonNull Order model) {
//                holder.BindOrder(model);
//            }
//
//        };
//        recycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
//        recycler.setAdapter(mFirebaseAdapter);
//        mFirebaseAdapter.notifyDataSetChanged();
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //mFirebaseAdapter.cleanup();
    }
}
