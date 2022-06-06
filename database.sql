--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2
-- Dumped by pg_dump version 14.2

-- Started on 2022-06-05 01:47:34

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 209 (class 1259 OID 16538)
-- Name: account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.account (
    id integer NOT NULL,
    name character varying(50),
    username character varying(50),
    password character varying(50),
    email character varying(100),
    contact character varying(12),
    question character varying,
    answer character varying,
    role character varying DEFAULT 'LIBRARIAN'::character varying
);


ALTER TABLE public.account OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16541)
-- Name: account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.account ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.account_id_seq
    START WITH 10000000
    INCREMENT BY 1
    MINVALUE 10000000
    MAXVALUE 99999999
    CACHE 1
);


--
-- TOC entry 211 (class 1259 OID 16542)
-- Name: books; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.books (
    book_id character varying(255) NOT NULL,
    book_name character varying(255),
    book_category character varying(255),
    book_author character varying(255),
    book_quantity integer,
    book_page_number integer,
    book_price double precision,
    publisher_name character varying(255),
    book_publish_year integer,
    book_is_deleted boolean,
    book_updated_time timestamp without time zone
);


ALTER TABLE public.books OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16547)
-- Name: publishers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.publishers (
    publisher_id character varying(255) NOT NULL,
    publisher_name character varying(255),
    publisher_phone_number character varying(50),
    publisher_address character varying(255),
    publisher_is_deleted boolean
);


ALTER TABLE public.publishers OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16552)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    "userID" integer NOT NULL,
    "userName" character varying NOT NULL,
    "userPassword" character varying NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

