# Персональный проект

Приложение позволяет просматривать стату автоматических сборок в сервисе Azure DevOps

Для показательности примера в приложении используется открытый проект лаборатории, доступный под адресу https://dev.azure.com/rtuitlab/RTU%20IT%20Lab

## Заметки
Организацию и проект (обязательно открытый) можно установить любые, отредактировав значения в `strings.xml`

https://github.com/CAPCHIK/SamsungPersonalProject/blob/master/app/src/main/res/values/strings.xml#L3-L4

## Сторонние инструменты
Проект использует API Azure DevOps - [документация](https://docs.microsoft.com/en-us/rest/api/azure/devops/build/?view=azure-devops-rest-5.1)

Проект использует следующие библиотеки:
* [Retrofit](https://square.github.io/retrofit/)
* [GSON](https://github.com/google/gson)
* [PrettyTime](https://github.com/ocpsoft/prettytime)
* [Picasso](https://square.github.io/picasso/)

## APK

Проверить приложение можно, скачав `app-debug.apk` со [страницы релизов](https://github.com/CAPCHIK/SamsungPersonalProject/releases/tag/v1.0)

## Демонстрация
* Список сценариев сборки

* Экран с информацией о сценарии

* видео демонстрация

