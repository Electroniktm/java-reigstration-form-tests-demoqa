# Автоматизированные тесты форм DemoQA

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.org/)
[![Selenide](https://img.shields.io/badge/Selenide-7.16.1-blue.svg)](https://selenide.org/)
[![JUnit 5](https://img.shields.io/badge/JUnit-5-green.svg)](https://junit.org/junit5/)
[![Chrome](https://img.shields.io/badge/Браузер-Chrome-lightgrey.svg)](https://www.google.com/chrome/)

> Современная автоматизация UI-тестирования веб-форм для [DemoQA Practice Form](https://demoqa.com) на Selenide и JUnit 5.

---

## 📋 Содержание

- [Обзор](#обзор)
- [Технологии](#технологии)
- [Структура проекта](#структура-проекта)
- [Покрытие тестами](#покрытие-тестами)
- [Начало работы](#начало-работы)
- [Запуск тестов](#запуск-тестов)
- [Принятые решения](#принятые-решения)
- [Лучшие практики](#лучшие-практики)

---

## 🎯 Обзор

Этот проект демонстрирует современное UI-автоматизированное тестирование веб-форм, включая:

- **Регистрационная форма** — полная проверка заполнения и валидации
- **Текстовое поле** — обработка полей ввода и проверка сохранения данных

Набор тестов охватывает успешные сценарии, пограничные случаи и негативные сценарии для обеспечения полного тестирования надёжности форм.

---

## 🛠 Технологии

| Категория | Технология | Версия |
|------|------|---------|
| **Язык** | Java | 17 |
| **Тестовый фреймворк** | JUnit Jupiter | 5.11.4 |
| **Обёртка Selenium** | Selenide | 7.16.1 |
| **Сборка** | Gradle | 9.3.0 |
| **Браузер** | Chrome | Актуальная |

---

## 📁 Структура проекта

```
java-reigstration-form-tests-demoqa/
├── build.gradle                          # Конфигурация сборки
├── README.MD                             # Этот файл
├── gradle/
│   └── wrapper/                          # Файлы Gradle wrapper
└── src/
    └── test/
        ├── java/
        │   └── tests/
        │       ├── TestBase.java         # Базовая конфигурация тестов
        │       ├── RegistrationFormTests.java  # Тесты регистрационной формы
        │       └── TextBoxTests.java     # Тесты текстового поля
        └── resources/
            └── test.png                  # Тестовый ресурс
```

---

## 🧪 Покрытие тестами

### Тесты регистрационной формы ([`RegistrationFormTests.java`](src/test/java/tests/RegistrationFormTests.java))

| Тест | Описание | Тип |
|------|------|------|
| `fillAllFieldsAndSubmitTest` | Полное заполнение формы со всеми проверками | ✅ Сценарий успеха |
| `fillOnlyRequiredFieldsTest` | Минимальная валидная отправка | ✅ Сценарий успеха |
| `invalidEmailFormatTest` | Обработка ошибки формата email | ❌ Отрицательный |
| `missingGenderSelectionTest` | Валидация обязательного поля | ❌ Отрицательный |
| `invalidPhoneNumberTest` | Валидация номера телефона | ❌ Отрицательный |
| `tooLongNameTest` | Обработка длинных строк | ⚠️ Крайний случай |
| `emptyRequiredFieldsTest` | Отправка пустой формы | ❌ Отрицательный |

### Тесты текстового поля ([`TextBoxTests.java`](src/test/java/tests/TextBoxTests.java))

| Тест | Описание | Тип |
|------|------|------|
| `successfulFillFormTest` | Полное заполнение валидными данными | ✅ Сценарий успеха |
| `textBoxMinimalFieldsTest` | Отправка только обязательных полей | ✅ Сценарий успеха |
| `textBoxEmptyFieldsTest` | Отправка пустой формы | ❌ Отрицательный |
| `textBoxTooLongNameTest` | Обработка длинных строк | ⚠️ Крайний случай |
| `failEmailFillFormTest` | Валидация некорректного email | ❌ Отрицательный |

---

## 🚀 Начало работы

### Требования

- **Java Development Kit (JDK)** 17 или выше
- **Chrome Browser** (актуальная версия)
- **Gradle** 9.3+ (или используйте встроенный wrapper)

### Установка

```bash
# Клонируйте репозиторий
git clone <url-репозитория>
cd java-reigstration-form-tests-demoqa

# (Опционально) Сделайте Gradle wrapper исполняемым
chmod +x gradlew
```

---

## ▶️ Запуск тестов

### Запуск всех тестов

```bash
.\gradlew.bat test
```

### Запуск конкретного класса тестов

```bash
.\gradlew.bat test --tests "RegistrationFormTests"
.\gradlew.bat test --tests "TextBoxTests"
```

### Запуск конкретного метода

```bash
.\gradlew.bat test --tests "RegistrationFormTests.fillAllFieldsAndSubmitTest"
```

### Запуск с подробным выводом

```bash
.\gradlew.bat test --info
```

---

## 🎨 Принятые решения

### Текстовый поиск элементов

Все выпадающие списки и чекбоксы используют **текстовые селекторы** вместо индексных:

```java
// ✅ Предпочтительно: текстовый поиск (устойчиво к изменениям DOM)
$("#genterWrapper").$$("label").findBy(text("Male")).click();
$("[id='react-select-3-input']").setValue("Uttar Pradesh").pressEnter();

// ❌ Избегайте: индексный поиск (хрупкий)
$("[id=react-select-3-option-1]").click();
```

### Загрузка ресурсов через classpath

Загрузка файлов использует `uploadFromClasspath` для независимости от окружения:

```java
// ✅ Чисто и переносимо
$("#uploadPicture").uploadFromClasspath("test.png");

// ❌ Зависит от окружения
$("#uploadPicture").uploadFile(new File("src/test/resources/test.png"));
```

### Паттерны Selenide

- **Цепочечные селекторы** для вложенных элементов: `$("[id=state]").$(text("Ohio"))`
- **Интеграция с React-select**: прямое управление через `react-select-*-input`
- **Проверка CSS-границ**: `cssValue("border-color", "rgb(220, 53, 69)")` для состояний ошибок

---

## 📐 Лучшие практики

| Практика | Реализация |
|------|------|
| **Page Object Pattern** | Прямой доступ к элементам через селекторы Selenide |
| **Data-Driven Testing** | Общие тестовые данные в [`TestBase.java`](src/test/java/tests/TestBase.java) |
| **Описательные имена тестов** | `invalidEmailFormatTest`, `missingGenderSelectionTest` |
| **Явные ассерции** | `shouldHave(text(...))` для проверок |
| **Чистый код** | Без inline-комментариев; самодокументирующиеся имена тестов |
| **Управление конфигурацией** | Настройки браузера централизованы в `TestBase.beforeALL()` |

---

## 📸 Тестовые ресурсы

| Файл | Назначение |
|------|------|
| [`test.png`](src/test/resources/test.png) | Образец изображения для тестирования загрузки файлов |

---

## 🔧 Конфигурация

Настройки браузера и тестов управляются в [`TestBase.java`](src/test/java/tests/TestBase.java):

```java
Configuration.browserSize = "1920x1280";   // Полноэкранное разрешение
Configuration.browser = "chrome";          // Браузер Chrome
Configuration.baseUrl = "https://demoqa.com";  // Базовый URL
```

---

## 📝 Тестовые данные

| Поле | Корректное значение | Некорректное значение |
|------|------|------|
| ФИО | `John Doe` | `JohnJohnJohn...` (100+ символов) |
| Email | `john.doe@example.com` | `john.doe@example` |
| Телефон | `1234567890` | `1234567` (слишком короткий) |
| Пол | Male / Female / Other | *(не выбран)* |
| Штат | Uttar Pradesh / Ohio | *(не выбран)* |
| Город | Agra / Columbus | *(не выбран)* |

---

## 🤝 Вклад в проект

1. Следуйте существующим соглашениям об именовании тестов
2. Используйте текстовые селекторы для поиска элементов
3. Добавляйте ассерции для всех пользовательских проверок
4. Делайте тесты независимыми и идемпотентными

---

## 📄 Лицензия

Этот проект создан в образовательных целях. Тесты работают с [demoqa.com](https://demoqa.com) — специальным сайтом для практики автоматизации.

---

<div align="center">

**Создано с ❤️ на Selenide + JUnit 5**

[⬆ Наверх](#автоматизированные-тесты-форм-demoqa)

</div>
