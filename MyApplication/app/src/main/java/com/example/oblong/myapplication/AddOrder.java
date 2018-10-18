package com.example.oblong.myapplication;

import android.graphics.Point;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import java.util.Calendar;


/**
 * Created by Jupe Taek on 10/6/2017.
 */

public class AddOrder extends Fragment implements View.OnClickListener{

    private DatabaseReference mDatabase;
    private View view;
    EditText NamaPemesan,JumlahOrder,KeteranganOrder,Harga;
    Spinner spinnerJenis;
    Button buttonDate,buttonAdd;
    private int mYear,mMonth,mDay;
    Date date;
    private String deadline;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_TEMPAT_PENGINAPAN);
        view = inflater.inflate(R.layout.add_order, container, false);
        NamaPemesan=(EditText)view.findViewById(R.id.NamaPemesan);
        JumlahOrder=(EditText)view.findViewById(R.id.JumlahOrder);
        Harga=(EditText)view.findViewById(R.id.Harga);
        KeteranganOrder=(EditText)view.findViewById(R.id.KeteranganOrder);
        spinnerJenis = (Spinner) view.findViewById(R.id.spinnerJenisOrder);
        buttonDate=(Button)view.findViewById(R.id.buttonDate);
        buttonAdd=(Button)view.findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);
        buttonDate.setOnClickListener(this);


        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener tanggal = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                // myCalendar.add(Calendar.DATE, 0);
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                date=myCalendar.getTime();
                deadline=sdf.format(myCalendar.getTime());
                buttonDate.setText(deadline);
            }


        };

        buttonDate.setOnClickListener(this);
        ArrayList<String> dataString = new ArrayList<>();

        dataString.add("Kaos");
        dataString.add("Kemeja");
        dataString.add("Jaket");
        dataString.add("Polo");
        dataString.add("Seragam");

        ArrayAdapter<String> adapterString = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, dataString);
        adapterString.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerJenis.setAdapter(adapterString);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonDate:
                setDate();
                break;
            case R.id.buttonAdd:
                add();
                break;
        }
    }

    public void setDate(){
        // TODO Auto-generated method stub
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(view.getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox

                        if (year < mYear)
                            view.updateDate(mYear,mMonth,mDay);

                        if (monthOfYear < mMonth && year == mYear)
                            view.updateDate(mYear,mMonth,mDay);

                        if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                            view.updateDate(mYear,mMonth,mDay);

                        buttonDate.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        dpd.getDatePicker().setMinDate(System.currentTimeMillis());
        dpd.show();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //mFirebaseAdapter.cleanup();
    }

    public void add() {

        String namaPemesan=NamaPemesan.getText().toString();
        String keterangan=KeteranganOrder.getText().toString();
        String jumlah=JumlahOrder.getText().toString();
        String harga=Harga.getText().toString();
        String deadline=buttonDate.getText().toString();
        if((NamaPemesan.length() > 0)&&(KeteranganOrder.length() > 0)&&(JumlahOrder.length() > 0)&&(Harga.length() > 0)&&(deadline.length() > 0)){
            Order item=new Order();
            item.setnamaPemesan(namaPemesan);
            item.setJenis(spinnerJenis.getSelectedItem().toString());
            item.setDeskripsi(keterangan);
            item.setJumlah(Integer.parseInt(jumlah));
            item.setHarga(Integer.parseInt(harga));
            item.setBelanjaBahan(0);
            item.setJahit(0);
            item.setPengeluaranLain(0);
            item.setPotong(0);
            item.setSalonBordir(0);
            item.setKeuntungan(0);
            item.setKey("");
            item.setIsInputBiaya(false);
            item.setIsDone(false);
            item.setDeadline(deadline);
            mDatabase = FirebaseDatabase.getInstance().getReference();
            String key= mDatabase.push().getKey();
            item.setKey(key);
            mDatabase.child("order").child(key).setValue(item);
            Toast.makeText(view.getContext(), "Order Berhasil Ditambahkan",
                    Toast.LENGTH_SHORT).show();
            ((MainActivity) getActivity()).move(1);
            NamaPemesan.setText(null);
            JumlahOrder.setText(null);
            Harga.setText(null);
            KeteranganOrder.setText(null);
        }else{
            Toast.makeText(view.getContext(), "Order Belum Lengkap",
                    Toast.LENGTH_SHORT).show();
        }

    }


}
