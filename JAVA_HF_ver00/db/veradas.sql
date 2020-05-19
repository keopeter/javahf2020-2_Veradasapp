drop database veradas;
create database veradas;  
use veradas;  
create table log(
	id int primary key auto_increment,
	uzenet nvarchar(50) not null
	);
    
create table donation(
	id int primary key auto_increment,
	
    datum date,
    dis int,
    sis int,
    hemo int,
    ok boolean

    );

    
    
    
insert into log(uzenet) values("Still here!");
insert into donation(datum, dis,sis,hemo,ok) values("2020. 03.31",80,120,134,true);
 