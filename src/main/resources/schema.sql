create table if not exists USERS
(
    USER_ID   INTEGER auto_increment,
    EMAIL     CHARACTER VARYING(50) not null,
    LOGIN     CHARACTER VARYING(50) not null,
    USER_NAME CHARACTER VARYING(50) not null,
    BIRTHDAY  DATE                  not null,
    constraint USERS_PK
        primary key (USER_ID)
);

create table if not exists MPA
(
    MPA_ID   INTEGER auto_increment,
    MPA_NAME CHARACTER VARYING(10) UNIQUE not null,
    constraint MPA_PK
    primary key (MPA_ID)
);

create table if not exists FILMS
(
    FILM_ID      INTEGER auto_increment,
    FILM_NAME    CHARACTER VARYING(50)  not null,
    DESCRIPTION  CHARACTER VARYING(200) not null,
    RELEASE_DATE DATE                   not null,
    DURATION     INTEGER                not null,
    MPA_ID       INTEGER                not null,
    constraint FILMS_PK
        primary key (FILM_ID),
    constraint FILMS_MPA_MPA_ID_FK
        foreign key (MPA_ID) references MPA
);

create table if not exists GENRE
(
    GENRE_ID   INTEGER auto_increment,
    GENRE_NAME CHARACTER VARYING(50) UNIQUE not null,
    constraint GENRE_PK
    primary key (GENRE_ID)
);

create table if not exists FILM_GENRE
(
    FILM_ID  INTEGER not null,
    GENRE_ID INTEGER not null,
    constraint FILM_GENRE_FILMS_FILM_ID_FK
        foreign key (FILM_ID) references FILMS,
    constraint FILM_GENRE_GENRE_GENRE_ID_FK
        foreign key (GENRE_ID) references GENRE
);

create table if not exists FRIENDSHIP
(
    USER_ID   INTEGER not null,
    FRIEND_ID INTEGER not null,
    constraint FRIENDSHIP_USERS_USER_ID_FK
        foreign key (USER_ID) references USERS,
    constraint FRIENDSHIP_USERS_USER_ID_FK_2
        foreign key (FRIEND_ID) references USERS
);

create table if not exists LIKES
(
    USER_ID INTEGER not null,
    FILM_ID INTEGER not null,
    constraint LIKES_FILMS_FILM_ID_FK
        foreign key (FILM_ID) references FILMS,
    constraint LIKES_USERS_USER_ID_FK
        foreign key (USER_ID) references USERS
);