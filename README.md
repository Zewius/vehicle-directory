<h1>Справочник автомобилей / Vehicle directory</h1>
<p>
REST-сервис, предоставляющий информацию о транспортных средствах из базы данных.

Написан на языке Java, используются такие технологии как Spring Framework 
(Spring Data JPA, Spring Web, Spring HATEOAS) и БД PostgreSQL.

Был выполнен в качестве тестового задания от компании "Эмбедика"</p>

<h2>Настройка</h2>
<h3>База данных</h3>
Прежде чем запустить программу, необходимо создать базу данных и таблицу.
В качестве базы данных используется БД PostgreSQL.

Создание таблицы:

<code>
CREATE TABLE IF NOT EXISTS vehicle_list (reg_number varchar(9) not null primary key,
	brand varchar(15) not null,
	color varchar(20) not null,
	production_year int not null)
</code>

<h3>Spring Boot</h3>

После того как таблица была создана, необходимо внести свои изменения в файле
<b>application.properties</b>. В этом файле нужно указать:
<ol>
    <li>spring.datasource.url - адрес базы данных;</li>
    <li>spring.datasource.username - имя пользователя PostgreSQL;</li>
    <li>spring.datasource.password - пароль.</li>
</ol>

<h2>API</h2>
<h3>Основные методы</h3>
<table>
    <tr>
        <td>Метод</td>
        <td>Описание</td>
        <td>Параметры</td>
        <td>Возвращаемый результат</td>
    </tr>
    <tr>
        <td>GET localhost:8080/api/vehicles</td>
        <td>Получения списка всех автомобилей</td>
        <td>Нет параметров</td>
        <td>Список всех объектов</td>
    </tr>
    <tr>
        <td>GET localhost:8080/api/vehicles/{ID}</td>
        <td>Получение информации об автомобиле с номером ID</td>
        <td>Нет параметров</td>
        <td>Объект</td>
    </tr>
    <tr>
        <td>POST localhost:8080/api/vehicles</td>
        <td>Добавление автомобиля</td>
        <td>JSON с параметрами объекта - регистрационный номер, марка, 
        цвет и год выпуска</td>
        <td>Результат операции</td>
    </tr>
    <tr>
        <td>DELETE localhost:8080/api/vehicles/{ID}</td>
        <td>Удаление автомобиля с номером ID</td>
        <td>Нет параметров</td>
        <td>Результат операции</td>
    </tr>
</table>
<h3>Служебные методы</h3>
<table>
    <tr>
        <td>Метод</td>
        <td>Описание</td>
        <td>Параметры</td>
        <td>Возвращаемый результат</td>
    </tr>
    <tr>
        <td>GET localhost:8080/api/status</td>
        <td>Получение информации о базе данных: количество записей 
        и занимаемый объем памяти</td>
        <td>Нет параметров</td>
        <td>Объект с полями: numberOfEntries, databaseSize</td>
    </tr>
</table>
<h3>Формат данных</h3>
<p>
Пример: <b>GET localhost:8080/api/vehicles/{ID}</b>
</p>
<code>
{
    "registrationNumber": "А999АА999",
    "brand": "Tesla",
    "color": "Blue",
    "productionYear": 2018
}
</code>
<ol>
    <li>registrationNumber - регистрационный номер автомобиля 
        российского образца (используется кириллица);</li>
    <li>brand - торговая марка автомобиля;</li>
    <li>color - цвет автомобиля;</li>
    <li>productionYear - год выпуска автомобиля</li>
</ol>

