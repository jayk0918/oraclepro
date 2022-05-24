-- create table
create table person(
    person_id number(5)
    ,name varchar2(30) not null
    ,hp varchar2(20)
    ,company varchar2(20)
    ,primary key(person_id)
);

-- create sequence
create sequence seq_person_id
increment by 1
start with 1;

select  *
from person
where name like '%123%'
or hp like '%123%'
or company like '%123%';
        

drop table person;
drop sequence seq_person_id;


select *
from person
order by person_id;