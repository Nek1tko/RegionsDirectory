# RegionsDirectory
## Spring Boot приложение для справочника регионов  
Стек технологий: Java 8, Maven, Spring Boot, Spring Cache, Swagger, JUnit 4  
Для запуска приложения необходимо предустановить Docker контейнер  

## Запуск приложения  
* git clone  
* cd RegionDirectory  
* docker build . -t regions:1.0-SNAPSHOT  
* docker run -d -p 8080:8080 regions:1.0-SNAPSHOT

## О приложении  
Доступные эндпоинты, модели и коды ответа можно посмотреть здесь: <http://localhost:8080/swagger-ui/#/>
## В приложении реализован кэш:  
* после извлечения или добавления записи, выполняется кэширование
* после обновления записи, выполняется ее обновление в кэше, если записи еще нет в кэше, то выполняется кэширование
* после удаления записи, она также удаляется из кэша, если она там есть
