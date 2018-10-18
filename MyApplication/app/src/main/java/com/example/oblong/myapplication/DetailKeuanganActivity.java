package com.example.oblong.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.util.ArrayList;


public class DetailKeuanganActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    TextView namaPemesan, jumlahOrder, jenisOrder, deskripsiOrder,belanjaBahan, potong, sablonBordir, jahit, pengeluaranLain,keuntungan,hargaTotal,harga,deadline;
    Button save,edit,done;
    Order orderFix;
    private MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_keuangan);
        Intent intent = getIntent();
//        ArrayList<Order> tempat = Parcels.unwrap(intent.getParcelableExtra("Tempat"));
        orderFix = Parcels.unwrap(intent.getParcelableExtra("Order"));

        //Adding toolbar to the activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Detail Keuangan Order");
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


        hargaTotal=(TextView)findViewById(R.id.hargaTotal);
        namaPemesan=(TextView)findViewById(R.id.namaPemesan);
        jumlahOrder=(TextView)findViewById(R.id.jumlahOrder);
        jenisOrder=(TextView)findViewById(R.id.jenisOrder);
        deskripsiOrder=(TextView)findViewById(R.id.deskripsiOrder);
        belanjaBahan=(TextView)findViewById(R.id.belanjaBahan);
        potong=(TextView)findViewById(R.id.potong);
        sablonBordir=(TextView)findViewById(R.id.sablonBordir);
        jahit=(TextView)findViewById(R.id.jahit);
        pengeluaranLain=(TextView)findViewById(R.id.pengeluaranLain);
        keuntungan=(TextView)findViewById(R.id.keuntungan);
        harga=(TextView)findViewById(R.id.harga);
        hargaTotal=(TextView)findViewById(R.id.hargaTotal);
        deadline=(TextView)findViewById(R.id.alamat);


        harga.setText("Rp. "+orderFix.getHarga());
        hargaTotal.setText("Rp. "+orderFix.getHarga()+" * "+orderFix.getJumlah()+"pcs = Rp. "+(String.valueOf(orderFix.getHarga()*orderFix.getJumlah())));
        deadline.setText(orderFix.getDeadline());
        namaPemesan.setText(orderFix.getNamaPemesan());
        jumlahOrder.setText(String.valueOf(orderFix.getJumlah()));
        jenisOrder.setText(orderFix.getJenis());
        deskripsiOrder.setText(orderFix.getDeskripsi());
        belanjaBahan.setText("Rp. "+String.valueOf(orderFix.getBelanjaBahan()));
        potong.setText("Rp. "+String.valueOf(orderFix.getPotong()));
        sablonBordir.setText("Rp. "+String.valueOf(orderFix.getSalonBordir()));
        jahit.setText("Rp. "+String.valueOf(orderFix.getJahit()));
        pengeluaranLain.setText("Rp. "+String.valueOf(orderFix.getPengeluaranLain()));
        keuntungan.setText("Rp. "+String.valueOf(orderFix.getKeuntungan()));
    }

    public void deleteOrder(View view){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("order").child(orderFix.getKey()).removeValue();
        Toast.makeText(DetailKeuanganActivity.this, "Order Dihapus",
                Toast.LENGTH_SHORT).show();
        Integer pos = 2;
        Intent intent = new Intent();
        intent.putExtra(MainActivity.STRING_RESULT_DATA_KEY, pos);
        setResult(RESULT_OK, intent);
        finish();

    }
}
