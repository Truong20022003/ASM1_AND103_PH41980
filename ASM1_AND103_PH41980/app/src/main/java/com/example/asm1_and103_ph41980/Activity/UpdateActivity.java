package com.example.asm1_and103_ph41980.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.asm1_and103_ph41980.Interface.ApiService;
import com.example.asm1_and103_ph41980.Model.SinhVien;
import com.example.asm1_and103_ph41980.R;
import com.example.asm1_and103_ph41980.databinding.ActivityUpdateBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateActivity extends AppCompatActivity {
    private ActivityUpdateBinding binding;
    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        SinhVien sinhVien = (SinhVien) getIntent().getSerializableExtra("sinhvien");
        binding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.edName.setText("");
                binding.edMaSV.setText("");
                binding.edPoint.setText("");
                binding.edTrangThai.setText("");
                binding.edAnh.setText("");
                startActivity(new Intent(UpdateActivity.this, ListActivity.class));
            }
        });

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = binding.edName.getText().toString().trim();
                String anh = binding.edAnh.getText().toString().trim();
                String ma = binding.edMaSV.getText().toString().trim();
                double diem = Double.parseDouble(binding.edPoint.getText().toString().trim());
                Boolean trangthai = Boolean.getBoolean(binding.edTrangThai.getText().toString().trim());


                SinhVien updateSinhVien = new SinhVien(ten,anh,ma,diem,trangthai);
                updateSinhVien(sinhVien.get_id(),updateSinhVien);
            }
        });
    }
    private void updateSinhVien(String id, SinhVien updatedSinhVien) {
        Call<SinhVien> call = apiService.updateSinhvien(id, updatedSinhVien);
        call.enqueue(new Callback<SinhVien>() {
            @Override
            public void onResponse(Call<SinhVien> call, Response<SinhVien> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UpdateActivity.this, "Cập nhật sinh viên thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateActivity.this, ListActivity.class));
                } else {

                    Toast.makeText(UpdateActivity.this, "Cập nhật sinh viên thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SinhVien> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "Có lỗi xảy ra ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}