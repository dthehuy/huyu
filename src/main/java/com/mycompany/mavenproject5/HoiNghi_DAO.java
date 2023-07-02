/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject5;

/**
 *
 * @author dtheh
 */
public class HoiNghi_DAO {
    private String maHoiNghi;
    private String tenHoiNghi;
    private int soNguoi;
    private String maLoaiPhong;

    public HoiNghi_DAO(String maHoiNghi, String tenHoiNghi, int soNguoi, String maLoaiPhong) {
        this.maHoiNghi = maHoiNghi;
        this.tenHoiNghi = tenHoiNghi;
        this.soNguoi = soNguoi;
        this.maLoaiPhong = maLoaiPhong;
    }

    public HoiNghi_DAO() {
    }

    public String getMaHoiNghi() {
        return maHoiNghi;
    }

    public void setMaHoiNghi(String maHoiNghi) {
        this.maHoiNghi = maHoiNghi;
    }

    public String getTenHoiNghi() {
        return tenHoiNghi;
    }

    public void setTenHoiNghi(String tenHoiNghi) {
        this.tenHoiNghi = tenHoiNghi;
    }

    public int getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }

    public String getMaLoaiPhong() {
        return maLoaiPhong;
    }

    public void setMaLoaiPhong(String maLoaiPhong) {
        this.maLoaiPhong = maLoaiPhong;
    }
    
}
