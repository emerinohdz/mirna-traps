create table MiRNA (
    version bigint(20) not null,
    id bigint(20) not null,
    name varchar(255) not null,
    type varchar(255) not null,

    primary key (id)
) engine=INNODB charset=utf8 collate=utf8_spanish_ci;

create table Author (
    version bigint(20) not null,
    id int(11) not null,
    name text not null,

    primary key (id)
) engine=INNODB charset=utf8 collate=utf8_spanish_ci;

create table Disease (
    version bigint(20) not null,
    id bigint(20) not null,
    name text not null,
    mortalityRate float not null,

    primary key (id)
) engine=INNODB charset=utf8 collate=utf8_spanish_ci;

create table DiscoveryMethod (
    version bigint(20) not null,
    id int(11) not null,
    name text not null,

    primary key (id)
) engine=INNODB charset=utf8 collate=utf8_spanish_ci;

create table Publication (
    version bigint(20) not null,
    id bigint(20) not null,
    name text not null,
    description text null,
    journal text not null,
    year smallint(4) not null,
    authorId int(11) not null,

    constraint fk_Publication_authorId foreign key (authorId) 
        references Author(id) on update cascade on delete cascade,

    primary key (id)
) engine=INNODB charset=utf8 collate=utf8_spanish_ci;

create table CorrelationDiscovery (
    version bigint(20) not null,
    id bigint(20) not null,
    potentialBiomarker tinyint(1) not null,
    mirnaId bigint(20) not null,
    diseaseId bigint(20) not null,
    methodId int(11) not null,
    publicationId bigint(20) not null,

    constraint fk_CorrelationDiscovery_mirnaId foreign key (mirnaId) 
        references MiRNA(id) on update cascade on delete cascade,

    constraint fk_CorrelationDiscovery_diseaseId foreign key (diseaseId) 
        references Disease(id) on update cascade on delete cascade,

    constraint fk_CorrelationDiscovery_methodId foreign key (methodId) 
        references DiscoveryMethod(id) on update cascade on delete cascade,

    constraint fk_CorrelationDiscovery_publicationId foreign key (publicationId) 
        references Publication(id) on update cascade on delete cascade,

    primary key (id)
) engine=INNODB charset=utf8 collate=utf8_spanish_ci;

create table ID_GEN (
    ID_NAME varchar(255) not null,
    ID_VAL bigint(20) not null,

    primary key (ID_NAME)
) engine=INNODB;

insert into ID_GEN (ID_NAME, ID_VAL) values ('mirnaId', 0);
insert into ID_GEN (ID_NAME, ID_VAL) values ('diseaseId', 0);
insert into ID_GEN (ID_NAME, ID_VAL) values ('authorId', 0);
insert into ID_GEN (ID_NAME, ID_VAL) values ('correlationDiscoveryId', 0);
insert into ID_GEN (ID_NAME, ID_VAL) values ('discoveryMethodId', 0);
insert into ID_GEN (ID_NAME, ID_VAL) values ('publicationId', 0);