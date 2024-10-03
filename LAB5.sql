CREATE DATABASE J5Shop 

GO

USE J5Shop


CREATE TABLE Categories
(
	Id int,
	Name NVARCHAR(50),
	PRIMARY KEY(Id)
);

GO

CREATE TABLE Accounts
(
	Username NVARCHAR(50),
	Password NVARCHAR(50),
	Fullname NVARCHAR(50),
	Email NVARCHAR(50),
	Photo NVARCHAR(50),
	Activated bit,
	Admin bit,
	PRIMARY KEY(Username)
);

GO

CREATE TABLE Orders
(
	Id int,
	Username NVARCHAR(50),
	CreateDate Datetime,
	Address NVARCHAR(100),
	PRIMARY KEY(Id),
	FOREIGN KEY(Username) REFERENCES Accounts(Username)
);

GO

CREATE TABLE Products
(
	Id int,
	Name NVARCHAR(50),
	Price float,
	CreateDate DATE,
	Available bit,
	CategoryId int,
	PRIMARY KEY(Id),
	FOREIGN KEY(CategoryId) REFERENCES Categories(Id)
);

GO

CREATE TABLE ProductImage
(
	product_id INT,
	image_link NVARCHAR(50),
	PRIMARY KEY(product_id,image_link),
	FOREIGN KEY(product_id) REFERENCES Products(Id)
);


GO

CREATE TABLE OrderDetails
(
	Id int,
	OrderId int,
	ProductId int,
	Price float,
	Quantity int,
	PRIMARY KEY(Id),
	FOREIGN KEY(OrderId) REFERENCES Orders(Id),
	FOREIGN KEY(ProductId) REFERENCES Products(Id)
);


GO

-- Thêm dữ liệu vào bảng Categories
INSERT INTO Categories (Id, Name)
VALUES 
(1, 'Smartphone'),
(2, 'Tablet'),
(3, 'Laptop');

GO

-- Thêm dữ liệu vào bảng Accounts
INSERT INTO Accounts (Username, Password, Fullname, Email, Photo, Activated, Admin)
VALUES 
('applefan01', 'password123', 'Nguyen Van A', 'applefan01@example.com', 'avatar1.png', 1, 0),
('applefan02', 'password456', 'Tran Thi B', 'applefan02@example.com', 'avatar2.png', 1, 0),
('applefan03', 'password789', 'Le Van C', 'applefan03@example.com', 'avatar3.png', 1, 0);

GO

-- Thêm dữ liệu vào bảng Products
INSERT INTO Products (Id, Name, Price, CreateDate, Available, CategoryId)
VALUES 
(1, 'iPhone 13', 999.99, '2023-01-01', 1, 1),
(2, 'iPhone 13 Pro', 1199.99, '2023-01-01', 1, 1),
(3, 'iPhone 14', 1099.99, '2023-06-01', 1, 1),
(4, 'iPhone 14 Pro Max', 1399.99, '2023-09-01', 1, 1),
(5, 'iPad Air', 599.99, '2023-02-15', 1, 2),
(6, 'iPad Pro 11"', 799.99, '2023-03-01', 1, 2),
(7, 'MacBook Air M2', 1199.99, '2023-04-01', 1, 3),
(8, 'MacBook Pro 14"', 1999.99, '2023-05-01', 1, 3);

GO

-- Thêm dữ liệu vào bảng ProductImage
INSERT INTO ProductImage 
VALUES 
(1, 'iphone13_front.jpg'),
(1, 'iphone13_back.jpg'),
(2, 'iphone13pro_front.jpg'),
(2, 'iphone13pro_back.jpg'),
(3, 'iphone14_front.jpg'),
(3, 'iphone14_back.jpg'),
(4, 'iphone14promax_front.jpg'),
(4, 'iphone14promax_back.jpg'),
(5, 'ipadair_front.jpg'),
(5, 'ipadair_back.jpg'),
(6, 'ipadpro11_front.jpg'),
(6, 'ipadpro11_back.jpg'),
(7, 'macbookairm2_front.jpg'),
(7, 'macbookairm2_side.jpg'),
(8, 'macbookpro14_front.jpg'),
(8, 'macbookpro14_side.jpg');

GO

-- Thêm dữ liệu vào bảng Orders
INSERT INTO Orders (Id, Username, CreateDate, Address)
VALUES 
(1, 'applefan01', '2024-09-25', '123 Apple Street, Cupertino'),
(2, 'applefan02', '2024-09-28', '456 Banana Avenue, San Francisco'),
(3, 'applefan03', '2024-09-30', '789 Cherry Lane, Los Angeles');

GO

-- Thêm dữ liệu vào bảng OrderDetails
INSERT INTO OrderDetails (Id, OrderId, ProductId, Price, Quantity)
VALUES 
(1, 1, 1, 999.99, 1),  -- applefan01 mua iPhone 13
(2, 1, 5, 599.99, 1),  -- applefan01 mua iPad Air
(3, 2, 3, 1099.99, 2),  -- applefan02 mua 2 chiếc iPhone 14
(4, 2, 6, 799.99, 1),  -- applefan02 mua iPad Pro 11"
(5, 3, 4, 1399.99, 1),  -- applefan03 mua iPhone 14 Pro Max
(6, 3, 8, 1999.99, 1);  -- applefan03 mua MacBook Pro 14"
