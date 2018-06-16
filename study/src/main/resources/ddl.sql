DROP TABLE IF EXISTS spring4fs.USERS;
create table spring4fs.USERS (
	userId varchar(12) NOT NULL PRIMARY KEY,
	password varchar(12) NOT NULL,
	name varchar(100) NOT NULL,
	email varchar(50)
	
)engine=InnoDB character set = utf8;

insert into USERS values ('test1', 'password', 'test', 'test@test.com');
