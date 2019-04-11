
drop table MemberPromotion;
drop table TotalPromotion;
drop table CombinePromotion;

create table MemberPromotion(
  dayId integer,

  createTime bigint,
  lastModifiedTime bigint,
  beginTime bigint,
  endTime bigint,

  comment varchar(150),
  promotionState integer,

  requiredLevel integer,
  discountFraction double,
  tokenAmount double,
  gifts varchar(1000)
) character set = utf8;

create table Log(
logId integer,
createTime bigint,
username varchar(100),
userCategory integer,
eventCategory integer,
event varchar(100)
) character set = utf8;

create table TotalPromotion(
  dayId integer,

  createTime bigint,
  lastModifiedTime bigint,
  beginTime bigint,
  endTime bigint,

  comment varchar(150),
  promotionState integer,

  requiredTotal double,
  tokenAmount double,
  gifts varchar(1000)
) character set = utf8;



create table CombinePromotion(
  dayId integer,

  createTime bigint,
  lastModifiedTime bigint,
  beginTime bigint,
  endTime bigint,

  comment varchar(150),
  promotionState integer,

  discountAmount double,

  Combination varchar(1000)
) character set = utf8;

create table SalesSellReceipt(
  dayId integer,
  operatorId integer,
  createTime bigint,
  lastModifiedTime bigint,
  receiptState integer,
  memberId integer,
  clerkName varchar(30),
  stockName varchar(30),
  goodsList varchar(1000),
  discountAmount double,
  tokenAmount double,
  originSum double,

  gifts varchar(1000),
  giveTokenAmount double,
  comment varchar(150)

) character set = utf8;


create table account(
    ID int auto_increment primary key,
    name varchar(255),
    balance double
)character set = utf8;


create table PaymentBillReceipt(
    dayId integer,
    operatorId integer,
    createTime bigint,
    lastModifiedTime bigint,
    receiptState integer,

    clientId integer,

    accountID integer,
    transferList varchar(1000),
    sum double
)character set = utf8;

create table ChargeBillReceipt(
    dayId integer,
    operatorId integer,
    createTime bigint,
    lastModifiedTime bigint,
    receiptState integer,

    clientId integer,
    transferList varchar(1000),
    sum double
)character set = utf8;

create table CashBillReceipt(
    dayId integer,
    operatorId integer,
    createTime bigint,
    lastModifiedTime bigint,
    receiptState integer,


    accountId INTEGER,
    total double,
    itemList varchar(1000)
)character set = utf8;


create table SalesDetail(
    date bigint,
    goodsName varchar(30),
    goodsID integer,
    number integer,
    price double,
    total double,

    clientID integer,
    clerkName varchar(30),
    stockID integer
);


create table BusinessCondition(
    date bigint,
    salesIncome double,
    overFlowIncome double,
    purPriceAdjustIncome double,
    priceDiffIncome double,
    tokenIncome double,

    discount double,

    purCost double,
    damageCost double,
    giftCost double
);

create table StockPurReceipt(
dayId integer,
createTime bigint,
lastModifiedTime bigint,
operatorId integer,
receiptState integer,
memberId integer,
stockName varchar(100),
goodsList varchar(1000),
originSum integer,
comment varchar(150)
)character set = utf8;


create table StockRetReceipt(
dayId integer,
createTime bigint,
lastModifiedTime bigint,
operatorId integer,
receiptState integer,
memberId integer,
stockName varchar(100),
goodsList varchar(1000),
originSum integer,
comment varchar(150)
)character set = utf8;

create table SalesRetReceipt(
  dayId integer,
  operatorId integer,
  createTime bigint,
  lastModifiedTime bigint,
  receiptState integer,
  memberId integer,
  clerkName varchar(30),
  stockName varchar(30),
  goodsList varchar(1000),
  discountAmount double,
  tokenAmount double,
  originSum double,
comment varchar(150)
) character set = utf8;

create table User(
  userId integer,
  username varchar(30),
  image MEDIUMBLOB,
  usertype integer DEFAULT 0,
  createTime bigint,
  facebook varchar(50),
  github varchar(100),
  twitter varchar(100),
  email varchar(100),
  phone varchar(100),
  comment varchar(100),
  password varchar(100),
  isDeleted integer
)character set = utf8;

create table Member(
memberId integer,
memberCategory integer,
image MEDIUMBLOB,
VIPgrade integer,
memberName varchar(100),
clerkName varchar(100),
phoneNumber varchar(100),
address varchar(100),
zipCode varchar(100),
emailAddress varchar(100),
debtCeiling double,
debt double,
credit double,
isDeleted integer,
comment varchar(100)
)character set = utf8;

