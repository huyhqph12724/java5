Create database Java5
Use Java5
Create table Users(
	username nvarchar(50) not null primary key,
	password varchar(50) ,
	fullname nvarchar(50) ,
	email varchar(50) ,
	photo varchar(50),
	active bit not null,
	admin bit not null)
Create table Category(
	id int not null primary key identity(1,1),
	name nvarchar(50) not null
)
Create table Product(
	id int not null primary key identity(1,1),
	name nvarchar(50) not null,
	image varchar(50),
	price float(30),
	createdate date,
	available bit,
	detail text,
	categoryid int not null foreign key references Category(id)
)
Create table Orders(
	id bigint not null primary key identity(1,1),
	username nvarchar(50) not null foreign key references Users(username),
	createdate datetime not null,
	address nvarchar(100),
	status bit
)
Create table OrderDetail(
	id bigint not null primary key identity(1,1),
	orderid bigint not null foreign key references Orders(id),
	productid int not null foreign key references Product(id),
	price float ,
	quantity int not null
)
Insert into Category values
(N'Iphone'),
(N'Samsung'),
(N'Oppo'),
(N'Xiaomi'),
(N'Realme')
Insert into Users values
('admin','123',N'Hoàng Huy','huyhqph12724@fpt.edu.vn','67944048_868043410241840_7669012368726163456_n.jpg',1,1),
('huy','123',N'Hoàng Huy','thusinhnhaque95@gmail.com','a.jpg',1,0)
Insert into Product values
('Iphone X','iphone-x-truoc-sau.jpg',1000,'2021-10-01',1,N'Apple iPhone X 64GB',1),
('Iphone 11 Pro Max','iphone-11-pro-max-mat-truoc-sau.jpg',1000,'2021-10-01',1,N'Apple iPhone 11 pro max 64GB',1),
('Iphone 12','iphone-12-pro-max-mat-truoc-sau.jpg',2000,'2021-10-01',1,N'Apple iPhone 12 pro max 128GB',1),
('SamSung S21','samsung-galaxy-s21-ultra-1_1_2.jpg','3000','2021-10-01',1,N'Samsung galaxy S21 Ultra',2),
('SamSung Note 21','samsung-note21-ultra.jpg',5000,'2021-10-01',1,N'Sammsung Galaxy Note 21 ultra',2),
('Xiaomi Mi 11','xiaomi-mi-11.jpg',2500,'2021-10-01',1,N'Điện thoại Xiaomi Mi 11 8Gb/128Gb',4),
('Redmi 10','xiaomi-redmi-10.jpg',3500,'2021-10-01',1,N'Smartphone Xiaomi Redmi 10 Ram 6Gb bộ nhớ 64Gb',4),
('Redmi Note 10 Pro','xiaomi-redmi-note-10-pro.jpg',5000,'2021-10-01',1,N'Smartphone Xiaomi Redmi Note 10 pro Ram 78 bộ nhớ 128GbGb',4)






