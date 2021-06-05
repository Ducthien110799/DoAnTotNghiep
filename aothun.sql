-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 27, 2021 lúc 10:51 AM
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
  `phivanchuyen` varchar(200) NOT NULL,
  `xacnhandh` int(1) NOT NULL,
  `idtk` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(1, 'Hoàng Lâm Hải', 'hai123', 'HoangLamHai@gmail,com', 'TP.HCM]', '0965483586'),
(2, 'Lê Đức Linh', 'Leduclinh123', 'Leduclinh@gmail,com', 'TP.HCM', '0971830053'),
(3, 'Mai Nguyễn Quốc Huy', 'Quoc123', 'QuocHuy@gmail,com', 'Đà nẵng', '0982511171'),
(4, 'Nguyễn Thị Huỳnh Như', 'huynhnhu123', 'HuynhNhu@gmail,com', 'Đà nẵng', '0337516194'),
(5, 'Trần Thị Cẩm Tiên', 'camtien3223', 'HuynhNhu@gmail,com', 'Đà nẵng', ' 0943490699'),
(6, 'Nguyễn Thị Mỹ Linh', 'MyLinh3223', 'NguyenThi MyLinh@gmail,com', 'Hà Nội', '0920042211'),
(7, 'Lê Thị Bê', 'lethibe456', 'Lethibe@gmail,com', 'Hà Nội', '0966976514'),
(8, 'Nguyễn Trinh Trinh', 'trinh456', 'TrinhTrinh@gmail,com', 'TP.HCM', '0964082930'),
(9, 'Nguyễn Nhật Nam', 'NAM456', 'NguyenNhatNam@gmail,com', 'TP.HCM', '0967713431'),
(10, 'Phan Văn Huy', 'vanhuypro', 'PhanHuy@gmail,com', 'Hà Nội', '0769891256 '),
(11, 'Trần Võ Khánh Thi', 'Thideptrai', 'khanhthi0101@gmail,com', 'Sa đéc', '0767698314'),
(12, 'Phan Thị Minh Châu', 'Chau123456', 'PhanThiMinhChau@gmail,com', 'Đồng Nai', '0968958053'),
(13, 'Nguyễn Võ Trọng Nhân', 'Nhan123456', 'Trong nhan@gmail,com', 'Đồng Nai', '0335279584'),
(14, 'Đặng Thụy Mỹ Huyền', 'Myhuyenne', 'myhuyen@gmail,com', 'Đồng Nai', '0906855305');

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
(3, 'ÁO THUN LEN', 'https://img.icons8.com/bubbles/50/000000/jumper.png');

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
(1, 'Áo Polo Trơn Gắn Nhãn PO028 Màu Bò', 'Chất liệu: Cá sấu 4 chiều, 95% cotton, 5% Spandex.\r\nForm: Slimfit\r\nĐặc tính: Bền bỉ, mềm mại, thấm hút tốt và giữ mùi thơm lâu.', 'http://shoptwestside.000webhostapp.com/upload/ao-polo-1.jpg', 'XL', 45, 250000, 2),
(2, 'Áo Polo Trơn Gắn Nhãn PO028 Màu Bò', 'Chất liệu: Cá sấu 4 chiều, 95% cotton, 5% Spandex.\r\nForm: Slimfit\r\nĐặc tính: Bền bỉ, mềm mại, thấm hút tốt và giữ mùi thơm lâu.', 'http://shoptwestside.000webhostapp.com/upload/ao-polo-2.jpg', 'L', 56, 250000, 2),
(3, 'Áo Polo Phối Ba Lớp Màu PO013 Màu Trắng', 'Chất liệu: Pique Breathable 100% Cotton\r\nForm: Regular.', 'http://shoptwestside.000webhostapp.com/upload/ao-polo-3.jpg', 'L', 152, 250000, 2),
(4, 'Áo Polo Phối Ba Lớp Màu PO013 Màu Trắng', 'Chất liệu: Pique Breathable 100% Cotton\r\nForm: Regular.', 'http://shoptwestside.000webhostapp.com/upload/ao-polo-4.jpg', 'L', 152, 250000, 2),
(5, 'Áo Polo Phối Màu Cacao In Chữ PO011 Màu Đen', 'Chất liệu: Cá sấu 4 chiều - 95% cotton, 5% spandex.\r\nForm: Regular.', 'http://shoptwestside.000webhostapp.com/upload/ao-polo-5.jpg', 'L', 152, 250000, 2),
(6, 'Áo Polo Phối Màu Cacao In Chữ PO011 Màu Đen', 'Chất liệu: Cá sấu 4 chiều - 95% cotton, 5% spandex.\r\nForm: Regular.', 'http://shoptwestside.000webhostapp.com/upload/ao-polo-6.jpg', 'L', 170, 250000, 2),
(7, 'Áo Polo Ngực In Chữ PO001 Màu Đen', 'Chất liệu: 98% cotton, 2% spandex\r\nForm: Slimfit.', 'http://shoptwestside.000webhostapp.com/upload/ao-polo-7.jpg', 'M', 170, 250000, 2),
(8, 'Áo Thun Polo Rã Phối AT850 Màu Đen', 'Chất liệu: 100% Cotton 4 chiều.\r\nForm: Slimfit', 'http://shoptwestside.000webhostapp.com/upload/ao-polo-8.jpg', 'M', 100, 250000, 2),
(9, 'Áo Thun Polo AT805 Màu Xám Muối Tiêu', 'Chất liệu: 100% Cotton 4 chiều.\r\nForm: Slimfit', 'http://shoptwestside.000webhostapp.com/upload/ao-polo-9.jpg', 'M', 56, 250000, 2),
(10, 'Áo Thun Polo AT805 Màu Xám Muối Tiêu', 'Chất liệu: 100% Cotton 4 chiều.\r\nForm: Slimfit', 'http://shoptwestside.000webhostapp.com/upload/ao-polo-10.jpg', 'M', 70, 250000, 2),
(11, 'Áo Thun Polo AT805 Màu Đen Muối Tiêu', 'Chất liệu: 100% Cotton 4 chiều.\r\nForm: Slimfit', 'http://shoptwestside.000webhostapp.com/upload/ao-polo-11.jpg', 'M', 70, 250000, 2),
(12, 'Áo Thun Polo AT805 Màu Xanh Da Trời', 'Chất liệu: 100% Cotton 4 chiều.\r\nForm: Slimfit', 'http://shoptwestside.000webhostapp.com/upload/ao-polo-12.jpg', 'M', 70, 250000, 2),
(13, 'Áo Thun Polo Tay Bo Phối AT849 Màu Trắng', 'Chất liệu: 99% cotton, 1% Spandex.\r\nForm: Slimfit', 'http://shoptwestside.000webhostapp.com/upload/ao-polo-13.jpg', 'XL', 80, 250000, 2),
(14, 'Áo Thun Polo Phối Màu Thêu AT864 Màu Xám\r\n', 'Chất liệu: 99% cotton, 1% Spandex.\r\nForm: Slimfit', 'http://shoptwestside.000webhostapp.com/upload/ao-polo-14.jpg', 'XL', 80, 250000, 2),
(15, 'Áo Polo Ngực In Sọc PO010 Màu Đen\r\n\r\n', 'Chất liệu: Cá mập 100% cotton tiêu chuẩn xuất khẩu.\r\nForm: Slimfit', 'http://shoptwestside.000webhostapp.com/upload/ao-polo-15.jpg', 'XL', 92, 250000, 2),
(16, 'Áo Len Sọc Đứng AL004 Màu Xám', ' Chất liệu: 15% angora, 50% viscose và 35% nylon.\r\nForm: Slimfit', 'http://shoptwestside.000webhostapp.com/upload/ao-thun-1.jpg', 'M', 75, 250000, 3),
(17, ' Áo Len Sọc Đứng AL004 Màu Nâu', ' Chất liệu: 15% angora, 50% viscose và 35% nylon.\r\nForm: Slimfit', 'http://shoptwestside.000webhostapp.com/upload/ao-thun-2.jpg', 'M', 75, 250000, 3),
(18, 'Áo Len Sọc Đứng AL004 Màu Nâu Vàng', ' Chất liệu: 15% angora, 50% viscose và 35% nylon.\r\nForm: Slimfit', 'http://shoptwestside.000webhostapp.com/upload/ao-thun-3.jpg', 'XL', 85, 250000, 3),
(19, 'Áo Len Sọc Đứng AL004 Màu Nâu Nhạt', 'Chất liệu: 15% angora, 50% viscose và 35% nylon.\r\nForm: Slimfit', 'http://shoptwestside.000webhostapp.com/upload/ao-thun-4.jpg', 'XL', 85, 250000, 3),
(20, ' Áo Len Sọc Đứng AL004 Màu Đỏ', ' Chất liệu: Len mi-lăng\r\nForm: Slimfit', 'http://shoptwestside.000webhostapp.com/upload/ao-thun-5.jpg', 'XL', 92, 250000, 3),
(21, ' Áo Len Cổ Lọ Trơn Màu Xanh Đen AL124', ' Chất liệu: Len trơn\r\nForm: Slimfit', 'http://shoptwestside.000webhostapp.com/upload/ao-thun-6.jpg', 'M', 85, 250000, 3),
(22, ' Áo Len Cổ Tim Đỏ AL120', ' Chất liệu: Len trơn\r\nForm: Slimfit', 'http://shoptwestside.000webhostapp.com/upload/ao-thun-7.jpg', 'XL', 96, 250000, 3),
(23, ' Áo Len Màu Rêu AL123\r\n', ' Chất liệu: Len trơn\r\nForm: Slimfit', 'http://shoptwestside.000webhostapp.com/upload/ao-thun-8.jpg', 'M', 32, 250000, 3),
(24, ' Áo Len Cổ Tim Vàng Kem AL81\r\n', ' Chất liệu: Len trơn\r\nForm: Slimfit', 'http://shoptwestside.000webhostapp.com/upload/ao-thun-9.jpg', 'M', 68, 250000, 3),
(25, ' Áo thun Cổ Tim Xanh rêu AL82\r\n', ' Chất liệu: Len trơn\r\nForm: Slimfit', 'http://shoptwestside.000webhostapp.com/upload/ao-thun-10.jpg', 'L', 78, 250000, 3),
(26, ' Áo Thun Phối Đắp AT032 Màu Xanh\r\n', ' Chất liệu: Compact Single Jersey 100% Cotton \r\nForm: Regular', 'http://shoptwestside.000webhostapp.com/upload/ao-thun-11.jpg', 'L', 37, 250000, 3),
(27, ' Áo Thun Phối Đắp AT032 Màu Xanh Dương\r\n', ' Chất liệu: Compact Single Jersey 100% Cotton \r\nForm: Regular', 'http://shoptwestside.000webhostapp.com/upload/ao-thun-12.jpg', 'L', 37, 250000, 3),
(28, ' Áo Thun Acid Wash AT028 Màu Xanh', ' Chất liệu: Compact Single Jersey 100% Cotton.\r\nForm: Regular', 'http://shoptwestside.000webhostapp.com/upload/ao-thun-13.jpg', 'L', 92, 250000, 3),
(29, ' Áo Thun Acid Wash AT028 Màu Xanh Dương', ' Chất liệu: Compact Single Jersey 100% Cotton.\r\nForm: Regular', 'http://shoptwestside.000webhostapp.com/upload/ao-thun-14.jpg', 'L', 92, 250000, 3),
(30, ' Áo Thun Acid Wash AT028 Màu Vàng', ' Chất liệu: Compact Single Jersey 100% Cotton.\r\nForm: Regular', 'http://shoptwestside.000webhostapp.com/upload/ao-thun-15.jpg', 'L', 48, 250000, 3),
(31, ' Áo Thun Ba Lỗ In The Blue AT011 Màu Trắng', ' Chất liệu: 100% cotton. \r\nForm: Regular', 'http://shoptwestside.000webhostapp.com/upload/ao-phong-1.jpg', 'XL', 26, 250000, 1),
(32, ' Áo Phông In The Summer AT016 Màu Trắng ', 'Chất liệu: 100% cotton. \r\nForm: Loose ', 'http://shoptwestside.000webhostapp.com/upload/ao-phong-2.jpg', 'L', 36, 250000, 1),
(33, ' Áo Phông Ngực In Chữ PO001 Màu Đen', ' Chất liệu: 98% cotton, 2% spandex\r\nForm: Slimfit', 'http://shoptwestside.000webhostapp.com/upload/ao-phong-3.jpg', 'XL', 56, 250000, 1),
(34, ' Áo Phông Rã Phối Form Loose AT010 Màu Xanh', ' Chất liệu: 100% cotton. \r\nForm: Loose ', 'http://shoptwestside.000webhostapp.com/upload/ao-phong-4.jpg', 'M', 26, 250000, 1),
(35, ' Áo Phông Phối Họa Tiết AT012 Màu Đen', ' Chất liệu: 100% cotton. \r\nForm: Loose', 'http://shoptwestside.000webhostapp.com/upload/ao-phong-5.jpg', 'M', 92, 250000, 1),
(36, ' Áo Phông In Chữ Form Loose AT009 Màu Đen', ' Chất liệu: 100% cotton. \r\nForm: Loose', 'http://shoptwestside.000webhostapp.com/upload/ao-phong-6.jpg', 'XL', 26, 250000, 1),
(37, ' Áo Phông In Sun Of The Beach AT008 Màu Đen', ' Chất liệu: 100% cotton. \r\nForm: Regular', 'http://shoptwestside.000webhostapp.com/upload/ao-phong-7.jpg', 'L', 132, 250000, 1),
(38, ' Áo Phông In Cactus Form Regular AT007 Màu Đen', ' Chất liệu: 100% cotton. \r\nForm: Regular', 'http://shoptwestside.000webhostapp.com/upload/ao-phong-8.jpg', 'XL', 156, 250000, 1),
(39, ' Áo Phông Cổ Bo Form Regular AT015 Màu Đen', ' Chất liệu: 98%cotton, 2% spandex.\r\nForm: Regular', 'http://shoptwestside.000webhostapp.com/upload/ao-phong-9.jpg', 'XL', 176, 250000, 1),
(40, ' Áo Phông Sọc Ngang Form Regular AT003 Màu Đỏ\r\n', ' Chất liệu: 100% cotton. \r\nForm: Regular', 'http://shoptwestside.000webhostapp.com/upload/ao-phong-10.jpg', 'L', 127, 250000, 1),
(41, ' Áo Phông In Summer Time AT002 Màu Trắng\r\n', ' Chất liệu: 100% cotton. \r\nForm: Regular', 'http://shoptwestside.000webhostapp.com/upload/ao-phong-11.jpg', 'M', 46, 250000, 1),
(42, ' Áo Phông Phối Họa Tiết AT012 Màu Đen\r\n', ' Chất liệu: 100% cotton. \r\nForm: Loose', 'http://shoptwestside.000webhostapp.com/upload/ao-phong-12.jpg', 'M', 26, 250000, 1),
(43, ' Áo Phông Sọc Ngang Ngực In Chữ AT001 Màu Xanh Đen\r\n', ' Chất liệu: 100% cotton. \r\nForm: Slim-fit', 'http://shoptwestside.000webhostapp.com/upload/ao-phong-13.jpg', 'XL', 142, 250000, 1),
(44, ' Áo Phông Căn Bản Form Loose AT014 Màu Xanh Đen\r\n', ' Chất liệu: 100% cotton. \r\nForm: Loose.', 'http://shoptwestside.000webhostapp.com/upload/ao-phong-14.jpg', 'XL', 126, 250000, 1),
(45, ' Áo Phông Căn Bản Form Regular AT004 Màu Xanh Đen\r\n', ' Chất liệu: 100% cotton. \r\nForm: Regular', 'http://shoptwestside.000webhostapp.com/upload/ao-phong-15.jpg', 'XL', 326, 250000, 1);

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
(1, 'Khánh Thi', '1', 'khanhthi@gmail.com', 'admin'),
(2, 'Đức Thiện', '1', 'nguyenthienducthien@gmail.com', 'admin');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD KEY `idsp` (`idsp`),
  ADD KEY `iddh` (`iddh`);

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`iddh`),
  ADD KEY `idtk` (`idtk`),
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
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `iddh` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `idkh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `idloaisp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `idsp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `idtk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD CONSTRAINT `chitietdonhang_ibfk_1` FOREIGN KEY (`idsp`) REFERENCES `sanpham` (`idsp`),
  ADD CONSTRAINT `chitietdonhang_ibfk_2` FOREIGN KEY (`iddh`) REFERENCES `donhang` (`iddh`);

--
-- Các ràng buộc cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD CONSTRAINT `donhang_ibfk_1` FOREIGN KEY (`idtk`) REFERENCES `taikhoan` (`idtk`),
  ADD CONSTRAINT `donhang_ibfk_2` FOREIGN KEY (`idkh`) REFERENCES `khachhang` (`idkh`);

--
-- Các ràng buộc cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `sanpham_ibfk_1` FOREIGN KEY (`idloaisp`) REFERENCES `loaisanpham` (`idloaisp`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