insert into Member (memberId,memberCategory,VIPgrade,memberName,clerkName,phoneNumber,address,zipCode,emailAddress,debtCeiling,debt,credit,isDeleted,comment)
values (5,0,4,'林冰','小王','139000013313','四明街525号','00013','lb131311@qq.com',1004,300,273,0,'常客');
insert into Member (memberId,memberCategory,VIPgrade,memberName,clerkName,phoneNumber,address,zipCode,emailAddress,debtCeiling,debt,credit,isDeleted,comment)
values (6,0,2,'连奚','小王','139206612365','四明街613号','00013','lx131rrr@sina.com',521,210,101,0,'无');
insert into Member (memberId,memberCategory,VIPgrade,memberName,clerkName,phoneNumber,address,zipCode,emailAddress,debtCeiling,debt,credit,isDeleted,comment)
values (7,0,1,'若欣','小王','139000013313','贝尔街525号','00014','rx1390013@qq.com',136,200,4,0,'无');
insert into Member (memberId,memberCategory,VIPgrade,memberName,clerkName,phoneNumber,address,zipCode,emailAddress,debtCeiling,debt,credit,isDeleted,comment)
values (8,0,4,'李若冰','小王','139000013313','贝尔街5号','00014','lrb--139000@qq.com',204,300,123,0,'无');
insert into Member (memberId,memberCategory,VIPgrade,memberName,clerkName,phoneNumber,address,zipCode,emailAddress,debtCeiling,debt,credit,isDeleted,comment)
values (9,1,4,'盛利冰','小李','139000013313','贝尔街13号','00014','lsbqqq11@qq.com',114,300,912,0,'无');
insert into Member (memberId,memberCategory,VIPgrade,memberName,clerkName,phoneNumber,address,zipCode,emailAddress,debtCeiling,debt,credit,isDeleted,comment)
values (10,1,4,'王水','小李','139000013313','思明区小明街32号','00032','lb1zxc311@qq.com',190,300,166,0,'无');
insert into Member (memberId,memberCategory,VIPgrade,memberName,clerkName,phoneNumber,address,zipCode,emailAddress,debtCeiling,debt,credit,isDeleted,comment)
values (11,1,4,'王青花','小李','139000013313','思明区小明街55号','00032','wqh1311@qq.com',588,300,213,0,'无');
insert into Member (memberId,memberCategory,VIPgrade,memberName,clerkName,phoneNumber,address,zipCode,emailAddress,debtCeiling,debt,credit,isDeleted,comment)
values (12,1,5,'陈小','小李','139000013313','若林街99号','00010','cx131311@qq.com',912,300,271,0,'常客');
insert into Member (memberId,memberCategory,VIPgrade,memberName,clerkName,phoneNumber,address,zipCode,emailAddress,debtCeiling,debt,credit,isDeleted,comment)
values (13,0,4,'伏请','小李','139000013313','四明街25号','00013','fq1adw311@qq.com',1500,300,531,0,'常客');


insert into SalesSellReceipt(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId, clerkName, stockName, discountAmount, tokenAmount, originSum, comment)
values(1,1,1515788527089,1515788527089,2,5,'小王','阳光仓库',0,0,0,'无');
insert into SalesSellReceipt(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId, clerkName, stockName, discountAmount, tokenAmount, originSum, comment)
values(2,1,1515788527089,1515788527089,2,11,'小李','阳光仓库',0,0,0,'无');
insert into SalesSellReceipt(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId, clerkName, stockName, discountAmount, tokenAmount, originSum, comment)
values(3,1,1515788527089,1515788527089,2,5,'小李','阳光仓库',0,0,0,'无');
insert into SalesSellReceipt(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId, clerkName, stockName, discountAmount, tokenAmount, originSum, comment)
values(4,1,1515788527089,1515788527089,2,5,'小李','盛华仓库',0,0,0,'无');
insert into SalesSellReceipt(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId, clerkName, stockName, discountAmount, tokenAmount, originSum, comment)
values(5,1,1515788527089,1515788527089,2,13,'小李','阳光仓库',0,0,0,'无');


insert into SalesRetReceipt(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId, clerkName, stockName, discountAmount, tokenAmount, originSum, comment)
values(1,1,1515788527089,1515788527089,2,5,'小王','阳光仓库',0,0,0,'无');
insert into SalesRetReceipt(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId, clerkName, stockName, discountAmount, tokenAmount, originSum, comment)
values(2,1,1515788527089,1515788527089,2,11,'小李','阳光仓库',0,0,0,'无');
insert into SalesRetReceipt(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId, clerkName, stockName, discountAmount, tokenAmount, originSum, comment)
values(3,1,1515788527089,1515788527089,3,5,'小李','阳光仓库',0,0,0,'无');
insert into SalesRetReceipt(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId, clerkName, stockName, discountAmount, tokenAmount, originSum, comment)
values(4,1,1515788527089,1515788527089,2,5,'小李','盛华仓库',0,0,0,'无');
insert into SalesRetReceipt(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId, clerkName, stockName, discountAmount, tokenAmount, originSum, comment)
values(5,1,1515788527089,1515788527089,2,10,'小李','阳光仓库',0,0,0,'无');


insert into StockPurReceipt(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId,stockName, originSum, comment)
values(4,1,1515788527090,1515788527090,2,5,'盛华仓库',0,'无');
insert into StockPurReceipt(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId,stockName, originSum, comment)
values(5,1,1515788527090,1515788527090,1,10,'盛华仓库',0,'无');
insert into StockPurReceipt(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId,stockName, originSum, comment)
values(6,1,1515788527090,1515788527090,1,10,'盛华仓库',0,'无');
insert into StockPurReceipt(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId,stockName, originSum, comment)
values(7,1,1515788527090,1515788527090,1,10,'盛华仓库',0,'无');
insert into StockPurReceipt(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId,stockName, originSum, comment)
values(8,1,1515788527090,1515788527090,3,5,'盛华仓库',0,'无');


insert into StockRetReceipt(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId,stockName, originSum, comment)
values(1,1,1515788527090,1515788527090,1,5,'盛华仓库',0,'无');
insert into StockRetReceipt(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId,stockName, originSum, comment)
values(2,1,1515788527090,1515788527090,2,7,'盛华仓库',0,'无');
insert into StockRetReceipt(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId,stockName, originSum, comment)
values(3,1,1515788527090,1515788527090,3,7,'盛华仓库',0,'无');
insert into StockRetReceipt(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId,stockName, originSum, comment)
values(4,1,1515788527090,1515788527090,2,5,'盛华仓库',0,'无');
insert into StockRetReceipt(dayId, operatorId, createTime, lastModifiedTime, receiptState, memberId,stockName, originSum, comment)
values(5,1,1515788527090,1515788527090,3,5,'盛华仓库',0,'无');

