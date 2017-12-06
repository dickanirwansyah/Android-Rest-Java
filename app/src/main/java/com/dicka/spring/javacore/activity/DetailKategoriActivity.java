package com.dicka.spring.javacore.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dicka.spring.javacore.MainActivity;
import com.dicka.spring.javacore.R;
import com.dicka.spring.javacore.entities.Kategori;
import com.dicka.spring.javacore.services.KategoriServices;

public class DetailKategoriActivity extends AppCompatActivity {

    private Button btnUpdates;
    private Button btnDeletes;
    private TextView textViewId;
    private TextView textViewNama;
    private TextView textViewDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail_kategori);

            //deklarasi variabel
            Intent intgetdetil = getIntent();
            final Kategori kategori = (Kategori) intgetdetil.getSerializableExtra("kategori");
            btnDeletes = (Button) findViewById(R.id.btnHapus);
            btnUpdates = (Button) findViewById(R.id.btnUpdate);
            textViewId = (TextView) findViewById(R.id.textDetilId);
            textViewNama = (TextView) findViewById(R.id.textDetilNama);
            textViewDeskripsi = (TextView) findViewById(R.id.textDetilDeskripsi);

            //get inten
            textViewId.setText(String.valueOf(kategori.getIdkategori()));
            textViewNama.setText(String.valueOf(kategori.getNama()));
            textViewDeskripsi.setText(String.valueOf(kategori.getDeskripsi()));

            //button hapus
            btnDeletes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        AlertDialog.Builder messages = new AlertDialog.Builder(view.getContext());
                        messages.setTitle("Konfirmasi")
                                .setMessage("Yakin ingin hapus "+kategori.getNama()+" ?")
                                .setNegativeButton("Batalkan", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.cancel();
                                    }
                                }).setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                try{
                                    boolean valid = new HttpRequestDeleteKategori()
                                            .execute(kategori.getIdkategori()).get();
                                    if(valid){
                                        Intent intent_kembali = new Intent(DetailKategoriActivity.this, MainActivity.class);
                                        startActivity(intent_kembali);
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });

            //btn update
            btnUpdates.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent_update = new Intent(DetailKategoriActivity.this, UpdateKategoriActivity.class);
                    intent_update.putExtra("kategori", kategori);
                    startActivity(intent_update);
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //http request delete
    private class HttpRequestDeleteKategori extends AsyncTask<Integer, Void, Boolean>{

        @Override
        protected Boolean doInBackground(Integer... integers) {
            KategoriServices kategoriServices = new KategoriServices();
            return kategoriServices.getDeleteKategori(integers[0]);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }

}
