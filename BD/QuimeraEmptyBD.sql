--
-- PostgreSQL database cluster dump
--

-- Started on 2025-05-31 18:05:58

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:gho0qp0XCvKX2hTYAbNRvg==$mX8if1aJBSn5aluSlRzJP8U26CDUazwMRbOX7Fc5p4A=:YTHboG8Rhc2G7nsSiPqwtGDmPsZ/CkzrhqhPZxf1TSE=';

--
-- User Configurations
--








--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5 (Debian 17.5-1.pgdg120+1)
-- Dumped by pg_dump version 17.4

-- Started on 2025-05-31 18:05:58

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Completed on 2025-05-31 18:05:58

--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

\connect postgres

--
-- PostgreSQL database dump
--

-- Dumped from database version 17.5 (Debian 17.5-1.pgdg120+1)
-- Dumped by pg_dump version 17.4

-- Started on 2025-05-31 18:05:58

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
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
-- TOC entry 218 (class 1259 OID 16389)
-- Name: tarefa; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tarefa (
    id integer NOT NULL,
    titulo character varying(50),
    descricao character varying(500),
    situacao character varying(1),
    data_inicio date,
    data_fim date,
    fk_usuario_id integer
);


ALTER TABLE public.tarefa OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16384)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    id integer NOT NULL,
    usuario character varying(30),
    senha character varying(50),
    email character varying(100),
    login_ativo character varying(1),
    nome_completo character varying(100),
    data_nascimento date
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 3364 (class 0 OID 16389)
-- Dependencies: 218
-- Data for Name: tarefa; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tarefa (id, titulo, descricao, situacao, data_inicio, data_fim, fk_usuario_id) FROM stdin;
\.


--
-- TOC entry 3363 (class 0 OID 16384)
-- Dependencies: 217
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.usuario (id, usuario, senha, email, login_ativo, nome_completo, data_nascimento) FROM stdin;
\.


--
-- TOC entry 3216 (class 2606 OID 16395)
-- Name: tarefa tarefa_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tarefa
    ADD CONSTRAINT tarefa_pkey PRIMARY KEY (id);


--
-- TOC entry 3214 (class 2606 OID 16388)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 3217 (class 2606 OID 16396)
-- Name: tarefa fk_tarefa_2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tarefa
    ADD CONSTRAINT fk_tarefa_2 FOREIGN KEY (fk_usuario_id) REFERENCES public.usuario(id) ON DELETE SET NULL;


-- Completed on 2025-05-31 18:05:59

--
-- PostgreSQL database dump complete
--

-- Completed on 2025-05-31 18:05:59

--
-- PostgreSQL database cluster dump complete
--

