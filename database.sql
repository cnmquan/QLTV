-- Database: QLTV

-- DROP DATABASE IF EXISTS "QLTV";

CREATE DATABASE "QLTV"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Vietnamese_Vietnam.1258'
    LC_CTYPE = 'Vietnamese_Vietnam.1258'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
	
-- SCHEMA: public

-- DROP SCHEMA IF EXISTS public ;

CREATE SCHEMA IF NOT EXISTS public
    AUTHORIZATION postgres;

COMMENT ON SCHEMA public
    IS 'standard public schema';

GRANT ALL ON SCHEMA public TO PUBLIC;

GRANT ALL ON SCHEMA public TO postgres;

-- Table: public.account

-- DROP TABLE IF EXISTS public.account;

CREATE TABLE IF NOT EXISTS public.account
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 10000000 MINVALUE 10000000 MAXVALUE 99999999 CACHE 1 ),
    name character varying(50) COLLATE pg_catalog."default",
    username character varying(50) COLLATE pg_catalog."default",
    password character varying(50) COLLATE pg_catalog."default",
    email character varying(100) COLLATE pg_catalog."default",
    contact character varying(12) COLLATE pg_catalog."default",
    CONSTRAINT account_pkey PRIMARY KEY (id),
    CONSTRAINT email_unique UNIQUE (email),
    CONSTRAINT username_unique UNIQUE (username)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.account
    OWNER to postgres;
	
INSERT INTO account (name,username,password,email,contact)
VALUES ('admin','admin','admin','a@gmail.com','0123456789'),
		('Phuc','19522038','19522038','19522038@gm.uit.edu.vn','0123456789'),
		('Thinh','19522281','19522281','19522281@gm.uit.edu.vn','0123456789'),
		('Quan','19522074','19522074','19522074@gm.uit.edu.vn','0123456789')
