create table image
(
    id     int auto_increment
        primary key,
    base64 tinytext     not null,
    name   varchar(100) not null,
    type   varchar(100) not null,
    constraint UK2o7ijfxp9nv014xfgn93go7cd
        unique (name)
);

create table grupo
(
    id          int auto_increment
        primary key,
    description tinytext     null,
    name        varchar(100) not null,
    image_id    int          null,
    constraint FK6fy8lxgcn6603l6iominq3o0r
        foreign key (image_id) references image (id)
);

create table evento
(
    id          int auto_increment
        primary key,
    description varchar(100) not null,
    fecha       date         not null,
    title       varchar(100) not null,
    id_grupo    int          not null,
    constraint FKsdf3n3et9n9c3mhqnd3o9rl4l
        foreign key (id_grupo) references grupo (id)
);

create table noticia
(
    id          int auto_increment
        primary key,
    description tinytext     not null,
    fecha       date         null,
    title       varchar(100) not null,
    image_id    int          null,
    constraint FK6bdlkc1ugaof02yalktc5p4ji
        foreign key (image_id) references image (id)
);

create table tramo
(
    id          int auto_increment
        primary key,
    description varchar(100) not null,
    enter       date         not null,
    go_out      date         not null
);

create table usuario
(
    id            int auto_increment
        primary key,
    activo        bit      default b'1'      not null,
    direccion     varchar(100)               not null,
    fecha_ingreso date                       not null,
    dni           varchar(100)               not null,
    email         varchar(100)               not null,
    nombre        varchar(100)               not null,
    pass          varchar(100)               not null,
    telefono      varchar(100)               null,
    rol           tinytext default 'usuario' not null
);

create table sesiones
(
    id               int auto_increment
        primary key,
    active           bit      not null,
    fecha_emision    bigint   not null,
    fecha_expiracion bigint   not null,
    finguer_print    tinytext not null,
    token            tinytext not null,
    id_usuario       int      not null,
    constraint FKthjjabg8m0bxivlky8os500og
        foreign key (id_usuario) references usuario (id)
);

create table tramo_grupo_usuario
(
    id         int auto_increment
        primary key,
    id_grupo   int not null,
    id_tramo   int not null,
    id_usuario int not null,
    constraint uk_tramo_usuario
        unique (id_tramo, id_usuario),
    constraint FK4j9v0dtqwr2kevx74psr00imm
        foreign key (id_usuario) references usuario (id),
    constraint FKgguef3wlyfxmeg44j1pdjultw
        foreign key (id_grupo) references grupo (id),
    constraint FKl2iqei1016pi3njjuwpli1806
        foreign key (id_tramo) references tramo (id)
);

