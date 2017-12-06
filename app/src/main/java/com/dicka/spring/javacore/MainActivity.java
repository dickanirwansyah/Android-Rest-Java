package com.dicka.spring.javacore;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.dicka.spring.javacore.activity.DetailKategoriActivity;
import com.dicka.spring.javacore.activity.KategoriAddActivity;
import com.dicka.spring.javacore.adapter.KategoriAdapter;
import com.dicka.spring.javacore.entities.Kategori;
import com.dicka.spring.javacore.services.KategoriServices;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //load listview kategori
            final List<Kategori> kategoris = new HttpRequestKategoriList().execute().get();
            ListView listView = (ListView) findViewById(R.id.listViewKategori);
            listView.setAdapter(new KategoriAdapter(kategoris, getApplicationContext()));

            //list on click
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Intent intent_detil= new Intent(MainActivity.this, DetailKategoriActivity.class);
                    intent_detil.putExtra("kategori", kategoris.get(position));
                    startActivity(intent_detil);
                }
            });

            //tambah kategori action
            Button btnTambahKategori = (Button) findViewById(R.id.btnTambahKategori);
            btnTambahKategori.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent inten_addkategori = new Intent(MainActivity.this, KategoriAddActivity.class);
                    startActivity(inten_addkategori);
                }
            });

        }catch (Exception e){
            e.printStackTrace();
            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
            builder.setMessage(e.getMessage());
            builder.create().show();
        }
    }


    //http request list kategori
    private class HttpRequestKategoriList extends AsyncTask<Void, Void, List<Kategori>>{

        @Override
        protected List<Kategori> doInBackground(Void... voids) {
            KategoriServices kategoriServices = new KategoriServices();
            return kategoriServices.getKategoriList();
        }

        @Override
        protected void onPostExecute(List<Kategori> kategoris) {
            super.onPostExecute(kategoris);
        }
    }
}
