package com.cntt.doantotnghiep.ultil;

public class Server {
    public static String localhost ="192.168.0.102/server";
    public static String DuongdanLoaisp = "http://" + localhost+ "/app/getloaisp.php";
    public static String Duongdansanphammoinhat = "http://" + localhost+ "/app/getsanphammoinhat.php";
    public static String Duongdanaothunphong = "http://" + localhost+ "/app/getsanpham.php?page=";
    public static String Duongdandonhang = "http://" + localhost+ "/app/thongtinkhachhang.php";
    public static String Duongdanchitietdonhang = "http://" + localhost+ "/app/chitietdonhang.php";
    public static String URLLogin = "http://"+localhost+"/app/login.php?email=";
    public static String URLAllsanpham = "http://" + localhost+ "/app/getallsanpham.php";
    public static String URLRegister = "http://"+localhost+"/app/register.php";
    public static String URLDonhangchuaxn = "http://"+localhost+"/app/getdonhangchuaxn.php";
    public static String URLDonhangdaxn = "http://"+localhost+"/app/getdonhangdaxn.php";
}
