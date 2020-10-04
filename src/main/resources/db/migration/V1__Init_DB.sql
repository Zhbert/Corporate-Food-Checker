create table dinners (
    id integer not null,
    description varchar(255),
    name varchar(255),
    primary key (id)
) engine=MyISAM;

create table dinner_set_admin (
    id bigint not null,
    dinner_date date,
    dinner_id integer,
    dinner_two_id integer,
    primary key (id)
) engine=MyISAM;

create table hibernate_sequence (
    next_val bigint
) engine=MyISAM;

insert into hibernate_sequence values ( 1 );

insert into hibernate_sequence values ( 1 );

insert into hibernate_sequence values ( 1 );

insert into hibernate_sequence values ( 1 );

insert into hibernate_sequence values ( 1 );

create table message (
    id integer not null,
    filename varchar(255),
    tag varchar(255),
    text varchar(255),
    user_id bigint,
    primary key (id)
) engine=MyISAM;

create table user_choices (
    id bigint not null,
    date date,
    day_of_week varchar(255),
    dinner_id integer,
    user_id bigint,
    primary key (id)
) engine=MyISAM;

create table user_role (
    user_id bigint not null,
    roles varchar(255)
) engine=MyISAM;

create table usr (
    id bigint not null,
    active bit not null,
    password varchar(255),
    username varchar(255),
    primary key (id)
) engine=MyISAM;

alter table dinner_set_admin
    add constraint FKge4p7fwtlxqrdd7yuwhv0305j
    foreign key (dinner_id) references dinners (id);

alter table dinner_set_admin
    add constraint FKafm4xv8ks7gut568tg40yg2lv
    foreign key (dinner_two_id) references dinners (id);

alter table message
    add constraint FK70bv6o4exfe3fbrho7nuotopf
    foreign key (user_id) references usr (id);

alter table user_choices
    add constraint FKbrnn2xxq887trgbf9h5p6qu13
    foreign key (dinner_id) references dinners (id);

alter table user_choices
    add constraint FK7m8uanp12v6os83s637vnr6i2
    foreign key (user_id) references usr (id);

alter table user_role
    add constraint FKfpm8swft53ulq2hl11yplpr5
    foreign key (user_id) references usr (id);