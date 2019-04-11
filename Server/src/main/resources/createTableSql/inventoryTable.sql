drop table inventoryGiftRecipt;
drop table inventoryDamageRecipt;
drop table inventoryOverflowRecipt;
drop table inventoryWarningRecipt;

create table goods(
     id varchar(30),
     goodName varchar(30),
     goodType varchar(30),
     classifyId varchar(30),
     inventoryNum int unsigned,
     purPrice double(8,2) unsigned,
     salePrice double(8,2) unsigned,
     recentPurPrice double(8,2) unsigned,
     recentSalePrice double(8,2) unsigned,
     alarmNumber int,
     isStockPur bool
)character set = utf8;

create table goodsclassification(
     id varchar(20),
     _name varchar(20),
     fatherId varchar(20),
     childrenId varchar(1000),
     goodsId varchar(1000)
)character set = utf8;

create table inventoryGiftReceipt(
     dayId integer,
     operatorId integer,
     createTime bigint,
     lastModifiedTime bigint,
     receiptState integer,

     clerkName varchar(20),
     goodsList varchar(1000),
     comment varchar(150)
)character set = utf8;

create table inventoryDamageReceipt(
     dayId integer,
     operatorId integer,
     createTime bigint,
     lastModifiedTime bigint,
     receiptState integer,

     clerkName varchar(20),
     goodsList varchar(1000),
     comment varchar(150)
)character set = utf8;

create table inventoryOverflowReceipt(
     dayId integer,
     operatorId integer,
     createTime bigint,
     lastModifiedTime bigint,
     receiptState integer,

     clerkName varchar(20),
     goodsList varchar(1000),
     comment varchar(150)
)character set = utf8;

create table inventoryWarningReceipt(
     dayId integer,
     operatorId integer,
     createTime bigint,
     lastModifiedTime bigint,
     receiptState integer,

     clerkName varchar(20),
     goodsList varchar(1000),
     comment varchar(150)
)character set = utf8;