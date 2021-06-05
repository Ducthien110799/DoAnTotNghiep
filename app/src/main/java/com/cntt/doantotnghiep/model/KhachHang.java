package com.cntt.doantotnghiep.model;

public class KhachHang {
    private String idkh;
    private String tenkh;
    private String emailkh;
    private String diachikh;
    private String sodienthoaikh;

    public KhachHang(String idkh, String tenkh, String emailkh, String diachikh, String sodienthoaikh) {
        this.idkh = idkh;
        this.tenkh = tenkh;
        this.emailkh = emailkh;
        this.diachikh = diachikh;
        this.sodienthoaikh = sodienthoaikh;
    }

    public KhachHang() {
    }

    public KhachHang(String idkh) {
        this.idkh = idkh;
    }

    public String getIdkh() {
        return idkh;
    }

    public void setIdkh(String idkh) {
        this.idkh = idkh;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getEmailkh() {
        return emailkh;
    }

    public void setEmailkh(String emailkh) {
        this.emailkh = emailkh;
    }

    public String getDiachikh() {
        return diachikh;
    }

    public void setDiachikh(String diachikh) {
        this.diachikh = diachikh;
    }

    public String getSodienthoaikh() {
        return sodienthoaikh;
    }

    public void setSodienthoaikh(String sodienthoaikh) {
        this.sodienthoaikh = sodienthoaikh;
    }
}
