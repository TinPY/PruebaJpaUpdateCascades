
    alter table pruebajpa.public.etapas 
        drop constraint FK_b432aa298d6542a1bb295eae5eb;
    alter table pruebajpa.public.proyectos 
        drop constraint FK_ad6e1698deb4495a919060a1e51;
    alter table pruebajpa.public.tareas 
        drop constraint FK_70f16c49d03b4fe0ab5dfea76e5;
    drop table if exists pruebajpa.public.etapas cascade;
    drop table if exists pruebajpa.public.personas cascade;
    drop table if exists pruebajpa.public.proyectos cascade;
    drop table if exists pruebajpa.public.tareas cascade;
    drop sequence etapa_id_seq;
    drop sequence persona_id_seq;
    drop sequence proyecto_id_seq;
    drop sequence tarea_id_seq;
    create table pruebajpa.public.etapas (
        id int4 not null,
        avance int4,
        nombreetapa varchar(255) not null,
        proyectoid_id int4,
        primary key (id)
    );
    create table pruebajpa.public.personas (
        id int4 not null,
        nombre varchar(255),
        telefono varchar(255),
        primary key (id)
    );
    create table pruebajpa.public.proyectos (
        id int4 not null,
        nombreproyecto varchar(255) not null,
        responsable int4,
        primary key (id)
    );
    create table pruebajpa.public.tareas (
        id int4 not null,
        nombretarea varchar(255) not null,
        prioridad int4,
        etapaid int4 not null,
        primary key (id)
    );
    alter table pruebajpa.public.etapas 
        add constraint FK_b432aa298d6542a1bb295eae5eb 
        foreign key (proyectoid_id) 
        references pruebajpa.public.proyectos;
    alter table pruebajpa.public.proyectos 
        add constraint FK_ad6e1698deb4495a919060a1e51 
        foreign key (responsable) 
        references pruebajpa.public.personas;
    alter table pruebajpa.public.tareas 
        add constraint FK_70f16c49d03b4fe0ab5dfea76e5 
        foreign key (etapaid) 
        references pruebajpa.public.etapas;
    create sequence etapa_id_seq;
    create sequence persona_id_seq;
    create sequence proyecto_id_seq;
    create sequence tarea_id_seq;