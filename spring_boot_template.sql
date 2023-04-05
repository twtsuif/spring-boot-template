create database spring_boot_template;

create table permission
(
    id   bigint auto_increment
        primary key,
    name varchar(64) default 'NULL' not null comment '菜单名'
)
    comment '菜单表';

create table role
(
    id   bigint auto_increment
        primary key,
    name varchar(128) null
)
    comment '角色表';

create table role_permission
(
    role_id       bigint auto_increment comment '角色ID',
    permission_id bigint default 0 not null comment '菜单id',
    primary key (role_id, permission_id)
);

create table user
(
    id       bigint auto_increment comment '主键'
        primary key,
    username varchar(64) default 'NULL' not null comment '用户名',
    password varchar(64) default 'NULL' not null comment '密码'
)
    comment '用户表';

create table user_role
(
    user_id bigint auto_increment comment '用户id',
    role_id bigint default 0 not null comment '角色id',
    primary key (user_id, role_id)
);