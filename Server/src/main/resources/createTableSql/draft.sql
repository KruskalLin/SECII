drop table PaymentBillReceipt;
drop table ChargeBillReceipt;
drop table CashBillReceipt;


create table initialMember(
year char(11),
memberId integer,
memberCategory integer ,
VIPgrade integer ,
memberName varchar(100),
clerkName varchar(100) ,
phoneNumber varchar(100) ,
address varchar(100),
zipCode varchar(100) ,
emailAddress varchar(100),
debtCeiling double,
debt double ,
credit double ,
isDeleted integer ,
comment varchar(100)
);

create table ReceiptMessage(
  id integer NOT NULL AUTO_INCREMENT,
  fromUserId integer,
  toUserId integer,
  toUserCategory integer,

  messagePurpose integer,

  createTime bigint,

  receiptDayId integer,
  receiptCreateTime bigint,
  billType integer,

  PRIMARY KEY (id)
) character set = utf8;

insert into User(userid, userName, usertype, password)
values (11, 'lianyuanxiang', 4, 123456);

insert into User(userid, userName, usertype, password)
values (12, 'liuhanyi', 0, 123456);

insert into User(userid, userName, usertype, password)
values (13, 'linyuchao', 2, 123456);

insert into User(userid, userName, usertype, password)
values (14, 'linpeng', 3, 123456);

insert into User(userid, userName, usertype, password)
values (1, '11111', 5, 11111);