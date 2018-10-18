package com.example.oblong.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import org.parceler.Parcels;


/**
 * Created by Jupe Taek on 10/6/2017.
 */

public class OrderViewHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;

    Context context;
    ArrayList<Order> items=new ArrayList<>();

    TextView namaPemesan,jumlahOrder,deskripsi;


    public OrderViewHolder(Context context) {
        this.context=context;
        /*super(itemView);
        view = itemView;
        mcontext = itemView.getContext();
        itemView.setOnClickListener(this);*/

        /*namaPemesan = (TextView) view.findViewById(R.id.namaPemesan);
        jenisKaos = (TextView) view.findViewById(R.id.jenisKaos);
        jumlahOrder = (TextView) view.findViewById(R.id.jumlahOrder);
        deskripsi = (TextView) view.findViewById(R.id.deskripsi);*/
    }

    public void setDataBinding(ArrayList<Order> items){
        this.items=items;

        notifyDataSetChanged();
    }

    public void BindOrder(Order order){
        /*Picasso.with(mcontext)
                .load(makanan.getImageURL())
                .fit()
                .centerCrop()
                .into(imgView);*/
        namaPemesan.setText(order.getNamaPemesan());
        jumlahOrder.setText("Jumlah :"+String.valueOf(order.getJumlah()));
        deskripsi.setText(order.getDeskripsi());

    }


    @Override
    public void onClick(View v) {
       /* if(v == itemView) {
            final ArrayList<Order> order = new ArrayList<>();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            Query query=ref.child("order").orderByChild("isDone").equalTo(false);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        order.add(data.getValue(Order.class));
                    }

                    int itemPosition = getLayoutPosition();
                    //perlu diubah============================V=============
                    Intent intent = new Intent(mcontext, DetailActivity.class);
                    intent.putExtra("Position", itemPosition + "");
                    intent.putExtra("Tempat", Parcels.wrap(order));
                    mcontext.startActivity(intent);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        /*if(v == ratingBar){
            float rating = ratingBar.getRating();
            Toast.makeText(mcontext, "rating ditambah" + rating, Toast.LENGTH_LONG).show();
        }*/

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View row=inflater.inflate(R.layout.item_list,parent,false);
        OrderViewHolder.Item item=new OrderViewHolder.Item(row);
        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((OrderViewHolder.Item)holder).namaPemesan.setText(items.get(position).getNamaPemesan());
        ((OrderViewHolder.Item)holder).jumlahOrder.setText("Jumlah :"+String.valueOf(items.get(position).getJumlah()));
        ((OrderViewHolder.Item)holder).jenisKaos.setText("Jenis :"+String.valueOf(items.get(position).getJenis()));
        ((OrderViewHolder.Item)holder).deskripsi.setText(items.get(position).getDeskripsi());
        ((OrderViewHolder.Item)holder).layoutList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Order", Parcels.wrap(items.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Item extends RecyclerView.ViewHolder{
        TextView name, telp;
        RelativeLayout layoutList;
        TextView namaPemesan,jenisKaos,jumlahOrder,deskripsi;

        public Item(View itemView){
            super(itemView);
            namaPemesan = (TextView) itemView.findViewById(R.id.namaPemesan);
            jenisKaos = (TextView) itemView.findViewById(R.id.jenisKaos);
            jumlahOrder = (TextView) itemView.findViewById(R.id.jumlahOrder);
            deskripsi = (TextView) itemView.findViewById(R.id.deskripsi);
            layoutList=(RelativeLayout)itemView.findViewById(R.id.layoutList);

        }
    }
}