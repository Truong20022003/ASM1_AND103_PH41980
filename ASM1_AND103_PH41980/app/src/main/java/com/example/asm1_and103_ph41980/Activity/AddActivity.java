package com.example.asm1_and103_ph41980.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import com.example.asm1_and103_ph41980.Interface.ApiService;
import com.example.asm1_and103_ph41980.Model.SinhVien;
import com.example.asm1_and103_ph41980.databinding.ActivityAddBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddActivity extends AppCompatActivity {
    private ActivityAddBinding binding;
    private ApiService apiService;
    List<SinhVien> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        list = new ArrayList<>();

        binding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edName.setText("");
                binding.edMaSV.setText("");
                binding.edPoint.setText("");
                startActivity(new Intent(AddActivity.this, ListActivity.class));
            }
        });

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSinhVien();
            }
        });

    }


    private void addSinhVien(){
            String ten = binding.edName.getText().toString().trim();
            String anh = binding.edAnh.getText().toString().trim();
            String ma = binding.edMaSV.getText().toString().trim();
            double diem = Double.parseDouble(binding.edPoint.getText().toString().trim());
            Boolean trangthai = Boolean.getBoolean(binding.edTrangThai.getText().toString().trim());

            SinhVien newSinhVien = new SinhVien(ten,anh,ma,diem,trangthai);

        Call<SinhVien> call = apiService.addSinhvien(newSinhVien);
        call.enqueue(new Callback<SinhVien>() {
            @Override
            public void onResponse(Call<SinhVien> call, Response<SinhVien> response) {
                if (response.isSuccessful()){
                    SinhVien sv = response.body();
                    list.add(sv);
                    Toast.makeText(AddActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddActivity.this,ListActivity.class));
                }
            }

            @Override
            public void onFailure(Call<SinhVien> call, Throwable t) {
                Toast.makeText(AddActivity.this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
            }
        });
    }


}