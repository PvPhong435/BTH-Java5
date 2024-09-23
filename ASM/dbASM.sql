CREATE DATABASE JAVA5_NHOM7
GO

use JAVA5_NHOM7
go

CREATE TABLE KhachHang (
    MaKhachHang INT NOT NULL IDENTITY(1,1),
    HoTen NVARCHAR(100) NOT NULL ,
    Email NVARCHAR(100) UNIQUE NULL,
    SoDienThoai VARCHAR (15) NOT NULL ,
    DiaChi NVARCHAR(255) NULL,
    Username VARCHAR(50) UNIQUE NOT NULL ,
    Password VARCHAR(255) NOT NULL ,
	primary key(MaKhachHang),

);

GO

--Nâng Cao 
--CREATE TABLE ChiNhanh(
--	MaChiNhanh VARCHAR(50), 
--	TenChiNhanh NVARCHAR(50),
--	DiaChi NVARCHAR(100),
--	SDT VARCHAR(15),
--	PRIMARY KEY(MaChiNhanh)
--);
--GO

CREATE TABLE SanCau (
    MaSan INT NOT NULL IDENTITY(1,1),
    TenSan NVARCHAR(100) NOT NULL,
    LoaiSan VARCHAR(50) NOT NULL, 
    GiaSan DECIMAL(10, 2) NOT NULL, 
    MoTa NVARCHAR(100), 
    DiaChi VARCHAR(255), 
    TrangThai BIT DEFAULT 0, -- mặc định khi tạo là 0 là sân còn trống, 1 là đã đặt,
	--MaChiNhanh VARCHAR(50), -- Nâng Cao
	PRIMARY KEY(MaSan)
);

GO


CREATE TABLE DatSanCau (
    MaDatSan INT NOT NULL IDENTITY(1,1),
    MaKhachHang INT,
    MaSan INT,
    NgayDat DATE,
    GioBatDau TIME,
    GioKetThuc TIME,
	TongThoiGianChoi INT DEFAULT 0, -- Tính tổng thời gian chơi, đơn vị là phút
    TongTien DECIMAL(10, 2), -- Tổng tiền cho lượt đặt sân
    TrangThaiDat VARCHAR(50), -- Trạng thái đặt: 'Đang xử lý', 'Đã thanh toán', 'Đã hủy'
	PRIMARY KEY(MaDatSan),
    FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang),
    FOREIGN KEY (MaSan) REFERENCES SanCau(MaSan)
);

GO

CREATE TABLE NhanVien (
    MaNhanVien VARCHAR(50) ,
    HoTen VARCHAR(100),
	ChucVu NVARCHAR(50),
    Username VARCHAR(50) UNIQUE,
    Password VARCHAR(255),
	PRIMARY KEY(MaNhanVien)
);

GO

CREATE TABLE HoaDonDatSan (
    SoHoaDon INT NOT NULL IDENTITY(1,1),
    MaDatSan INT,
    NgayThanhToan DATE,
    TongTien DECIMAL(10, 2),
    TrangThaiThanhToan BIT DEFAULT 0, -- 1 là đã thanh toán, 0 là chưa thanh toán
	GiamGia DECIMAL(10, 2), -- kiểm tra xem hóa đơn được giảm giá bao nhiêu tiền
	MaNhanVien VARCHAR(50),
	PRIMARY KEY(SoHoaDon),
    FOREIGN KEY (MaDatSan) REFERENCES DatSanCau(MaDatSan),
	FOREIGN KEY(MaNhanVien) REFERENCES NhanVien(MaNhanVien)
);

go


CREATE TABLE UuDai(
	MaUuDai INT NOT NULL IDENTITY(1,1),
	NgayBatDau DATE,
	NgayKetThuc DATE,
	PhanTramGiam DECIMAL(5, 2),
	DieuKienGiam NVARCHAR(100),
	PRIMARY KEY(MaUuDai)
);

GO

CREATE TABLE LoaiSanPham(
	MaLoai VARCHAR(50),
	TenLoai NVARCHAR(100),
	PRIMARY KEY(MaLoai)
);


GO

--Phần Buôn Bán Sản Phẩm

CREATE TABLE SanPham(
	MaSanPham VARCHAR(50),
	TenSanPham NVARCHAR(100),
	DonGia DECIMAL(10, 2),
	MaLoai VARCHAR(50),
	MoTa NVARCHAR(100),
	PRIMARY KEY(MaSanPham),
	FOREIGN KEY(MaLoai) REFERENCES LoaiSanPham(MaLoai)
);

GO

