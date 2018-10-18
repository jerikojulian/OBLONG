package com.example.oblong.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;


public class DetailActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    int keuntungan;
    TextView namaPemesan, jumlahOrder, jenisOrder, deskripsiOrder,deadline,harga;
    EditText belanjaBahan, potong, sablonBordir, jahit, pengeluaranLain,editnamaPemesan,editjumlahOrder,editdeskripsiOrder,editHarga;
    Button save,edit,done,saveEditOrder;
    Order orderFix;
    Customer cust;
    private MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        orderFix = Parcels.unwrap(intent.getParcelableExtra("Order"));
        //orderFix = tempat.get(Integer.valueOf(intent.getStringExtra("Position")));

        //Adding toolbar to the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Detail Order");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp); // Set the icon

        // Icon click listener
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = 2;
                Intent intent = new Intent();
                intent.putExtra("pos", pos);
                setResult(RESULT_OK, intent);
                finish();
            }
        });


        namaPemesan=(TextView)findViewById(R.id.namaPemesan);
        jumlahOrder=(TextView)findViewById(R.id.jumlahOrder);
        jenisOrder=(TextView)findViewById(R.id.jenisOrder);
        deskripsiOrder=(TextView)findViewById(R.id.deskripsiOrder);
        deadline=(TextView)findViewById(R.id.alamat);
        harga=(TextView)findViewById(R.id.harga);

        editdeskripsiOrder=(EditText)findViewById(R.id.editdeskripsiOrder);
        editHarga=(EditText)findViewById(R.id.editharga);
        editjumlahOrder=(EditText)findViewById(R.id.editjumlahOrder);
        editnamaPemesan=(EditText)findViewById(R.id.editnamaPemesan);

        belanjaBahan=(EditText)findViewById(R.id.belanjaBahan);
        potong=(EditText)findViewById(R.id.potong);
        sablonBordir=(EditText)findViewById(R.id.sablonBordir);
        jahit=(EditText)findViewById(R.id.jahit);
        pengeluaranLain=(EditText)findViewById(R.id.pengeluaranLain);

        save=(Button)findViewById(R.id.save);
        edit=(Button)findViewById(R.id.edit);
        done=(Button)findViewById(R.id.done);
        saveEditOrder=(Button)findViewById(R.id.saveEditOrder);

        namaPemesan.setText(orderFix.getNamaPemesan());
        editnamaPemesan.setText(orderFix.getNamaPemesan());
        jumlahOrder.setText(String.valueOf(orderFix.getJumlah()));
        editjumlahOrder.setText(String.valueOf(orderFix.getJumlah()));
        jenisOrder.setText(orderFix.getJenis());
        deskripsiOrder.setText(orderFix.getDeskripsi());
        editdeskripsiOrder.setText(orderFix.getDeskripsi());
        belanjaBahan.setText(String.valueOf(orderFix.getBelanjaBahan()));
        potong.setText(String.valueOf(orderFix.getPotong()));
        sablonBordir.setText(String.valueOf(orderFix.getSalonBordir()));
        jahit.setText(String.valueOf(orderFix.getJahit()));
        pengeluaranLain.setText(String.valueOf(orderFix.getPengeluaranLain()));
        harga.setText("Rp. "+String.valueOf(orderFix.getHarga()));
        editHarga.setText(String.valueOf(orderFix.getHarga()));
        deadline.setText(orderFix.getDeadline());
    }

    public void Save(View view){
        if((belanjaBahan.getText().toString().length() > 0)&&(jahit.getText().toString().length() > 0)&&(pengeluaranLain.getText().toString().length() > 0)&&(potong.getText().toString().length() > 0)&&(sablonBordir.getText().toString().length() > 0)){
            save.setBackgroundResource(R.drawable.roundbclicked);
                mDatabase = FirebaseDatabase.getInstance().getReference();

            mDatabase.child("order").child(orderFix.getKey()).child("belanjaBahan").setValue(Integer.valueOf(belanjaBahan.getText().toString()));
            mDatabase.child("order").child(orderFix.getKey()).child("jahit").setValue(Integer.valueOf(jahit.getText().toString()));
            mDatabase.child("order").child(orderFix.getKey()).child("pengeluaranLain").setValue(Integer.valueOf(pengeluaranLain.getText().toString()));
            mDatabase.child("order").child(orderFix.getKey()).child("potong").setValue(Integer.valueOf(potong.getText().toString()));
            mDatabase.child("order").child(orderFix.getKey()).child("salonBordir").setValue(Integer.valueOf(sablonBordir.getText().toString()));

            if (((Integer.valueOf(belanjaBahan.getText().toString()))!=0)||((Integer.valueOf(jahit.getText().toString()))!=0)||((Integer.valueOf(pengeluaranLain.getText().toString()))!=0)||((Integer.valueOf(potong.getText().toString()))!=0)||((Integer.valueOf(sablonBordir.getText().toString()))!=0)){
                mDatabase.child("order").child(orderFix.getKey()).child("isInputBiaya").setValue(true);
            } else {
                mDatabase.child("order").child(orderFix.getKey()).child("isInputBiaya").setValue(false);
            }

            if (((Integer.valueOf(belanjaBahan.getText().toString()))!=0)&&((Integer.valueOf(jahit.getText().toString()))!=0)&&((Integer.valueOf(pengeluaranLain.getText().toString()))!=0)&&((Integer.valueOf(potong.getText().toString()))!=0)&&((Integer.valueOf(sablonBordir.getText().toString()))!=0)){
                done.setEnabled(true);
                done.setBackgroundResource(R.drawable.roundb);
            }


            save.setEnabled(false);
            save.setBackgroundResource(R.drawable.roundbdisabled);
            edit.setEnabled(true);
            edit.setBackgroundResource(R.drawable.roundb);
            belanjaBahan.setEnabled(false);
            potong.setEnabled(false);
            sablonBordir.setEnabled(false);
            jahit.setEnabled(false);
            pengeluaranLain.setEnabled(false);
        }else{
            Toast.makeText(this, "Biaya Produksi Belum Lengkap",
                    Toast.LENGTH_SHORT).show();
        }


    }

    public void Edit(View view){
        edit.setBackgroundResource(R.drawable.roundbclicked);
        save.setEnabled(true);
        save.setBackgroundResource(R.drawable.roundb);
        edit.setEnabled(false);
        edit.setBackgroundResource(R.drawable.roundbdisabled);
        belanjaBahan.setEnabled(true);
        potong.setEnabled(true);
        sablonBordir.setEnabled(true);
        jahit.setEnabled(true);
        pengeluaranLain.setEnabled(true);

    }

    public void Done(View view){
        done.setBackgroundResource(R.drawable.roundbclicked);
        mDatabase.child("order").child(orderFix.getKey()).child("isDone").setValue(true);
        keuntungan=(orderFix.getHarga()*orderFix.getJumlah())-((Integer.valueOf(belanjaBahan.getText().toString()))+(Integer.valueOf(jahit.getText().toString()))+(Integer.valueOf(pengeluaranLain.getText().toString()))+(Integer.valueOf(potong.getText().toString()))+(Integer.valueOf(sablonBordir.getText().toString())));
        mDatabase.child("order").child(orderFix.getKey()).child("keuntungan").setValue(keuntungan);
        Toast.makeText(DetailActivity.this, "Order Done",
                Toast.LENGTH_SHORT).show();
        Integer pos = 2;
        Intent intent = new Intent();
        intent.putExtra(MainActivity.STRING_RESULT_DATA_KEY, pos);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void editOrder(View view){
        editnamaPemesan.setVisibility(View.VISIBLE);
        editjumlahOrder.setVisibility(View.VISIBLE);
        editHarga.setVisibility(View.VISIBLE);
        editdeskripsiOrder.setVisibility(View.VISIBLE);
        saveEditOrder.setVisibility(View.VISIBLE);


    }

    public void saveEditOrder(View view){
        mDatabase = FirebaseDatabase.getInstance().getReference();


        if((editnamaPemesan.getText().toString().length() > 0)&&(editjumlahOrder.getText().toString().length() > 0)&&(editdeskripsiOrder.getText().toString().length() > 0)&&(editHarga.getText().toString().length() > 0)){
            mDatabase.child("order").child(orderFix.getKey()).child("namaPemesan").setValue(editnamaPemesan.getText().toString());
            mDatabase.child("order").child(orderFix.getKey()).child("jumlah").setValue(Integer.valueOf(editjumlahOrder.getText().toString()));
            mDatabase.child("order").child(orderFix.getKey()).child("deskripsi").setValue(editdeskripsiOrder.getText().toString());
            mDatabase.child("order").child(orderFix.getKey()).child("harga").setValue(Integer.valueOf(editHarga.getText().toString()));

            namaPemesan.setText(editnamaPemesan.getText().toString());
            jumlahOrder.setText(editjumlahOrder.getText().toString());
            deskripsiOrder.setText(editdeskripsiOrder.getText().toString());
            harga.setText("Rp. "+editHarga.getText().toString());
            saveEditOrder.setVisibility(View.GONE);
            editnamaPemesan.setVisibility(View.INVISIBLE);
            editjumlahOrder.setVisibility(View.INVISIBLE);
            editHarga.setVisibility(View.INVISIBLE);
            editdeskripsiOrder.setVisibility(View.INVISIBLE);
        }
        else{
            Toast.makeText(this, "Order Belum Lengkap",
                    Toast.LENGTH_SHORT).show();
        }


    }

    public void deleteOrder(View view){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("order").child(orderFix.getKey()).removeValue();
        Toast.makeText(DetailActivity.this, "Order Dihapus",
                Toast.LENGTH_SHORT).show();
        Integer pos = 2;
        Intent intent = new Intent();
        intent.putExtra(MainActivity.STRING_RESULT_DATA_KEY, pos);
        setResult(RESULT_OK, intent);
        finish();

    }
}
