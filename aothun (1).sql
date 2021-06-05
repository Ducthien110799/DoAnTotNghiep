-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 20, 2021 lúc 09:11 AM
-- Phiên bản máy phục vụ: 10.4.17-MariaDB
-- Phiên bản PHP: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `aothun`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `iddh` int(11) NOT NULL,
  `idsp` int(11) NOT NULL,
  `soluongctdh` int(11) NOT NULL,
  `giactdh` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `iddh` int(11) NOT NULL,
  `idkh` int(11) NOT NULL,
  `hinhthucthanhtoan` varchar(255) NOT NULL,
  `diachigiaohang` varchar(255) NOT NULL,
  `xacnhandh` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`iddh`, `idkh`, `hinhthucthanhtoan`, `diachigiaohang`, `xacnhandh`) VALUES
(1, 1, 'tiền mặt', 'kiên giang', 0),
(2, 4, 'momo', 'quận 12', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `idkh` int(11) NOT NULL,
  `tenkh` varchar(255) NOT NULL,
  `matkhaukh` varchar(255) NOT NULL,
  `emailkh` varchar(255) NOT NULL,
  `diachikh` varchar(255) NOT NULL,
  `sodienthoaikh` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`idkh`, `tenkh`, `matkhaukh`, `emailkh`, `diachikh`, `sodienthoaikh`) VALUES
(1, 'adf', '12', 'asd', 'das', 'asdasd'),
(2, 'adf', '12', 'asd', 'das', 'asdasd'),
(3, 's', 'a', 'a', '', ''),
(4, 'asd', '1', 'das', '', ''),
(5, 'aa', 'w', 'aa', '', ''),
(6, '1', '1', '1', '', ''),
(7, 'duc thien', '1', 'eaa', '', ''),
(19, '5', '5', '8', '', ''),
(20, '5', '5', '8', '', ''),
(21, '5', '5', '8', '', ''),
(22, '', '', '', '', ''),
(23, 'e', '2', 'e', '', ''),
(24, 'e', '5', 'f', '', ''),
(25, 'hh', 'j', 'hh', 'dd', 'hh'),
(26, 'dd', 'dd', 'dd', 'dd', '11');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `idloaisp` int(11) NOT NULL,
  `tenloaisp` varchar(255) NOT NULL,
  `hinhanhloaisp` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`idloaisp`, `tenloaisp`, `hinhanhloaisp`) VALUES
(1, 'Áo THUN PHÔNG', 'https://img.icons8.com/bubbles/50/000000/t-shirt.png'),
(2, 'ÁO THUN POLO', 'https://img.icons8.com/emoji/48/000000/t-shirt-emoji.png'),
(3, 'ÁO THUN SWEATER', 'https://img.icons8.com/bubbles/50/000000/jumper.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `idsp` int(11) NOT NULL,
  `tensp` varchar(255) NOT NULL,
  `motasp` varchar(255) NOT NULL,
  `hinhanhsp` varchar(255) NOT NULL,
  `sizesp` varchar(255) NOT NULL,
  `soluongton` int(11) NOT NULL,
  `giasp` int(11) NOT NULL,
  `idloaisp` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`idsp`, `tensp`, `motasp`, `hinhanhsp`, `sizesp`, `soluongton`, `giasp`, `idloaisp`) VALUES
(24, 'đient haoij', 'đâsdasd', '_MG_7343.jpg', 'S', 10, 10000, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `idtk` int(11) NOT NULL,
  `tentk` varchar(255) NOT NULL,
  `matkhautk` varchar(255) NOT NULL,
  `emailtk` varchar(255) NOT NULL,
  `loaitaikhoan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`idtk`, `tentk`, `matkhautk`, `emailtk`, `loaitaikhoan`) VALUES
(1, 'ducthien', '1', 'ducthien@gmail.com', 'admin'),
(7, 'nguyenducthien', '4', 'nguyenthien110799@gmail.com', 'admin'),
(8, 'nguyenducthien', '2', 'nguyenthien110799@gmail.com', 'admin'),
(10, 'nguyen duc thien', 'q', 'nguyenthien110799@gmail.com', 'admin');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thanhtoan`
--

CREATE TABLE `thanhtoan` (
  `iddh` int(11) NOT NULL,
  `idtk` int(11) NOT NULL,
  `ngaythanhtoan` date NOT NULL,
  `sotienthanhtoan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD KEY `iddh` (`iddh`),
  ADD KEY `idsp` (`idsp`);

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`iddh`),
  ADD KEY `idkh` (`idkh`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`idkh`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`idloaisp`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`idsp`),
  ADD KEY `idloaisp` (`idloaisp`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`idtk`);

--
-- Chỉ mục cho bảng `thanhtoan`
--
ALTER TABLE `thanhtoan`
  ADD KEY `iddh` (`iddh`),
  ADD KEY `idtk` (`idtk`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `iddh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `idkh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `idloaisp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `idsp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `idtk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD CONSTRAINT `chitietdonhang_ibfk_1` FOREIGN KEY (`iddh`) REFERENCES `donhang` (`iddh`),
  ADD CONSTRAINT `chitietdonhang_ibfk_2` FOREIGN KEY (`idsp`) REFERENCES `sanpham` (`idsp`);

--
-- Các ràng buộc cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD CONSTRAINT `donhang_ibfk_1` FOREIGN KEY (`idkh`) REFERENCES `khachhang` (`idkh`);

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`idloaisp`) REFERENCES `loaisanpham` (`idloaisp`);

--
-- Các ràng buộc cho bảng `thanhtoan`
--
ALTER TABLE `thanhtoan`
  ADD CONSTRAINT `thanhtoan_ibfk_1` FOREIGN KEY (`iddh`) REFERENCES `donhang` (`iddh`),
  ADD CONSTRAINT `thanhtoan_ibfk_2` FOREIGN KEY (`idtk`) REFERENCES `taikhoan` (`idtk`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
