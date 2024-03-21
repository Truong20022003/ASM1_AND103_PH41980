package com.example.asm1_and103_ph41980.Model;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private String _id;
    private String ten;
    private String anh;
    private String masv;
    private double diem;
    private Boolean trangthai;

    public SinhVien() {
    }

    public SinhVien(String _id, String ten, String anh, String masv, double diem, Boolean trangthai) {
        this._id = _id;
        this.ten = ten;
        this.anh = anh;
        this.masv = masv;
        this.diem = diem;
        this.trangthai = trangthai;
    }

    public SinhVien(String ten, String anh, String masv, double diem, Boolean trangthai) {
        this.ten = ten;
        this.anh = anh;
        this.masv = masv;
        this.diem = diem;
        this.trangthai = trangthai;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    public Boolean getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(Boolean trangthai) {
        this.trangthai = trangthai;
    }
}
