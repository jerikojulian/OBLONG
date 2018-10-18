package com.example.oblong.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;


/**
 * Created by Jupe Taek on 10/6/2017.
 */

public class KeuanganViewHolder extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener{

    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;

    View view;
    Context mcontext;
    ArrayList<Order> items=new ArrayList<>();

    TextView namaPemesan,jenisKaos,jumlahOrder,keuntungan;


    public KeuanganViewHolder(Context context) {
//        super(itemView);
//        view = itemView;
        mcontext = context;
//        itemView.setOnClickListener(this);
//
//        namaPemesan = (TextView) view.findViewById(R.id.namaPemesan);
//        jenisKaos = (TextView) view.findViewById(R.id.jenisKaos);
//        jumlahOrder = (TextView) view.findViewById(R.id.jumlahOrder);
//        keuntungan = (TextView) view.findViewById(R.id.keuntungan);
    }

    public void BindOrder(Order order){
        /*Picasso.with(mcontext)
                .load(makanan.getImageURL())
                .fit()
                .centerCrop()
                .into(imgView);*/
        namaPemesan.setText(order.getNamaPemesan());
        jenisKaos.setText("Jenis :"+order.getJenis());
        jumlahOrder.setText("Jumlah :"+String.valueOf(order.getJumlah()));
        keuntungan.setText("Keuntungan : Rp. "+String.valueOf(order.getKeuntungan()));

    }

    public void setDataBinding(ArrayList<Order> items){
        this.items=items;

        notifyDataSetChanged();
    }


    @Override
    public void onClick(View v) {
//        if(v == itemView) {
//            final ArrayList<Order> order = new ArrayList<>();
//            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//
//            Query query=ref.child("order").orderByChild("isDone").equalTo(true);
//            query.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    for (DataSnapshot data : dataSnapshot.getChildren()) {
//                        order.add(data.getValue(Order.class));
//                    }
//
//                    int itemPosition = getLayoutPosition();
//                    //perlu diubah============================V=============
//                    Intent intent = new Intent(mcontext, DetailKeuanganActivity.class);
//                    intent.putExtra("Position", itemPosition + "");
//                    intent.putExtra("Tempat", Parcels.wrap(order));
//                    mcontext.startActivity(intent);
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//        }
        /*if(v == ratingBar){
            float rating = ratingBar.getRating();
            Toast.makeText(mcontext, "rating ditambah" + rating, Toast.LENGTH_LONG).show();
        }*/
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mcontext);
        View row=inflater.inflate(R.layout.item_list_keuangan,parent,false);
        KeuanganViewHolder.Item item=new KeuanganViewHolder.Item(row);
        return item;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((KeuanganViewHolder.Item)holder).namaPemesan.setText(items.get(position).getNamaPemesan());
        ((KeuanganViewHolder.Item)holder).jumlahOrder.setText("Jumlah :"+String.valueOf(items.get(position).getJumlah()));
        ((KeuanganViewHolder.Item)holder).jenisKaos.setText("Jenis :"+String.valueOf(items.get(position).getJenis()));
        ((KeuanganViewHolder.Item)holder).keuntungan.setText(items.get(position).getDeskripsi());
        ((KeuanganViewHolder.Item)holder).layoutList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, DetailKeuanganActivity.class);
                intent.putExtra("Order", Parcels.wrap(items.get(position)));
                mcontext.startActivity(intent);
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
        TextView namaPemesan,jenisKaos,jumlahOrder,keuntungan;

        public Item(View itemView){
            super(itemView);
            namaPemesan = (TextView) itemView.findViewById(R.id.namaPemesan);
            jenisKaos = (TextView) itemView.findViewById(R.id.jenisKaos);
            jumlahOrder = (TextView) itemView.findViewById(R.id.jumlahOrder);
            keuntungan = (TextView) itemView.findViewById(R.id.keuntungan);
            layoutList=(RelativeLayout)itemView.findViewById(R.id.layoutList);

        }
    }
}