CREATE TABLE HinhSanPham(
	MaSanPham  VARCHAR(50),
	DuongDanHinh NVARCHAR(100),
	PRIMARY KEY(MaSanPham,DuongDanHinh),
	FOREIGN KEY(MaSanPham) REFERENCES SanPham(MaSanPham)
);
go

CREATE TABLE Kho(
	MaSanPham VARCHAR(50),
	SoLuongTon INT DEFAULT 0, --mặc định khi tạo tồn kho là 0
	GhiChu NVARCHAR(100),
	PRIMARY KEY(MaSanPham),
	FOREIGN KEY(MaSanPham) REFERENCES SanPham(MaSanPham)
);

GO

CREATE TABLE PhieuNhap(
	SoPhieuNhap INT IDENTITY(1,1),
	NgayNhap DATE,
	TongTien DECIMAL(10, 2),
	MaNhanVien VARCHAR(50),
	PRIMARY KEY(SoPhieuNhap),
	FOREIGN KEY(MaNhanVien) REFERENCES NhanVien(MaNhanVien)
);

GO

CREATE TABLE ChiTietPhieuNhap(
	SoPhieuNhap INT,
	MaSanPham VARCHAR(50),
	SoLuong int,
	GhiChu NVARCHAR(100),
	PRIMARY KEY(SoPhieuNhap),
	FOREIGN KEY(SoPhieuNhap) REFERENCES PhieuNhap(SoPhieuNhap),
	FOREIGN KEY(MaSanPham) REFERENCES SanPham(MaSanPham),
);

GO

CREATE TABLE HoaDonSanPham(
	SoHoaDon INT IDENTITY(1,1) ,
	NgayDat DATE,
	TongTien INT,
	TrangThaiDat VARCHAR(50),
	MaKhachHang INT,
	NhanVienLap VARCHAR(50),
	PRIMARY KEY(SoHoaDon),
	FOREIGN KEY(MaKhachHang) REFERENCES KhacHHang(MaKhachHang),
	FOREIGN KEY(NhanVienLap) REFERENCES NhanVien(MaNhanVien)
);

GO

CREATE TABLE ChiTietHoaDonSanPham(
	SoHoaDon INT,
	MaSanPham VARCHAR(50),
	SoLuong INT,
	GhiChu NVARCHAR(100),
	PRIMARY KEY(SoHoaDon),
	FOREIGN KEY(MaSanPham) REFERENCES SanPham(MaSanPham),
	FOREIGN KEY(SoHoaDon) REFERENCES HoaDonSanPham(SoHoaDon)

);



-- Nhập dữ liệu
GO

INSERT INTO KhachHang (HoTen, Email, SoDienThoai, DiaChi, Username, Password)
VALUES 
('Nguyễn Văn An', 'nguyenvana@gmail.com', '0123456789', '123 Lê Lợi, Hà Nội', 'nguyenvana', 'password123'),
('Trần Thị Bích', 'tranthibich@gmail.com', '0987654321', '456 Nguyễn Trãi, Hà Nội', 'tranthib', 'password456'),
('Lê Văn Cường', 'levancuong@gmail.com', '0912345678', '789 Lê Thanh Nghị, Hà Nội', 'levanc', 'password789'),
('Phạm Thị Dung', 'phamthidung@gmail.com', '0909123456', '12 Phố Huế, Hà Nội', 'phamthidung', 'password000'),
('Đỗ Văn Hải', 'dovanhai@gmail.com', '0938456723', '34 Hai Bà Trưng, Hà Nội', 'dovanhai', 'passwordhai'),
('Vũ Thị Hương', 'vuthihuong@gmail.com', '0912346789', '56 Tôn Đức Thắng, Hà Nội', 'vuthihuong', 'passwordhuong'),
('Ngô Văn Thành', 'ngovanthanh@gmail.com', '0923456789', '78 Nguyễn Du, Hà Nội', 'ngovanthanh', 'passwordthanh');

GO

INSERT INTO NhanVien (MaNhanVien, HoTen, ChucVu, Username, Password)
VALUES 
('NV001', 'Nguyễn Văn Dũng', 'Quản lý', 'nguyenvandung', 'passdung123'),
('NV002', 'Trần Thị Lan', 'Nhân viên', 'tranthilan', 'passlan456'),
('NV003', 'Lê Văn Hải', 'Nhân viên', 'levanhai', 'passhai789'),
('NV004', 'Phạm Quốc Huy', 'Nhân viên', 'phamquochuy', 'passhuy000'),
('NV005', 'Ngô Thị Mai', 'Nhân viên', 'ngothimai', 'passmai111'),
('NV006', 'Vũ Hồng Sơn', 'Nhân viên', 'vuhongson', 'passson222'),
('NV007', 'Đỗ Thanh Bình', 'Nhân viên', 'dothanhbinh', 'passbinh333');

