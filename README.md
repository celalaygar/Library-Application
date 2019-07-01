## Library-Application (This project is beeing built by me)
This project is about Library Application. 
I hace run this project's backside on localhost:8182/ and frontside on localhost:5422/
## Using Tools & Technologies
``` 
- Spring Boot 2.1.5
- Spring Security
- JWT
- REST API, DTO
- Jpa, Hibernate
- PostgreSql 9.6
- Angular 8
- Typescript
- Bootstrap 4
- Lombok
- ModelMapper
```
## Author Rest api
These links are just example how to use rest api with these links.

Author get mapping : 
```
localhost:8182/api/author 
localhost:8182/api/author/{id} 
```
Author get mapping as pagination: 
```
localhost:8182/api/author/pagination?page=0&size=3
localhost:8182/api/author/pagination?page=1&size=5
```
Author post mapping :
``` 
localhost:8182/api/author
{
	"name":"lara",
	"surname":"gül",
	"email":"lara.g@gmail.com",
	"phone":"+905434445511",
	"about":"hakan ali is using an email called hasan.a@gmail.com"
	
}
``` 
Author put mapping :
``` 
localhost:8182/api/author/{id} 
{
	"name":"hakan ali",
	"surname":"kara",
	"email": "hasan.a@gmail.com",
	"phone": "+905434445511",
	"about":"hakan ali is using an email called hasan.a@gmail.com"
}
``` 
Author delete mapping : 
``` 
localhost:8182/api/author/{id} 
```
## Book Rest api
These links are just example how to use rest api with these links.

Book get mapping : 
```
localhost:8182/api/book 
localhost:8182/api/book/{id}
```
Book get mapping as pagination: 
```
localhost:8182/api/book/pagination?page=0&size=3
localhost:8182/api/book/pagination?page=1&size=5
```
Book post mapping : 
```
localhost:8182/api/book
{
	"name":"Ağa sızma teknikleri",
	"barcode":"NTW-00352",
	"content":"Network üzerinden ağa sızma teknikleri anlatılmaktadır.",
	"publisher":"Kodlab Yayıncılık",
	"bookStatus":"FREE",
	"authorId":1
}
```
Book put mapping :  
```
localhost:8182/api/book/{id} 

{
	"name": "Spring boot 2",
	"barcode": "SPRING-01-2",
	"content": "Spring boot 2 ile web uygulama adımları anlatılmıştır",
	"publisher": "Level 2",
	"bookStatus":"USED",
	"authorId": 4
}
```
Book delete mapping : 
```
localhost:8182/api/book/{id} 
``` 
## Create front side regarding Angular 8
Follow step: open git bash and write bottom ones to create new project about angular 8
- npm i @angular/cli
- ng new project-name
- cd project-name
- npm install bootstrap
- npm install jquery
- npm install @swimlane/ngx-datatable
- npm i ngx-datatable
- npm install ngx-bootstrap
Then you can get author and book data from back side regarding spring boot
### Sql Query
``` 

CREATE TABLE public.users
(
    id bigint NOT NULL,
    email character varying(100) COLLATE pg_catalog."default",
    pwd character varying(300) COLLATE pg_catalog."default",
    surname character varying(100) COLLATE pg_catalog."default",
    uname character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id),
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
``` 
