## Library-Application
This project is about Library Application for automotion. 

## Using Tools & Technologies
``` 
- Spring Boot 2.1.5
- Spring Security, JWT
- REST API (get, post, put, delete, patch)
- Lombok
- ModelMapper, DTO
- JPA, Hibernate
- PostgreSql 9.6
- Angular 8
- Typescript
- Alertify Js
- Bootstrap 4
```
## Create backend side Spring boot 2.1.5
- Download this repository
- After opening this project on STS When you click Library Application project then right click `Maven->Update project`.

## Create front side regarding Angular 8
#####  Create client
if you wanna create this project again You need to write commands below.
- npm i @angular/cli
- ng new project-name
- cd project-name
- npm install bootstrap
- npm install jquery
- npm install alertifyjs --save
- npm install @swimlane/ngx-datatable
- npm i ngx-datatable
##### Build client
if you wanna build this project You need to write `ng build` then You can run client `ng serve --port 5422`
- ng build
- ng serve --port 5422

### Sql Query
``` 
CREATE TABLE public.users
(
    id bigint NOT NULL,
    email character varying(100) COLLATE pg_catalog."default",
    pwd character varying(300) COLLATE pg_catalog."default",
    uname character varying(100) COLLATE pg_catalog."default",
    firstname character varying(100) COLLATE pg_catalog."default",
    lastname character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
    CONSTRAINT uk_iqm8x8lkitrfo4idy96trfm9p UNIQUE (uname)

)

CREATE TABLE public.author
(
    id bigint NOT NULL,
    pwd character varying(300) COLLATE pg_catalog."default",
    email character varying(100) COLLATE pg_catalog."default",
    fname character varying(100) COLLATE pg_catalog."default" NOT NULL,
    phone character varying(100) COLLATE pg_catalog."default",
    surname character varying(100) COLLATE pg_catalog."default" NOT NULL,
    about character varying(300) COLLATE pg_catalog."default",
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT author_pkey PRIMARY KEY (id),
    CONSTRAINT uk_go0gdk91kpgq94qg6ckgejdi4 UNIQUE (fname),
    CONSTRAINT uk_or6k6jmywerxbme223c988bmg UNIQUE (name)

)

CREATE TABLE public.book
(
    id bigint NOT NULL,
    content character varying(300) COLLATE pg_catalog."default",
    name character varying(300) COLLATE pg_catalog."default",
    author_id bigint,
    barcode character varying(300) COLLATE pg_catalog."default",
    publisher character varying(300) COLLATE pg_catalog."default",
    CONSTRAINT book_pkey PRIMARY KEY (id),
    CONSTRAINT fkklnrv3weler2ftkweewlky958 FOREIGN KEY (author_id)
        REFERENCES public.author (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)


CREATE TABLE public.student
(
    id bigint NOT NULL,
    address character varying(100) COLLATE pg_catalog."default",
    city character varying(100) COLLATE pg_catalog."default",
    department character varying(3000) COLLATE pg_catalog."default",
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    fullname character varying(100) COLLATE pg_catalog."default" NOT NULL,
    phone character varying(100) COLLATE pg_catalog."default" NOT NULL,
    tc_no character varying(11) COLLATE pg_catalog."default",
    university character varying(3000) COLLATE pg_catalog."default",
    CONSTRAINT student_pkey PRIMARY KEY (id),
    CONSTRAINT uk_6vhihqulrkjbo4rf08auq73fp UNIQUE (fullname)
,
    CONSTRAINT uk_fe0i52si7ybu0wjedj6motiim UNIQUE (email)
,
    CONSTRAINT uk_gv4cafrm5j078fxw7po0o6cxu UNIQUE (tc_no)

)
``` 