GO

INSERT INTO SanCau (TenSan, LoaiSan, GiaSan, MoTa, DiaChi, TrangThai)
VALUES 
('Sân A', 'Sân Cao Cấp', 250000, 'Sân thích hợp cho giải đấu', '123 Lê Lợi, Hà Nội', 0),
('Sân B', 'Sân Thường', 200000, 'Sân cho người chơi trung bình', '456 Nguyễn Trãi, Hà Nội', 0),
('Sân C', 'Sân Phổ Thông', 150000, 'Sân cho người mới chơi', '789 Lê Thanh Nghị, Hà Nội', 0),
('Sân D', 'Sân VIP', 300000, 'Sân cho giải đấu chuyên nghiệp', '12 Phố Huế, Hà Nội', 0),
('Sân E', 'Sân Cao Cấp', 250000, 'Sân chuẩn quốc tế', '34 Hai Bà Trưng, Hà Nội', 1),
('Sân F', 'Sân Thường', 180000, 'Sân ngoài trời', '56 Tôn Đức Thắng, Hà Nội', 0),
('Sân G', 'Sân Phổ Thông', 130000, 'Sân cho người chơi bình dân', '78 Nguyễn Du, Hà Nội', 0);

GO

INSERT INTO DatSanCau (MaKhachHang, MaSan, NgayDat, GioBatDau, GioKetThuc, TongThoiGianChoi, TongTien, TrangThaiDat)
VALUES 
(1, 1, '2024-09-10', '08:00', '10:00', 120, 500000, 'Đã thanh toán'),
(2, 2, '2024-09-12', '14:00', '16:00', 120, 400000, 'Đang xử lý'),
(3, 3, '2024-09-13', '09:00', '11:00', 120, 300000, 'Đã thanh toán'),
(4, 4, '2024-09-14', '17:00', '19:00', 120, 600000, 'Đã thanh toán'),
(5, 5, '2024-09-15', '08:00', '10:00', 120, 500000, 'Đang xử lý'),
(6, 6, '2024-09-16', '18:00', '20:00', 120, 360000, 'Đã thanh toán'),
(7, 7, '2024-09-17', '15:00', '17:00', 120, 260000, 'Đã thanh toán');

GO

INSERT INTO HoaDonDatSan (MaDatSan, NgayThanhToan, TongTien, TrangThaiThanhToan, GiamGia, MaNhanVien)
VALUES 
(1, '2024-09-10', 500000, 1, 50000, 'NV001'),
(2, '2024-09-12', 400000, 0, 0, 'NV002'),
(3, '2024-09-13', 300000, 1, 30000, 'NV003'),
(4, '2024-09-14', 600000, 1, 60000, 'NV004'),
(5, '2024-09-15', 500000, 0, 0, 'NV005'),
(6, '2024-09-16', 360000, 1, 36000, 'NV006'),
(7, '2024-09-17', 260000, 1, 26000, 'NV007');

GO

INSERT INTO UuDai (NgayBatDau, NgayKetThuc, PhanTramGiam, DieuKienGiam)
VALUES 
('2024-09-01', '2024-09-30', 10, 'Giảm giá cho hóa đơn trên 500000 VND'),
('2024-10-01', '2024-10-31', 15, 'Giảm giá cho khách hàng VIP'),
('2024-11-01', '2024-11-30', 20, 'Giảm giá cho khách hàng đặt sân lần thứ 5'),
('2024-12-01', '2024-12-31', 5, 'Giảm giá cho hóa đơn thanh toán trong ngày'),
('2024-12-15', '2024-12-31', 10, 'Giảm giá mừng Giáng sinh'),
('2024-01-01', '2024-01-31', 10, 'Giảm giá chào năm mới'),
('2024-02-01', '2024-02-28', 20, 'Giảm giá cho khách hàng đặt sân trước Tết');

GO

INSERT INTO LoaiSanPham (MaLoai, TenLoai)
VALUES 
('L001', 'Vợt Cầu Lông'),
('L002', 'Quả Cầu Lông'),
('L003', 'Quần Áo Cầu Lông'),
('L004', 'Giày Cầu Lông'),
('L005', 'Phụ Kiện Cầu Lông'),
('L006', 'Túi Đựng Dụng Cụ'),
('L007', 'Balo Thể Thao');

GO

