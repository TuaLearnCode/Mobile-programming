package com.example.huynhtruongthaoduyen;

public class Ticket {
    private int id;
    private String tenPhim;
    private String gioChieu;
    private String ghe;
    private String trangThai;

    public Ticket(int id, String tenPhim, String gioChieu, String ghe, String trangThai) {
        this.id = id;
        this.tenPhim = tenPhim;
        this.gioChieu = gioChieu;
        this.ghe = ghe;
        this.trangThai = trangThai;
    }

    public int getId() { return id; }
    public String getTenPhim() { return tenPhim; }
    public String getGioChieu() { return gioChieu; }
    public String getGhe() { return ghe; }
    public String getTrangThai() { return trangThai; }
    public void setGioChieu(String gioChieu) {
        this.gioChieu = gioChieu;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

}