CREATE TABLE public.borrows (
    borrow_id character varying NOT NULL,
    reader_id character varying NOT NULL,
    book_id character varying NOT NULL,
    quantity integer DEFAULT 0 NOT NULL,
    borrow_date date NOT NULL,
    return_date date,
    borrow_is_deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE public.borrows OWNER TO postgres;

CREATE TABLE public.readers (
    reader_id character varying NOT NULL,
    reader_name character varying NOT NULL,
    reader_phone_number character varying NOT NULL,
    reader_is_deleted boolean DEFAULT false NOT NULL
);


ALTER TABLE public.readers OWNER TO postgres;
--
-- TOC entry 3326 (class 0 OID 16538)
-- Dependencies: 209
-- Data for Name: account; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.readers (reader_id, reader_name, reader_phone_number, reader_is_deleted) VALUES ('rd001', 'nguyen doan thinh', '0328384454', false);

INSERT INTO public.account (id, name, username, password, email, contact, question, answer, role) OVERRIDING SYSTEM VALUE VALUES (10000005, 'Phuc', '19522038', 'ea1c6a97e00545178ef6e43110820906', '19522038@gm.uit.edu.vn', '0123456789', 'Họ và tên của người anh họ lớn nhất của bạn là gì?', 'Nguyễn Duy Phúc', 'LIBRARIAN');
INSERT INTO public.account (id, name, username, password, email, contact, question, answer, role) OVERRIDING SYSTEM VALUE VALUES (10000006, 'Thinh', '19522281', 'f19a694f06ff7045d64fcb3057b269dc', '19522281@gm.uit.edu.vn', '0123456789', 'Họ và tên của người anh họ lớn nhất của bạn là gì?', 'Nguyễn Duy Phúc', 'LIBRARIAN');
INSERT INTO public.account (id, name, username, password, email, contact, question, answer, role) OVERRIDING SYSTEM VALUE VALUES (10000007, 'Quan', '19522074', 'a43e5317f8511e5f608d233b335f6558', '19522074@gm.uit.edu.vn', '0123456789', 'Họ và tên của người anh họ lớn nhất của bạn là gì?', 'Nguyễn Duy Phúc', 'LIBRARIAN');
INSERT INTO public.account (id, name, username, password, email, contact, question, answer, role) OVERRIDING SYSTEM VALUE VALUES (10000004, 'admin', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin@gmail.com', '0123456789', 'Họ và tên của người anh họ lớn nhất của bạn là gì?', 'Nguyễn Duy Phúc', 'ADMIN');


--
-- TOC entry 3328 (class 0 OID 16542)
-- Dependencies: 211
-- Data for Name: books; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.books (book_id, book_name, book_category, book_author, book_quantity, book_page_number, book_price, publisher_name, book_publish_year, book_is_deleted, book_updated_time) VALUES ('BK01', 'Linh Nam Chich Quai', 'Van hoc Viet Nam', 'Tran The Phap, Vu Quynh, Kieu Phu, Dinh Gia Khanh, Nguyen Ngoc San, Ta Huy Long', 10, 260, 316000, 'NXB Kim Dong', 2021, false, '2022-06-01 04:05:06');
INSERT INTO public.books (book_id, book_name, book_category, book_author, book_quantity, book_page_number, book_price, publisher_name, book_publish_year, book_is_deleted, book_updated_time) VALUES ('BK02', 'Bo cau khong dua thu', 'Van hoc VIet Nam', 'Nguyen Nhat Anh', 8, 180, 95000, 'NXB Tre', 2022, false, '2022-06-01 04:05:06');
INSERT INTO public.books (book_id, book_name, book_category, book_author, book_quantity, book_page_number, book_price, publisher_name, book_publish_year, book_is_deleted, book_updated_time) VALUES ('BK03', 'Kham Pha Nhung Thanh Pho Tuyet Dep Tren The Gioi', 'Van hoa xa hoi', 'Huynh Thu Dung', 6, 210, 151000, 'NXB Tong hop TPHCM', 2019, false, '2022-06-01 04:05:06');
INSERT INTO public.books (book_id, book_name, book_category, book_author, book_quantity, book_page_number, book_price, publisher_name, book_publish_year, book_is_deleted, book_updated_time) VALUES ('BK04', 'Mot so van ban ve cong tac Dang va quy dinh, huong dan thi hanh', 'Chinh tri', 'Dang Cong san Viet Nam', 10, 279, 106000, 'NXB Chinh Tri Quoc gia Su that', 2022, false, '2022-06-01 04:05:06');
INSERT INTO public.books (book_id, book_name, book_category, book_author, book_quantity, book_page_number, book_price, publisher_name, book_publish_year, book_is_deleted, book_updated_time) VALUES ('BK05', 'Tai Lieu Chuyen Toan Trung hoc co so Toan 9 Dai So + Hinh Hoc', 'Giao duc', 'Vu Huu Binh', 2, 200, 75000, 'NXB Giao duc', 2020, false, '2022-06-01 04:05:06');
INSERT INTO public.books (book_id, book_name, book_category, book_author, book_quantity, book_page_number, book_price, publisher_name, book_publish_year, book_is_deleted, book_updated_time) VALUES ('BK06', 'Bam Chan Qua Tuoi Dai Kho', 'Van hoc Viet Nam', 'Cao Xuan Son', 5, 200, 150000, 'NXB Hoi Nha Van', 2021, false, '2022-06-01 04:05:06');
INSERT INTO public.books (book_id, book_name, book_category, book_author, book_quantity, book_page_number, book_price, publisher_name, book_publish_year, book_is_deleted, book_updated_time) VALUES ('BK07', 'Ky nang nghiep vu hoi tham dung trong xet xu cac vu an hinh su', 'Chinh tri', 'Ts. Vu Hoai Nam', 12, 300, 106000, 'NXB Tu Phap', 2017, false, '2022-06-01 04:05:06');
INSERT INTO public.books (book_id, book_name, book_category, book_author, book_quantity, book_page_number, book_price, publisher_name, book_publish_year, book_is_deleted, book_updated_time) VALUES ('BK08', 'Chuyen doi so den cot loi: Nang tam nang luc lanh dao cho nganh nghe, doanh nghiep va chinh ban than ban', 'Kinh te', 'Pham Anh Tuan, Huynh Huu Tai', 2, 309, 210000, 'NXB Thong tin va Truyen thong', 2020, false, '2022-06-01 04:05:06');
INSERT INTO public.books (book_id, book_name, book_category, book_author, book_quantity, book_page_number, book_price, publisher_name, book_publish_year, book_is_deleted, book_updated_time) VALUES ('BK09', 'Luat Dat Dai & Cac Van Ban Huong Dan Thi Hanh Moi Nhat   Giai Quyet Thu Tuc Dang Ky Dat Dai, Tai San Khac Gan Lien Voi Dat; Cap, Cap Doi, Cap Lai So Do   Chinh Sach Mien, Giam Thue Su Dung Dat Nong Nghiep', 'Chinh tri', 'Vu Tuoi, Thien Kim', 4, 400, 395000, 'NXB Lao Dong', 2021, false, '2022-06-01 04:05:06');
INSERT INTO public.books (book_id, book_name, book_category, book_author, book_quantity, book_page_number, book_price, publisher_name, book_publish_year, book_is_deleted, book_updated_time) VALUES ('BK10', 'Giao trinh dao duc nguoi lai xe va van hoa giao thong', 'Giao duc', 'Tong cuc duong bo Viet Nam', 4, 68, 41000, 'NXB Giao thong Van tai', 2018, false, '2022-06-01 04:05:06');
INSERT INTO public.books (book_id, book_name, book_category, book_author, book_quantity, book_page_number, book_price, publisher_name, book_publish_year, book_is_deleted, book_updated_time) VALUES ('BK11', 'Truyen co grimm', 'Van hoc co dien', 'Jacob Grimm, Wilhelm Grimm', 2, 200, 300000, 'NXB Kim Dong', 1812, false, '2022-06-01 04:05:06');
INSERT INTO public.books (book_id, book_name, book_category, book_author, book_quantity, book_page_number, book_price, publisher_name, book_publish_year, book_is_deleted, book_updated_time) VALUES ('BK12', 'Sach tieng anh 10', 'Giao duc', 'Bo GD & DT', 10, 60, 25000, 'NXB Giao duc', 1990, false, '2022-06-01 04:05:06');
INSERT INTO public.books (book_id, book_name, book_category, book_author, book_quantity, book_page_number, book_price, publisher_name, book_publish_year, book_is_deleted, book_updated_time) VALUES ('BK13', 'Thien su nha su ben', 'Light Novel', 'Hong Duc', 2, 300, 300000, 'Kim Dong TPHCM', 2013, false, '2022-06-01 04:05:06');


--
-- TOC entry 3329 (class 0 OID 16547)
-- Dependencies: 212
-- Data for Name: publishers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.publishers (publisher_id, publisher_name, publisher_phone_number, publisher_address, publisher_is_deleted) VALUES ('PUB01', 'NXB Kim Dong', '01900571595', 'Ha Noi, Viet Nam', false);
INSERT INTO public.publishers (publisher_id, publisher_name, publisher_phone_number, publisher_address, publisher_is_deleted) VALUES ('PUB02', 'NXB Tre', '02839316289', 'TPHCM, Viet Nam', false);
INSERT INTO public.publishers (publisher_id, publisher_name, publisher_phone_number, publisher_address, publisher_is_deleted) VALUES ('PUB03', 'NXB Tong hop TPHCM', '02838256804', 'TPHCM, Viet Nam', false);
INSERT INTO public.publishers (publisher_id, publisher_name, publisher_phone_number, publisher_address, publisher_is_deleted) VALUES ('PUB04', 'NXB Chinh Tri Quoc gia Su that', '02438221633', 'Ha Noi, Viet Nam', false);
INSERT INTO public.publishers (publisher_id, publisher_name, publisher_phone_number, publisher_address, publisher_is_deleted) VALUES ('PUB05', 'NXB Giao duc', '02438220801', 'Ha Noi, Viet Nam', false);
INSERT INTO public.publishers (publisher_id, publisher_name, publisher_phone_number, publisher_address, publisher_is_deleted) VALUES ('PUB06', 'NXB Hoi Nha Van', '02438222135', 'Ha Noi, Viet Nam', false);
INSERT INTO public.publishers (publisher_id, publisher_name, publisher_phone_number, publisher_address, publisher_is_deleted) VALUES ('PUB07', 'NXB Tu Phap', '02462632079', 'Ha Noi, Viet Nam', false);
INSERT INTO public.publishers (publisher_id, publisher_name, publisher_phone_number, publisher_address, publisher_is_deleted) VALUES ('PUB08', 'NXB Thong tin va Truyen thong', '02435772145', 'Ha Noi, Viet Nam', false);
INSERT INTO public.publishers (publisher_id, publisher_name, publisher_phone_number, publisher_address, publisher_is_deleted) VALUES ('PUB09', 'NXB Lao Dong', '02438515380', 'Ha Noi, Viet Nam', false);
INSERT INTO public.publishers (publisher_id, publisher_name, publisher_phone_number, publisher_address, publisher_is_deleted) VALUES ('PUB10', 'NXB Giao thong Van tai', '02439423345', 'Ha Noi, Viet Nam', false);
INSERT INTO public.publishers (publisher_id, publisher_name, publisher_phone_number, publisher_address, publisher_is_deleted) VALUES ('PUB12', 'NXB Nhan Van', '0123456789', 'Ha Noi, Viet Nam', false);
INSERT INTO public.publishers (publisher_id, publisher_name, publisher_phone_number, publisher_address, publisher_is_deleted) VALUES ('PUB11', 'NXB IPM', '0123456789', 'Ha Noi, Viet Nam', false);

INSERT INTO public.borrows (borrow_id, reader_id, book_id, quantity, borrow_date, return_date, borrow_is_deleted) VALUES ('BR02', 'rd001', 'BK09', 4, '2022-02-22', '2022-02-22', false);
INSERT INTO public.borrows (borrow_id, reader_id, book_id, quantity, borrow_date, return_date, borrow_is_deleted) VALUES ('BR04', 'rd001', 'BK06', 5, '2001-12-05', '2012-08-12', false);
INSERT INTO public.borrows (borrow_id, reader_id, book_id, quantity, borrow_date, return_date, borrow_is_deleted) VALUES ('BR03', 'rd001', 'BK01', 5, '2012-02-22', '2012-02-22', false);
INSERT INTO public.borrows (borrow_id, reader_id, book_id, quantity, borrow_date, return_date, borrow_is_deleted) VALUES ('BR08', 'rd001', 'BK01', 10, '2023-02-22', '2023-02-24', false);
--
-- TOC entry 3330 (class 0 OID 16552)
-- Dependencies: 213
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 3336 (class 0 OID 0)
-- Dependencies: 210
-- Name: account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.account_id_seq', 10000009, true);


--
-- TOC entry 3178 (class 2606 OID 16558)
-- Name: account account_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);


--
-- TOC entry 3182 (class 2606 OID 16560)
-- Name: books books_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pk PRIMARY KEY (book_id);


--
-- TOC entry 3184 (class 2606 OID 16564)
-- Name: publishers publishers_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.publishers
    ADD CONSTRAINT publishers_pk PRIMARY KEY (publisher_id);


--
-- TOC entry 3180 (class 2606 OID 16566)
-- Name: account username_unique; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.account
    ADD CONSTRAINT username_unique UNIQUE (username);


--
-- TOC entry 3186 (class 2606 OID 16568)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY ("userID");

ALTER TABLE ONLY public.readers
    ADD CONSTRAINT readers_pkey PRIMARY KEY (reader_id);

ALTER TABLE ONLY public.borrows
    ADD CONSTRAINT borrows_pkey PRIMARY KEY (borrow_id);
-- Completed on 2022-06-05 01:47:34

--
-- PostgreSQL database dump complete
--

