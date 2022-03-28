drop table if exists 'saiyan' CASCADE;
create table 'saiyan' (
   'id' bigint primary key auto_increment,
   'age' integer check ( age>= 13 AND age<= 65),
   'isssj' boolean,
   'name' varchar (255) not null,
   'power_level' integer,
   'sex' varchar (255));