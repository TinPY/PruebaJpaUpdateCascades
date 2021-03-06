--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.7
-- Dumped by pg_dump version 9.2.7
-- Started on 2017-12-04 16:43:08

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 176 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1961 (class 0 OID 0)
-- Dependencies: 176
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 168 (class 1259 OID 122634)
-- Name: etapa_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE etapa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.etapa_id_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 169 (class 1259 OID 122636)
-- Name: etapas; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE etapas (
    id integer NOT NULL,
    avance integer,
    nombreetapa character varying(255) NOT NULL,
    proyectoid_id integer
);


ALTER TABLE public.etapas OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 122639)
-- Name: persona_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE persona_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.persona_id_seq OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 122641)
-- Name: personas; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE personas (
    id integer NOT NULL,
    nombre character varying(255),
    telefono character varying(255)
);


ALTER TABLE public.personas OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 122647)
-- Name: proyecto_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE proyecto_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.proyecto_id_seq OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 122649)
-- Name: proyectos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE proyectos (
    id integer NOT NULL,
    nombreproyecto character varying(255) NOT NULL,
    responsable integer
);


ALTER TABLE public.proyectos OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 122652)
-- Name: tarea_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tarea_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tarea_id_seq OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 122654)
-- Name: tareas; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tareas (
    id integer NOT NULL,
    nombretarea character varying(255) NOT NULL,
    prioridad integer,
    etapaid integer NOT NULL
);


ALTER TABLE public.tareas OWNER TO postgres;

--
-- TOC entry 1962 (class 0 OID 0)
-- Dependencies: 168
-- Name: etapa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('etapa_id_seq', 2, true);


--
-- TOC entry 1947 (class 0 OID 122636)
-- Dependencies: 169
-- Data for Name: etapas; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO etapas VALUES (1, 23, 'Etapa 1', 1);
INSERT INTO etapas VALUES (2, 12, 'Etapa 2', 1);


--
-- TOC entry 1963 (class 0 OID 0)
-- Dependencies: 170
-- Name: persona_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('persona_id_seq', 1, true);


--
-- TOC entry 1949 (class 0 OID 122641)
-- Dependencies: 171
-- Data for Name: personas; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO personas VALUES (1, 'Tincho', '0.44276973578279777');


--
-- TOC entry 1964 (class 0 OID 0)
-- Dependencies: 172
-- Name: proyecto_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('proyecto_id_seq', 1, true);


--
-- TOC entry 1951 (class 0 OID 122649)
-- Dependencies: 173
-- Data for Name: proyectos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO proyectos VALUES (1, 'Proyecto A', 1);


--
-- TOC entry 1965 (class 0 OID 0)
-- Dependencies: 174
-- Name: tarea_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tarea_id_seq', 5, true);


--
-- TOC entry 1953 (class 0 OID 122654)
-- Dependencies: 175
-- Data for Name: tareas; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tareas VALUES (1, 'Tarea A - Etapa 1', 80, 1);
INSERT INTO tareas VALUES (2, 'Tarea B - Etapa 1', 62, 1);
INSERT INTO tareas VALUES (3, 'Tarea A - Etapa 2', 95, 2);
INSERT INTO tareas VALUES (4, 'Tarea B - Etapa 2', 79, 2);
INSERT INTO tareas VALUES (5, 'Tarea C - Etapa 2', 67, 2);


--
-- TOC entry 1830 (class 2606 OID 122658)
-- Name: etapas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY etapas
    ADD CONSTRAINT etapas_pkey PRIMARY KEY (id);


--
-- TOC entry 1832 (class 2606 OID 122660)
-- Name: personas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY personas
    ADD CONSTRAINT personas_pkey PRIMARY KEY (id);


--
-- TOC entry 1834 (class 2606 OID 122662)
-- Name: proyectos_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY proyectos
    ADD CONSTRAINT proyectos_pkey PRIMARY KEY (id);


--
-- TOC entry 1836 (class 2606 OID 122664)
-- Name: tareas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tareas
    ADD CONSTRAINT tareas_pkey PRIMARY KEY (id);


--
-- TOC entry 1839 (class 2606 OID 122665)
-- Name: fk_70f16c49d03b4fe0ab5dfea76e5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tareas
    ADD CONSTRAINT fk_70f16c49d03b4fe0ab5dfea76e5 FOREIGN KEY (etapaid) REFERENCES etapas(id);


--
-- TOC entry 1838 (class 2606 OID 122670)
-- Name: fk_ad6e1698deb4495a919060a1e51; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY proyectos
    ADD CONSTRAINT fk_ad6e1698deb4495a919060a1e51 FOREIGN KEY (responsable) REFERENCES personas(id);


--
-- TOC entry 1837 (class 2606 OID 122675)
-- Name: fk_b432aa298d6542a1bb295eae5eb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY etapas
    ADD CONSTRAINT fk_b432aa298d6542a1bb295eae5eb FOREIGN KEY (proyectoid_id) REFERENCES proyectos(id);


--
-- TOC entry 1960 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-12-04 16:43:08

--
-- PostgreSQL database dump complete
--

