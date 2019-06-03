## Library-Application (This project is beeing building by me)
This project is about Library Application
#### Using Tools & Technologies
``` 
- Spring Boot
- Spring Security
- JWT
- REST API, DTO
- Jpa, Hibernate
- PostgreSql
- Angular 7
- Typescript
- Bootstrap 4
``` 
#### Sql Query
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
    CONSTRAINT uk_go0gdk91kpgq94qg6ckgejdi4 UNIQUE (fname)
,
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