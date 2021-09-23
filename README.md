# Персональный проект

Приложение позволяет просматривать стату автоматических сборок в сервисе Azure DevOps

Для показательности примера в приложении используется [открытый проект лаборатории RTU IT Lab](https://dev.azure.com/rtuitlab/RTU%20IT%20Lab/_build)

## Заметки
Организацию и проект (обязательно открытый) можно установить собственные, отредактировав значения в `strings.xml`

```xml
   
<resources>
    <string name="app_name">PersonalProject</string>
    <string name="azureDevOpsOrganization">rtuitlab</string>
    <string name="azureDevOpsProject">RTU IT Lab</string>
    ...
</resources>
```
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
| Список сценариев сборки | Экран с информацией о сценарии |
| --- | --- |
|![image](https://user-images.githubusercontent.com/12235437/134478455-76e8279d-dd53-4f26-aef7-44994ad648a5.png)|![image](https://user-images.githubusercontent.com/12235437/134478463-ec363dc2-ad44-41ac-8718-54ee6fb65999.png)|



https://user-images.githubusercontent.com/12235437/134478686-f3bf4e83-6141-4f8a-bb91-bf71bf04f8cd.mp4

