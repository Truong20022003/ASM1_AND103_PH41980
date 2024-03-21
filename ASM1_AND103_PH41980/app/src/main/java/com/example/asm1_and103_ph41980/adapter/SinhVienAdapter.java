package com.example.asm1_and103_ph41980.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm1_and103_ph41980.Interface.OnDeleteClick;
import com.example.asm1_and103_ph41980.Interface.OnItemClick;
import com.example.asm1_and103_ph41980.Model.SinhVien;
import com.example.asm1_and103_ph41980.databinding.ItemRcvBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SinhVienAdapter extends RecyclerView.Adapter<SinhVienAdapter.ViewHolder> {

    Context context;
    List<SinhVien> list;

    private OnItemClick mListener;
    private OnDeleteClick onItemDelete;

    public void setOnItemClick(OnItemClick listener) {
        mListener = listener;
    }
    public void onItemDelete(OnDeleteClick listener) {
        onItemDelete = listener;
    }

    public SinhVienAdapter(Context context, List<SinhVien> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRcvBinding binding = ItemRcvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(list.get(position).getAnh()).into(holder.binding.imgSV);
        holder.binding.txtName.setText("Tên sinh viên: " + list.get(position).getTen());
        holder.binding.txtMa.setText("Mã sinh viên: " + list.get(position).getMasv());
        holder.binding.txtDiem.setText("Điểm trung bình: " + list.get(position).getDiem());
        if (list.get(position).getTrangthai() == true) {
            holder.binding.txtTrangThai.setText("Đang học");
            holder.binding.txtTrangThai.setTextColor(Color.parseColor("#0099FF"));
        }else {
            holder.binding.txtTrangThai.setText("Học lại");
            holder.binding.txtTrangThai.setTextColor(Color.parseColor("#FF0000"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(holder.getAdapterPosition());
                }
            }
        });

        holder.binding.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onItemDelete != null) {
                    onItemDelete.onItemDelete(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        ItemRcvBinding binding;
        public ViewHolder(ItemRcvBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
