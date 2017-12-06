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

public class UpdateKategoriActivity extends AppCompatActivity {

    private Button btnUpdate;
    private Button btnKembali;
    private EditText etNama;
    private EditText etDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_update_kategori);

            //deklarasi variable
            Intent intentget = getIntent();
            final Kategori kategori = (Kategori) intentget.getSerializableExtra("kategori");
            etNama = (EditText) findViewById(R.id.edtEditNama);
            etDeskripsi = (EditText) findViewById(R.id.edtEditDeskrispi);
            btnKembali = (Button) findViewById(R.id.btnKembali);
            btnUpdate = (Button) findViewById(R.id.btnEdit);
            etNama.setText(String.valueOf(kategori.getNama()));
            etDeskripsi.setText(String.valueOf(kategori.getDeskripsi()));

            //btn kembali
            btnKembali.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(UpdateKategoriActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });


            //btn update
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        kategori.setNama(String.valueOf(etNama.getText().toString()));
                        kategori.setDeskripsi(String.valueOf(etDeskripsi.getText().toString()));
                        boolean valid = new HttpRequestEditKategori().execute(kategori).get();
                        if(valid){
                            AlertDialog.Builder messages = new AlertDialog.Builder(view.getContext());
                            messages.setMessage("data berhasil diupdate");
                            Intent intent = new Intent(UpdateKategoriActivity.this, MainActivity.class);
                            startActivity(intent);

                        }else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                            builder.setMessage("data gagal disimpan");
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //http request edit
    private class HttpRequestEditKategori extends AsyncTask<Kategori, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Kategori... kategoris) {
            KategoriServices kategoriServices = new KategoriServices();
            return kategoriServices.getUpdateKategori(kategoris[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }
}
