package com.dicka.spring.javacore.services;

import com.dicka.spring.javacore.entities.Kategori;

import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by java-spring on 06/12/17.
 */

public class KategoriServices {


    private final static String URI_LIST_KATEGORI = "http://192.168.43.68:8080/java-server-api/api/kategoris";
    private final static String URI_INSERT_KATEGORI = "http://192.168.43.68:8080/java-server-api/api/insertKategori";
    private final static String URI_UPDATE_KATEGORI = "http://192.168.43.68:8080/java-server-api/api/updateKategori";
    private final static String URI_DELETE_KATEGORI = "http://192.168.43.68:8080/java-server-api/api/deleteKategori/";

    private RestTemplate restTemplate = new RestTemplate();


    //MENAMPILKAN DATA KATEGORI LIST
    public List<Kategori> getKategoriList(){
        try{
            return restTemplate.exchange(
                    URI_LIST_KATEGORI,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Kategori>>() {
                    }).getBody();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //INSERT DATA KATEGORI
    public boolean getInsertKategori(Kategori kategori){
        try{
            Map<String, String> values = new HashMap<String, String>();
            values.put("nama", String.valueOf(kategori.getNama()));
            values.put("deskripsi", String.valueOf(kategori.getDeskripsi()));

            JSONObject JSON_PARSE = new JSONObject(values);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(JSON_PARSE.toString(), headers);
            restTemplate.postForEntity(URI_INSERT_KATEGORI, entity, null);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //DELETE DATA KATEGORI
    public boolean getDeleteKategori(int idkategori){
        try{
            restTemplate.delete(URI_DELETE_KATEGORI+idkategori);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //UPDATE DATA KATEGORI
    public boolean getUpdateKategori(Kategori kategori){
        try{
            Map<String, String> values = new HashMap<String, String>();
            values.put("idkategori", String.valueOf(kategori.getIdkategori()));
            values.put("nama", String.valueOf(kategori.getNama()));
            values.put("deskripsi", String.valueOf(kategori.getDeskripsi()));

            JSONObject JSON_PARSE = new JSONObject(values);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String>entity = new HttpEntity<String>(JSON_PARSE.toString(), headers);
            restTemplate.postForEntity(URI_UPDATE_KATEGORI, entity, null);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