INSERT INTO SanPham (MaSanPham, TenSanPham, DonGia, MaLoai, MoTa)
VALUES 
('SP001', 'Vợt Cầu Lông Yonex', 1500000, 'L001', 'Vợt chuyên nghiệp cho người chơi lâu năm'),
('SP002', 'Vợt Cầu Lông Lining', 1200000, 'L001', 'Vợt thích hợp cho người mới chơi'),
('SP003', 'Quả Cầu Lông Yonex', 100000, 'L002', 'Quả cầu lông chất lượng cao'),
('SP004', 'Quần Áo Cầu Lông Adidas', 500000, 'L003', 'Bộ quần áo thể thao chất liệu thoáng mát'),
('SP005', 'Giày Cầu Lông Mizuno', 800000, 'L004', 'Giày thể thao chống trượt, bảo vệ cổ chân'),
('SP006', 'Balo Cầu Lông Yonex', 300000, 'L005', 'Balo đựng dụng cụ chơi cầu lông'),
('SP007', 'Túi Đựng Vợt Cầu Lông', 200000, 'L005', 'Túi đựng vợt chuyên dụng');

GO

INSERT INTO HinhSanPham (MaSanPham, DuongDanHinh)
VALUES 
('SP001', 'images/yonex_vot.png'),
('SP002', 'images/lining_vot.png'),
('SP003', 'images/yonex_cau.png'),
('SP004', 'images/adidas_quanao.png'),
('SP005', 'images/mizuno_giay.png'),
('SP006', 'images/yonex_balo.png'),
('SP007', 'images/tui_vot.png');

GO

INSERT INTO Kho (MaSanPham, SoLuongTon, GhiChu)
VALUES 
('SP001', 100, 'Còn hàng'),
('SP002', 150, 'Còn hàng'),
('SP003', 500, 'Còn nhiều'),
('SP004', 75, 'Còn hàng'),
('SP005', 60, 'Hàng mới về'),
('SP006', 120, 'Hàng bán chạy'),
('SP007', 80, 'Còn hàng');

GO

INSERT INTO PhieuNhap (NgayNhap, TongTien, MaNhanVien)
VALUES 
('2024-09-01', 2000000, 'NV001'),
('2024-09-02', 1500000, 'NV002'),
('2024-09-03', 1000000, 'NV003'),
('2024-09-04', 2500000, 'NV004'),
('2024-09-05', 1800000, 'NV005'),
('2024-09-06', 2200000, 'NV006'),
('2024-09-07', 1900000, 'NV007');

GO

INSERT INTO ChiTietPhieuNhap (SoPhieuNhap, MaSanPham, SoLuong, GhiChu)
VALUES 
(1, 'SP001', 50, 'Nhập mới'),
(2, 'SP002', 75, 'Nhập mới'),
(3, 'SP003', 200, 'Nhập số lượng lớn'),
(4, 'SP004', 30, 'Nhập lại do hết hàng'),
(5, 'SP005', 40, 'Nhập số lượng trung bình'),
(6, 'SP006', 60, 'Nhập mới'),
(7, 'SP007', 40, 'Nhập hàng thường xuyên');

GO

INSERT INTO HoaDonSanPham (NgayDat, TongTien, TrangThaiDat, MaKhachHang, NhanVienLap)
VALUES 
('2024-09-10', 3000000, 'Đã thanh toán', 1, 'NV001'),
('2024-09-11', 2000000, 'Đã thanh toán', 2, 'NV002'),
('2024-09-12', 1500000, 'Đã thanh toán', 3, 'NV003'),
('2024-09-13', 2500000, 'Đã thanh toán', 4, 'NV004'),
('2024-09-14', 1800000, 'Đang xử lý', 5, 'NV005'),
('2024-09-15', 2200000, 'Đã thanh toán', 6, 'NV006'),
('2024-09-16', 1900000, 'Đã thanh toán', 7, 'NV007');

GO

INSERT INTO ChiTietHoaDonSanPham (SoHoaDon, MaSanPham, SoLuong, GhiChu)
VALUES 
(1, 'SP001', 2, 'Mua vợt cầu lông Yonex'),
(2, 'SP002', 1, 'Mua vợt cầu lông Lining'),
(3, 'SP003', 10, 'Mua quả cầu lông Yonex'),
(4, 'SP004', 3, 'Mua quần áo cầu lông Adidas'),
(5, 'SP005', 1, 'Mua giày cầu lông Mizuno'),
(6, 'SP006', 2, 'Mua balo cầu lông Yonex'),
(7, 'SP007', 2, 'Mua túi đựng vợt cầu lông');



