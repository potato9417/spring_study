-- Employees
SELECT * FROM Employees;

-- Job_History
SELECT * FROM job_history WHERE employee_id=102;

-- Employees
INSERT INTO employees (employee_id,first_name,last_name,email,phone_number,hire_date,job_id,salary,commission_pct,manager_id,department_id) VALUES 
(300,'James','Kim','JKIM','511.111.2323',to_date('23/09/04','RR/MM/DD'),'SH_CLERK',90000,null,100,50);

DELETE employees WHERE employee_id=300;
