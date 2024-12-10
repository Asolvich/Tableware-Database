# Tableware-Database
Console Java application that works with PostgreSQL.

## Описание
Простое консольное Java приложение для работы с PostgreSQL.

## Сборка и запуск
### Требования
- Java 17 (или выше)
- Maven 3.6.0 (или выше)

### Сборка
Чтобы собрать приложение, необходимо выполнить следующие действия:
1. Скопируйте репозиторий.
2. Откройте папку с проектом.
3. Откройте консоль в этой папке и введите команду:
```cmd
mvn clean package
```
4. Запустите скрипт ```init.sql``` с помощью следующей команды:
```cmd
psql -U postgres -d postgres -f init.sql
```
(Если PostgreSQL уже установлен, но psql не найден, нужно добавить путь к папке с исполняемым файлом psql в переменную окружения PATH.)

### Запуск
После выполнения команды откройте папку *target* и выполните команду:
```cmd
java -jar Tableware-Database-1.0-SNAPSHOT.jar
```
## Документация

### Задачи
Описать класс сущности, который имеет как минимум три текстовых поля и два числовых (и, естественно, id). 
Она описывает некий товар.
Создать в СУБД PostgreSQL таблицу базы данных, соответствующую спроектированной сущности.
Реализовать консольное Spring приложение (должно иметь простейший консольный пользовательский интерфейс), которое должно позволять:
- Вводить (консольный ввод) пользователю поля сущности и добавлять её в таблицу БД.
- Выводить в консоль все записи из таблицы БД.
- Редактировать запись таблицы БД по id.
- Удалять запись по Id.
- Осуществлять поиск по любому из признаков (поиск по типу). 

### Реализовано
- Создана структура проекта Spring для управления базой данных посуды.
- Реализован консольный интерфейс для взаимодействия с базой данных, включая добавление, редактирование, удаление, вывод и поиск записей.
- Использованы DAO и JdbcTemplate для работы с базой данных.
- Подготовлен SQL-скрипт для автоматического создания базы данных и наполнения таблицы начальными данными.
- Настроено конфигурирование базы данных через Spring @Configuration.

#### Описание классов
- **Class DatabaseConfig** это класс настраивает DataSource и JdbcTemplate для работы с базой данных.
- **Class Tableware:** Класс-сущность, представляющий посуду. 
id (автоматически генерируется в БД). name, material, type, volume_ml, price_rub.
- **Class TablewareDAO:** Data Access Object для работы с таблицей tableware. 
add — добавление новой записи.
getAll — получение всех записей.
update — обновление записи по ID.
delete — удаление записи по ID.
searchByType — поиск записей по типу.
- **Class TablewareService:** Сервисный класс для управления посудой. Предоставляет методы для добавления, получения, обновления, удаления и поиска посуды.
- **Class Application:** Класс реализующий консольное приложение для взаимодействия с пользователем.
- **Class Main:** Главный класс, который запускает работу программы.

### Описание ресурсов
- **application.properties:** файл в котором определяются настройки, такие как параметры подключения к базе данных, порты, пути.
- **init.sql:** файл, создаёт базу данных, таблицу tableware и заполняет её начальными данными.
