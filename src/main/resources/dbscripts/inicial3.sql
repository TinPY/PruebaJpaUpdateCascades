create table pruebajpa.public.agente_tarea (
        id int4 not null,
        costo numeric(19, 2),
        funcion varchar(50),
        horasdedicadas int4,
        observacion varchar(255),
        agenteid int4 not null,
        tareaid int4 not null,
        primary key (id)
    );
    create table pruebajpa.public.etapas (
        id int4 not null,
        avance int4,
        nombreetapa varchar(255) not null,
        proyectoid int4 not null,
        primary key (id)
    );
    create table pruebajpa.public.personas (
        id int4 not null,
        nombre varchar(255),
        telefono varchar(255),
        primary key (id)
    );
    create table pruebajpa.public.presupuesto_tarea (
        id int4 not null,
        aportecomitente numeric(19, 2),
        aporteorganismo numeric(19, 2),
        aporteuniversidad numeric(19, 2),
        cantidad numeric(19, 2),
        costounitario numeric(19, 2),
        descripcion varchar(255),
        total numeric(19, 2),
        rubroid int4,
        tareaid int4,
        primary key (id)
    );
    create table pruebajpa.public.proyectos (
        id int4 not null,
        nombreproyecto varchar(255) not null,
        responsable int4,
        primary key (id)
    );
    create table pruebajpa.public.rubro (
        id int4 not null,
        rubro varchar(255),
        primary key (id)
    );
    create table pruebajpa.public.tareas (
        id int4 not null,
        nombretarea varchar(255) not null,
        prioridad int4,
        etapaid int4 not null,
        primary key (id)
    );
    alter table pruebajpa.public.agente_tarea 
        add constraint FK_9a32422904644576bdd4ef7b790 
        foreign key (agenteid) 
        references pruebajpa.public.personas;
    alter table pruebajpa.public.agente_tarea 
        add constraint FK_2c56cd0790a44486a91066f8426 
        foreign key (tareaid) 
        references pruebajpa.public.tareas;
    alter table pruebajpa.public.etapas 
        add constraint FK_921f4e4f2f154cb9affd4517ba3 
        foreign key (proyectoid) 
        references pruebajpa.public.proyectos;
    alter table pruebajpa.public.presupuesto_tarea 
        add constraint FK_17c3c7fd41914583b178c527aca 
        foreign key (rubroid) 
        references pruebajpa.public.rubro;
    alter table pruebajpa.public.presupuesto_tarea 
        add constraint FK_b6845fc8321d454a8432ba1bcfa 
        foreign key (tareaid) 
        references pruebajpa.public.tareas;
    alter table pruebajpa.public.proyectos 
        add constraint FK_167bfb59096c4e91aebfa77df96 
        foreign key (responsable) 
        references pruebajpa.public.personas;
    alter table pruebajpa.public.tareas 
        add constraint FK_323b0b5877e14d71a8e8ea52999 
        foreign key (etapaid) 
        references pruebajpa.public.etapas;
    create sequence agente_tarea_id_seq;
    create sequence etapa_id_seq;
    create sequence persona_id_seq;
    create sequence proyecto_id_seq;
    create sequence public.presupuesto_tarea_id_seq;
    create sequence rubro_id_seq;
    create sequence tarea_id_seq;