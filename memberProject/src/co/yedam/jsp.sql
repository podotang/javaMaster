--사원테이블(사원번호,사원명,연락처,이메일,입사일자,급여)
drop table emp purge;

create table emp(
    emp_no number primary key   --emp_seq.nextval
    ,emp_name varchar2(40)not null
    ,emp_phone varchar2(13) not null --02-1234-1234
    ,email varchar2(30) not null
    ,hire_date date default sysdate
    ,salary number
);
create sequence emp_seq;

insert into emp(emp_no,emp_name,emp_phone,email,salary)
    values(emp_seq.nextval, 'kildongHong','01-1234-1234','kildong@naver.com',2000);
    
insert into emp(emp_no
                ,emp_name
                ,emp_phone
                ,email
                ,salary)
values(emp_seq.nextval
        , 'kildongPark'
        ,'01-3333-4444'
        ,'pkildong@naver.com'
        ,2300
);
insert into emp(emp_no
                ,emp_name
                ,emp_phone
                ,email
                ,salary)
values(emp_seq.nextval
        , 'kildongJung'
        ,'053-3333-1111'
        ,'jkildong@naver.com'
        ,5300
);

select * from emp
order by emp_no;

update emp
set salary =salary + 500
    ,emp_phone='01-1111-1111'
where emp_name='kildongHong';

delete from emp
where emp_no = 3;

commit;

update emp 
set emp_name = "길동홍"
where emp_no=1;

--===========================================================================
create sequence semp_seq;

create table semp(
    semp_no     number(3) primary key
    ,semp_name  varchar2(20) not null
    ,semp_phone varchar2(13) not null
    ,semp_birth date default sysdate
    ,semp_gen   varchar2(7) not null
);

drop table semp;

insert into semp (semp_no,semp_name,semp_phone,semp_birth,semp_gen)
values(semp_seq.nextval,'정유진','010-777-1234','2023-11-11','woman');

insert into semp (semp_no,semp_name,semp_phone,semp_birth,semp_gen)
values(semp_seq.nextval,'박유진','010-555-1234','2018-05-21','man');

select * from semp order by semp_no;

commit;

alter table emp add dept varchar2(10);

alter tabler emp
modify ();















