PGDMP     7    $                z            QLTV    14.2    14.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            	           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            
           1262    16414    QLTV    DATABASE     g   CREATE DATABASE "QLTV" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Vietnamese_Vietnam.1258';
    DROP DATABASE "QLTV";
                postgres    false            �            1259    16447    account    TABLE     �   CREATE TABLE public.account (
    id integer NOT NULL,
    name character varying(50),
    username character varying(50),
    password character varying(50),
    email character varying(100),
    contact character varying(12)
);
    DROP TABLE public.account;
       public         heap    postgres    false            �            1259    16446    account_id_seq    SEQUENCE     �   ALTER TABLE public.account ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.account_id_seq
    START WITH 10000000
    INCREMENT BY 1
    MINVALUE 10000000
    MAXVALUE 99999999
    CACHE 1
);
            public          postgres    false    210            �            1259    16456    books    TABLE     �  CREATE TABLE public.books (
    book_id character varying(255) NOT NULL,
    book_name character varying(255),
    book_category character varying(255),
    book_author character varying(255),
    book_quantity integer,
    book_page_number integer,
    book_price double precision,
    publisher_id character varying(255),
    book_publish_year integer,
    book_is_deleted boolean
);
    DROP TABLE public.books;
       public         heap    postgres    false            �            1259    16461 
   publishers    TABLE       CREATE TABLE public.publishers (
    publisher_id character varying(255) NOT NULL,
    publisher_name character varying(255),
    publisher_phone_number character varying(50),
    publisher_address character varying(255),
    publisher_is_deleted boolean
);
    DROP TABLE public.publishers;
       public         heap    postgres    false            �            1259    16466    users    TABLE     �   CREATE TABLE public.users (
    "userID" integer NOT NULL,
    "userName" character varying NOT NULL,
    "userPassword" character varying NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false                      0    16447    account 
   TABLE DATA           O   COPY public.account (id, name, username, password, email, contact) FROM stdin;
    public          postgres    false    210   b                 0    16456    books 
   TABLE DATA           �   COPY public.books (book_id, book_name, book_category, book_author, book_quantity, book_page_number, book_price, publisher_id, book_publish_year, book_is_deleted) FROM stdin;
    public          postgres    false    211   �                 0    16461 
   publishers 
   TABLE DATA           �   COPY public.publishers (publisher_id, publisher_name, publisher_phone_number, publisher_address, publisher_is_deleted) FROM stdin;
    public          postgres    false    212                    0    16466    users 
   TABLE DATA           E   COPY public.users ("userID", "userName", "userPassword") FROM stdin;
    public          postgres    false    213   ;                  0    0    account_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.account_id_seq', 10000007, true);
          public          postgres    false    209            i           2606    16451    account account_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.account DROP CONSTRAINT account_pkey;
       public            postgres    false    210            o           2606    16472    books books_pk 
   CONSTRAINT     Q   ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pk PRIMARY KEY (book_id);
 8   ALTER TABLE ONLY public.books DROP CONSTRAINT books_pk;
       public            postgres    false    211            k           2606    16453    account email_unique 
   CONSTRAINT     P   ALTER TABLE ONLY public.account
    ADD CONSTRAINT email_unique UNIQUE (email);
 >   ALTER TABLE ONLY public.account DROP CONSTRAINT email_unique;
       public            postgres    false    210            q           2606    16474    publishers publishers_pk 
   CONSTRAINT     `   ALTER TABLE ONLY public.publishers
    ADD CONSTRAINT publishers_pk PRIMARY KEY (publisher_id);
 B   ALTER TABLE ONLY public.publishers DROP CONSTRAINT publishers_pk;
       public            postgres    false    212            m           2606    16455    account username_unique 
   CONSTRAINT     V   ALTER TABLE ONLY public.account
    ADD CONSTRAINT username_unique UNIQUE (username);
 A   ALTER TABLE ONLY public.account DROP CONSTRAINT username_unique;
       public            postgres    false    210            s           2606    16476    users users_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY ("userID");
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    213            t           2606    16477    books books_publishers_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_publishers_fk FOREIGN KEY (publisher_id) REFERENCES public.publishers(publisher_id);
 C   ALTER TABLE ONLY public.books DROP CONSTRAINT books_publishers_fk;
       public          postgres    false    212    211    3185               q   x�34 �Ĕ��<T�!=713G/9?������������Ô3 �4����������ԭW�Y���R�W���3ΐ�̼�#CLA#�9K�V��`2p����� U�<�         +  x�eTM��0=�bN�4Z%|�=-D��X��z1I[%�b����q�C��=�޼y��*���r��Ѷ.9w��h�j�6����፪hS�kQ�F���kMS��!ţ0i�}�<��ߧ߽�*�J���Z�C�T�Z��r�,'�+�'�Ku�cM���M����]�j�	�iL�(�h��@��mH�l�g��8��I-��8QX\�uW�M���׉s�6,�h����ɐ?>�g؛�x��'1��7ÇrG�*�kS8�k�Rj���t1�B�������G^
��f�0�B�������$z�4y�w$�a8�䝃;��_��Ƨ]i{o܍��:|�ٚF>k$Y۴cI�i'��r-
ԥ6h��I3��r)�9�4o���`�LN�Trv:��'�j,Xy�4ເ���R�[4(�c���Gk>!ܷ�j�]�G1S��2�.��� ��ړW�>������JM��1Hp�7�$!��nd��Ǵv���?�?�:��3<Ko�D�s-��7�䧆�t��	k���'d����;�c�2^`_�����g���É5�b��)� h�S`b%X��Q�@!��2=�l３�ȕ�hHC(0�Oy|�f��];`a�/f<��[!-�ts]��r���
�A�W�`��xJ�۸���Ѵ���u���d�������9}U�=�B��a��8V�k7U������c�y��(YZw+Q��\$�v��m0��ь�sGDk�c��� ��?��I��|��B�q` �q�l�8�<�'���.�g'�M�ؖע�(N�h|/��ԯ�^���>��           x�u��N�0E��W� ��׶�DQ�.�X%�^4�P���3q� ���gܽn8�����x�]Ό��\TN��C��b?B�/������+�D-�c���>���%K��r(cV(my]��mCEx��	����c�#U�T!PKYz��w�'x�N+�-��&Eh���VH�,^�,�t�_g@-7���M�8��'���,� m*c��M�+�l�V������+��>f��B��"����PPF����mUU?�͜R            x������ � �     