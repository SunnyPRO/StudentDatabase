# --- First database schema

# --- !Ups

set ignorecase true;

create table groupsql (
  id                        bigint not null,
  name                      varchar(255) not null,
  constraint pk_groupsql primary key (id))
;

create table student (
  id                        bigint not null,
  name                      varchar(255) not null,
  introduced                timestamp,
  groupsql_id                bigint,
  constraint pk_student primary key (id))
;

create sequence groupsql_seq start with 1000;

create sequence student_seq start with 1000;

alter table student add constraint fk_student_group_1 foreign key (groupsql_id) references groupsql (id) on delete restrict on update restrict;
create index ix_student_groupsql_1 on student (groupsql_id);


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists groupsql;

drop table if exists student;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists groupsql_seq;

drop sequence if exists student_seq;

