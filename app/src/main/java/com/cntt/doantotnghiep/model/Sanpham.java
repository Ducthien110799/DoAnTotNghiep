package com.cntt.doantotnghiep.model;

import java.io.Serializable;

public class Sanpham implements Serializable {
    public int ID;
    public String Tensanpham;
    public int Giasanpham;
    public String size;
    public String Hinhanhsanpham;
    public String Motasanpham;
    public int soluonghangton;
    public int IDSanpham;

    public Sanpham(int ID, String tensanpham, int giasanpham, String size, String hinhanhsanpham, String motasanpham, int soluonghangton, int IDSanpham) {
        this.ID = ID;
        Tensanpham = tensanpham;
        Giasanpham = giasanpham;
        this.size = size;
        Hinhanhsanpham = hinhanhsanpham;
        Motasanpham = motasanpham;
        this.soluonghangton = soluonghangton;
        this.IDSanpham = IDSanpham;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTensanpham() {
        return Tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        Tensanpham = tensanpham;
    }

    public int getGiasanpham() {
        return Giasanpham;
    }

    public void setGiasanpham(int giasanpham) {
        Giasanpham = giasanpham;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getHinhanhsanpham() {
        return Hinhanhsanpham;
    }

    public void setHinhanhsanpham(String hinhanhsanpham) {
        Hinhanhsanpham = hinhanhsanpham;
    }

    public String getMotasanpham() {
        return Motasanpham;
    }

    public void setMotasanpham(String motasanpham) {
        Motasanpham = motasanpham;
    }

    public int getSoluonghangton() {
        return soluonghangton;
    }

    public void setSoluonghangton(int soluonghangton) {
        this.soluonghangton = soluonghangton;
    }

    public int getIDSanpham() {
        return IDSanpham;
    }

    public void setIDSanpham(int IDSanpham) {
        this.IDSanpham = IDSanpham;
    }
}
