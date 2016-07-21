# --- Sample dataset

# --- !Ups

insert into groupsql (id,name) values (  1,'TI-121');
insert into groupsql (id,name) values (  2,'TI-122');
insert into groupsql (id,name) values (  3,'TI-123');

insert into student (id,name,introduced,groupsql_id) values (  1,'Berestean Victor',null,1);
insert into student (id,name,introduced,groupsql_id) values (  2,'Canciuc Anatole',null,1);
insert into student (id,name,introduced,groupsql_id) values (  3,'Cobîlaș Adrian',null,1);
insert into student (id,name,introduced,groupsql_id) values (  4,'Evtodieva Alisa',null,1);
insert into student (id,name,introduced,groupsql_id) values (  5,'Golban Valeria','2010-01-01',1);
insert into student (id,name,introduced,groupsql_id) values (  6,'Latu Eugenia',null,1);
insert into student (id,name,introduced,groupsql_id) values (  7,'Nichita Mihail',null,1);
insert into student (id,name,introduced,groupsql_id) values (  8,'Ostafi Ion',null,1);
insert into student (id,name,introduced,groupsql_id) values (  9,'Sonic Alexandru',null,1);
insert into student (id,name,introduced,groupsql_id) values (  10,'Șuiu Ion',null,1);
insert into student (id,name,introduced,groupsql_id) values (  11,'Vacaruc Andrei','2011-01-01',1);
insert into student (id,name,introduced,groupsql_id) values (  12,'Ziaev Alexandr',null,1);
insert into student (id,name,introduced,groupsql_id) values (  13,'Însurățelu Alexandru',null,2);
insert into student (id,name,introduced,groupsql_id) values (  14,'Buga Marian',null,2);
insert into student (id,name,introduced,groupsql_id) values (  15,'Cebotari Piotr',null,2);
insert into student (id,name,introduced,groupsql_id) values (  16,'Celpan Valeria',null,2);
insert into student (id,name,introduced,groupsql_id) values (  17,'Chetrușca Ecaterina',null,2);
insert into student (id,name,introduced,groupsql_id) values (  18,'Cojocaru Ștefan',null,2);
insert into student (id,name,introduced,groupsql_id) values (  19,'Colnic Adrian',null,2);
insert into student (id,name,introduced,groupsql_id) values (  20,'Cotorobai Victor',null,2);
insert into student (id,name,introduced,groupsql_id) values (  21,'Bodoga Cristina',null,3);

# --- !Downs

delete from student;
delete from groupsql;
