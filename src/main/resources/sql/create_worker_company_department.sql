CREATE TABLE worker (
id SERIAL PRIMARY KEY,
name VARCHAR(30) NOT NULL
);
CREATE TABLE company (
id SERIAL PRIMARY KEY,
name VARCHAR(30) NOT NULL
);
CREATE TABLE department (
id SERIAL PRIMARY KEY,
name VARCHAR(30) NOT NULL
);
CREATE TABLE company_association (
worker_id INTEGER,
company_id INTEGER,
department_id INTEGER,
CONSTRAINT fk_worker FOREIGN KEY(worker_id) REFERENCES worker(id),
CONSTRAINT fk_company FOREIGN KEY(company_id) REFERENCES company(id),
CONSTRAINT fk_department FOREIGN KEY(department_id) REFERENCES department(id),
PRIMARY KEY (worker_id, company_id, department_id)
);