# Homework Citrus 3
**Содержание проекта:** 
- Mock тесты с использованием Citrus Framework v.3.2.1  
- API Helpers

## В папке mocks/ находятся следующие тесты:

**testMockGetListUsers** - метод-заглушка "Получение списка пользователей"  

**testMockGetListCourses** - метод-заглушка "Получение списка курсов"

**testMockGetUserRating** - метод-заглушка "Получение оценки пользователя"


## В папке APIHelpers/ находятся следующие тесты:

**HTTPHelper** - http-хелпер для авторизации пользователя с использованиеми сервиса reqres 

**SOAPHelper** - soap-хелпер для проверки 20 долларов для сервиса NumberConversion 

**SQLHelper** - sql-хелпер для запроса некоторых данных из БД Postrgres и извлечения полученных данных в переменную контекста (БД Postgres развернута в docker-образе) 


## Ресурсы:

[Песочница для REST](https://reqres.in/)

[Песочница для SOAP](https://www.dataaccess.com/)

[Описание домашнего задания](https://github.com/nmochalova/OtusCitrusFramework/blob/main/HomeworkCitrus_3/Doc/Homework_3.docx)


