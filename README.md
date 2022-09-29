# Homework Citrus 3
**Содержание проекта:** 
- Mock тесты с использованием Citrus Framework v.2.8.0  
- API Helpers (http,soap, sql) с использованием Citrus Framework v.2.8.0  

## В папке mocks/ находятся следующие тесты:

**MockGetListUsersTest** - метод-заглушка "Получение списка пользователей"  

**MockGetListCoursesTest** - метод-заглушка "Получение списка курсов"

**MockGetUserRatingTest** - метод-заглушка "Получение оценки пользователя"


## В папке APIHelpers/ находятся следующие тесты:

**HTTPHelperTest** - http-хелпер для авторизации пользователя с использованиеми сервиса reqres 

**SOAPHelperTest** - soap-хелпер для проверки 20 долларов для сервиса NumberConversion 

**SQLHelperTest** - sql-хелпер для запроса некоторых данных из БД Postrgres и извлечения полученных данных в переменную контекста (БД Postgres развернута в docker-образе) 


## Ресурсы:

[Песочница для REST](https://reqres.in/)

[Песочница для SOAP](https://www.dataaccess.com/)

[Описание домашнего задания](https://github.com/nmochalova/OtusCitrusFramework/blob/main/HomeworkCitrus_3/Doc/Homework_3.docx)


