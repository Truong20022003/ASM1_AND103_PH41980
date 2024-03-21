package com.example.asm1_and103_ph41980.Interface;

import com.example.asm1_and103_ph41980.Model.SinhVien;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    String DOMAIN = "http://192.168.0.104:3000/";
    @GET("/api/list")
    retrofit2.Call<List<SinhVien>> getSinhViens();

    @POST("/api/add")
    Call<SinhVien> addSinhvien(@Body SinhVien sinhVien);

    @PUT("/api/update/{id}")
    Call<SinhVien> updateSinhvien(@Path("id") String id, @Body SinhVien sinhVien);

    @DELETE("/api/delete/{id}")
    Call<Void> deleteSinhVien(@Path("id") String id);
}
