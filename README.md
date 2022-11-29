# java-filmorate

**Схема базы данных представлена на рисунке** :sauropod:
![This is an image](https://user-images.githubusercontent.com/32979737/204552408-f148eb70-f1c9-4675-999f-c70636035325.png)

## Примеры запросов для Film:

:large_blue_circle: **GET** 

/films - получить список всех фильмов

/films/{id} - получить фильм по id

 /films/popular - получить список самых популярных фильмов 

:green_circle: **POST** 

/films - создать фильм

:orange_circle: **PUT** 

/films - обновить фильм

/films/{id}/like/{userId} - поставить лайк

:red_circle: **DELETE** 

/films - удалить фильм

/films/{id}/like/{userId} - удалить лайк 

## Примеры запросов для User:
:large_blue_circle: **GET**

/users - получить список всех пользователей

/users/{id} - получить пользователя по id

/users/{id}/friends - получить список друзей пользователя

/users/{id}/friends/common/{otherId} - получить список общих друзей у двух конкретных пользователей

:green_circle: **POST** 

/users - создать пользователя

:orange_circle: **PUT** 

/users - обновить пользователя

/users/{id}/friends/{friendId} - добавить пользователя в друзья

:red_circle: **DELETE** 

/users - удалить пользователя

/users/{id}/friends/{friendId} - удалить пользователя из друзей
