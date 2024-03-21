package com.example.asm1_and103_ph41980.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.asm1_and103_ph41980.Interface.ApiService;
import com.example.asm1_and103_ph41980.Interface.OnDeleteClick;
import com.example.asm1_and103_ph41980.Interface.OnItemClick;
import com.example.asm1_and103_ph41980.Model.SinhVien;
import com.example.asm1_and103_ph41980.adapter.SinhVienAdapter;
import com.example.asm1_and103_ph41980.databinding.ActivityListBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity {
    private ActivityListBinding binding;
    List<SinhVien> list;
    SinhVienAdapter adapter;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

        Call<List<SinhVien>> call = apiService.getSinhViens();

        call.enqueue(new Callback<List<SinhVien>>() {
            @Override
            public void onResponse(Call<List<SinhVien>> call, Response<List<SinhVien>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    binding.rcvDanhSach.setLayoutManager(layoutManager);
                    adapter = new SinhVienAdapter(getApplicationContext(), list);
                    binding.rcvDanhSach.setAdapter(adapter);
                }
                adapter.onItemDelete(new OnDeleteClick() {
                    @Override
                    public void onItemDelete(int position) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
                        builder.setTitle("Xác nhận xóa");
                        builder.setMessage("Bạn có chắc muốn xóa?");

                        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ListActivity.this, "Không xóa", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });

                        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                deleteSinhVien(position);

                                dialogInterface.dismiss();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                });

                adapter.setOnItemClick(new OnItemClick() {
                    @Override
                    public void onItemClick(int position) {
                        SinhVien selectedModel = list.get(position);
                        Intent intent = new Intent(ListActivity.this, UpdateActivity.class);
                        intent.putExtra("sinhvien", selectedModel);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<SinhVien>> call, Throwable t) {
                Log.e("Mainnn", t.getMessage());
            }
        });
        binding.floatAddDanhSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ListActivity.this, AddActivity.class));
            }
        });

    }

    private void deleteSinhVien(int position) {
        String id = list.get(position).get_id();
        Call<Void> call = apiService.deleteSinhVien(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    list.remove(position);
                    adapter.notifyItemRemoved(position);
                    Toast.makeText(ListActivity.this, "Đã xóa sinh viên thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ListActivity.this, "Xóa sinh viên không thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("ListActivity", "Error deleting sinh vien: " + t.getMessage());
                Toast.makeText(ListActivity.this, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}