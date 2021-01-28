select * from EMPLOYEE;
select * from EMPLOYEE_SALARY;
select * from POSITION_LIST;
alter table position_list rename column name to position_name;

select rownum, eno, name, department, position_name, salary from(select e.eno, e.name, e.department, p.position_name, s.salary from EMPLOYEE e, POSITION_LIST p, EMPLOYEE_SALARY s where e.eno = s.eno and e.position = p.pno order by s.salary)where rownum <= 5;

select * from employee e, employee_salary s

select * from employee;