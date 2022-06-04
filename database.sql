PGDMP     1    &                z            QLTV    14.2    14.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            	           1262    16609    QLTV    DATABASE     g   CREATE DATABASE "QLTV" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Vietnamese_Vietnam.1258';
    DROP DATABASE "QLTV";
                postgres    false            �            1259    16610    account    TABLE     �   CREATE TABLE public.account (
    id integer NOT NULL,
    name character varying(50),
    username character varying(50),
    password character varying(50),
    email character varying(100),
    contact character varying(12)
);
    DROP TABLE public.account;
       public         heap    postgres    false            �            1259    16613    account_id_seq    SEQUENCE     �   ALTER TABLE public.account ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.account_id_seq
    START WITH 10000000
    INCREMENT BY 1
    MINVALUE 10000000
    MAXVALUE 99999999
    CACHE 1
);
            public          postgres    false    209            �            1259    16614    books    TABLE     �  CREATE TABLE public.books (
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
    DROP TABLE public.books;
       public         heap    postgres    false            �            1259    16619 
   publishers    TABLE       CREATE TABLE public.publishers (
    publisher_id character varying(255) NOT NULL,
    publisher_name character varying(255),
    publisher_phone_number character varying(50),
    publisher_address character varying(255),
    publisher_is_deleted boolean
);
    DROP TABLE public.publishers;
       public         heap    postgres    false            �            1259    16624    users    TABLE     �   CREATE TABLE public.users (
    "userID" integer NOT NULL,
    "userName" character varying NOT NULL,
    "userPassword" character varying NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false            �          0    16610    account 
   TABLE DATA           O   COPY public.account (id, name, username, password, email, contact) FROM stdin;
    public          postgres    false    209   )                 0    16614    books 
   TABLE DATA           �   COPY public.books (book_id, book_name, book_category, book_author, book_quantity, book_page_number, book_price, publisher_name, book_publish_year, book_is_deleted, book_updated_time) FROM stdin;
    public          postgres    false    211   �                 0    16619 
   publishers 
   TABLE DATA           �   COPY public.publishers (publisher_id, publisher_name, publisher_phone_number, publisher_address, publisher_is_deleted) FROM stdin;
    public          postgres    false    212   W                 0    16624    users 
   TABLE DATA           E   COPY public.users ("userID", "userName", "userPassword") FROM stdin;
    public          postgres    false    213          
           0    0    account_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.account_id_seq', 10000007, true);
          public          postgres    false    210            i           2606    16630    account account_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.account
    ADD CONSTRAINT account_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.account DROP CONSTRAINT account_pkey;
       public            postgres    false    209            o           2606    16632    books books_pk 
   CONSTRAINT     Q   ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pk PRIMARY KEY (book_id);
 8   ALTER TABLE ONLY public.books DROP CONSTRAINT books_pk;
       public            postgres    false    211            k           2606    16634    account email_unique 
   CONSTRAINT     P   ALTER TABLE ONLY public.account
    ADD CONSTRAINT email_unique UNIQUE (email);
 >   ALTER TABLE ONLY public.account DROP CONSTRAINT email_unique;
       public            postgres    false    209            q           2606    16636    publishers publishers_pk 
   CONSTRAINT     `   ALTER TABLE ONLY public.publishers
    ADD CONSTRAINT publishers_pk PRIMARY KEY (publisher_id);
 B   ALTER TABLE ONLY public.publishers DROP CONSTRAINT publishers_pk;
       public            postgres    false    212            m           2606    16638    account username_unique 
   CONSTRAINT     V   ALTER TABLE ONLY public.account
    ADD CONSTRAINT username_unique UNIQUE (username);
 A   ALTER TABLE ONLY public.account DROP CONSTRAINT username_unique;
       public            postgres    false    209            s           2606    16640    users users_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY ("userID");
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    213            �   q   x�34 �Ĕ��<T�!=713G/9?������������Ô3 �4����������ԭW�Y���R�W���3ΐ�̼�#CLA#�9K�V��`2p����� U�<�         �  x��UM��@=���>y�I�XXOB���Rk�Z=xB�LI2
j���z@k� ������^�L*����t�3csß����k6.�G[4�F�o2S���?z���6=^����q"q�V��hYY����qA���{�iN�3/]]R�()�G#���L��D�bG�M.�5z�"V�;5�S���B�4u�k��6��kn�� ��>e7�����1EcE��)]�/B����B*)Q<reR�g��pR��0m_�֝ڦ�I�b)�a�s��4�eG��A*0Q��=pD��pt�>8>"��c��htΉ��Q�O�wk��Ƈ�`_c,r����-��3�p��3�B���3)�loA7�[�ݵGD���yCʴ奈cf��}(9t�%�� ���לJ���9����z�S,P�l�n��7���eDӠn���A��!�¸�B�!���kW�0$�$a�1�QH�q�:���3���4J9zф��f�m/<!�s�!h��[��\�9�S_`]h�|�KQ��uHc�����mM휽C� ��ż�9�D�[�#���R�PJ����>���.H7Aj�6i�� վ	yq�v �O������Ĺ�0ҍ�%*�"X�y�_���c̓���g�0;�/�����
���ņY$$ږq�9�|7K �'�e����r�����5���?��mo����-�kg�Ǳ�d�$W!�$���*��7�}PkO�#%,�4@��#[j����ӂ���p(��ܧBz~���[C��3�*ǡmh��]ff@�1�?�2�$4B���ױEԑS(����c�[TNu�6<���;S���3��R/>$�8��PX
�4_����")ߘy!%��">T�KӉ&�뚌��rv�\�0p٠��-��},v�]�{�q�*8��|�W�|}{ss��t-�           x����N�0���S� ��k[� hD�8p�J�}�]���Ǳq@��f<�^6I����]gB�P*
#Hg����~������`�8|��2�Gɴ!�����hy�&gp�
Y�1̈́Դ�`mƶ���<<��go�y���)Y�ɂ���L�=�>�V��ԸB�Lt�C�,mX!����*g��.�d�3�L�p�&��eS��{�Y�@�J1l��&[<�f���״B �#�,�`�>��e�WӰ�;M�&ȒX*]k��K���o�M�� �ܪ&            x������ � �     