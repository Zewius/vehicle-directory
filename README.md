# Автомобильный справочник / Vehicle directory

REST-сервис, предоставляющий информацию о транспортных средствах из базы данных.

Написан на языке Java, используются такие технологии как [Spring Framework](https://spring.io) 
([Spring Data](https://spring.io/projects/spring-data), [Spring Web](https://spring.io/projects/spring-framework), 
[Spring HATEOAS](https://spring.io/projects/spring-hateoas)), [Spring Boot](https://spring.io/projects/spring-boot) 
и СУБД [PostgreSQL](https://www.postgresql.org).

Был выполнен в качестве тестового задания от компании "Эмбедика".

## Настройка

### База данных

Прежде чем запустить программу, необходимо создать базу данных и таблицу. 
В качестве базы данных используется БД PostgreSQL.

Создание таблицы:

```postgresql
CREATE TABLE IF NOT EXISTS vehicle_list (
reg_number      VARCHAR(9)  NOT NULL PRIMARY KEY,
brand           VARCHAR(15) NOT NULL,
color           VARCHAR(20) NOT NULL,
production_year INT         NOT NULL);
```

### Spring Boot

После того как таблица была создана, необходимо внести свои изменения в файле **application.properties**. 
В этом файле нужно указать:

1. spring.datasource.url - адрес базы данных;
2. spring.datasource.username - имя пользователя PostgreSQL;
3. spring.datasource.password - пароль.

## API

### Основные методы

|                    Метод                    |                      Описание                      |                                   Параметры                                   | Возвращаемый результат  |
|:-------------------------------------------:|:--------------------------------------------------:|:-----------------------------------------------------------------------------:|:-----------------------:|
|     GET<br/>localhost:8080/api/vehicles     |         Получения списка всех автомобилей          |                                Нет параметров                                 |  Список всех объектов   |
|  GET<br/>localhost:8080/api/vehicles/{ID}   | Получение информации об автомобиле с номером _ID_  |                                Нет параметров                                 |  Объект с номером _ID_  |
|    POST<br/>localhost:8080/api/vehicles     |               Добавление автомобиля                | JSON с параметрами объекта - регистрационный номер, марка, цвет и год выпуска |   Результат операции    |
| DELETE<br/>localhost:8080/api/vehicles/{ID} |         Удаление автомобиля с номером _ID_         |                                Нет параметров                                 |   Результат операции    |

### Служебные методы

|               Метод                |                                     Описание                                     |   Параметры    |             Возвращаемый результат              |
|:----------------------------------:|:--------------------------------------------------------------------------------:|:--------------:|:-----------------------------------------------:|
| GET<br/>localhost:8080/api/status  | Получение информации о базе данных: количество записей и занимаемый объем памяти | Нет параметров | Объект с полями: numberOfEntries, databaseSize  |

### Формат данных

Пример: **GET localhost:8080/api/vehicles/{ID}**

```json
{
    "registrationNumber": "А999АА999",
    "brand": "Tesla",
    "color": "Blue",
    "productionYear": 2018
}
```

1. registrationNumber - регистрационный номер автомобиля российского образца (используется кириллица);
2. brand - торговая марка автомобиля;
3. color - цвет автомобиля;
4. productionYear - год выпуска автомобиля

