package com.dicka.spring.javacore.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dicka.spring.javacore.MainActivity;
import com.dicka.spring.javacore.R;
import com.dicka.spring.javacore.entities.Kategori;
import com.dicka.spring.javacore.services.KategoriServices;

public class KategoriAddActivity extends AppCompatActivity {


    private Button btnSimpan;
    private EditText edtNama;
    private EditText edtDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_kategori_add);
            //deklarasi variable
            btnSimpan = (Button) findViewById(R.id.btnSimpan);
            edtNama = (EditText) findViewById(R.id.etNama);
            edtDeskripsi = (EditText) findViewById(R.id.etDeskripsi);

            //button kembali
            Button btnKembali = (Button) findViewById(R.id.btnKembali);
            btnKembali.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent_kembali = new Intent(KategoriAddActivity.this, MainActivity.class);
                    startActivity(intent_kembali);
                }
            });

            //button simpan
            btnSimpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        Kategori kategori = new Kategori();
                        kategori.setNama(String.valueOf(edtNama.getText().toString()));
                        kategori.setDeskripsi(String.valueOf(edtDeskripsi.getText().toString()));
                        boolean valid = new HttpRequestAdd().execute(kategori).get();
                        if(valid){
                            AlertDialog.Builder builderMessage = new AlertDialog.Builder(view.getContext());
                            builderMessage.setMessage("data berhasil disimpan").show();
                            Intent intent = new Intent(KategoriAddActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                            builder.setMessage("data gagal disimpan !").show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage(e.getMessage());
            builder.create().show();
        }

    }

    private class HttpRequestAdd extends AsyncTask<Kategori, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Kategori... kategoris) {
            KategoriServices kategoriServices = new KategoriServices();
            return kategoriServices.getInsertKategori(kategoris[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }
}
