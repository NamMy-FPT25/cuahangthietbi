package com.example.onlineapplicationstore.model;

public class Giohang {
    public int IDsp;
    public String tensp;
    public long giasp;
    public String hinhsp;
    public int soluongsp;

    public Giohang(int IDsp, String tensp, long giasp, String hinhsp, int soluongsp) {
        this.IDsp = IDsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.hinhsp = hinhsp;
        this.soluongsp = soluongsp;
    }

    public int getIDsp() {
        return IDsp;
    }

    public void setIDsp(int IDsp) {
        this.IDsp = IDsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public long getGiasp() {
        return giasp;
    }

    public void setGiasp(long giasp) {
        this.giasp = giasp;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }
}

