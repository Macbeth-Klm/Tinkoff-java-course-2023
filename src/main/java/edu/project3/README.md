# Проект №3: анализатор логов

## Задание

Лог-файлы являются важной частью работы любого сервера, так как они содержат информацию о том, какие запросы были
отправлены на сервер, какие ошибки возникли и какие действия были выполнены.

Однако, обрабатывать и анализировать эти логи вручную может быть очень трудоемким процессом. Для решения этой проблемы
напишем программу-анализатор логов.

На вход программе через аргументы командной строки задаётся:

* путь к одному или нескольким **NGINX** лог-файлам в виде локального шаблона или **URL**
* опциональные временные параметры **from** и **to** в формате [ISO8601](https://ru.wikipedia.org/wiki/ISO_8601)
* необязательный аргумент формата вывода результата: **markdown** или **adoc**

Примеры вызова программы:

    java -jar nginx-log-stats.jar --path logs/2023* --from 2023-08-31 --format markdown
    java -jar nginx-log-stats.jar --path https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs --format adoc
    java -jar nginx-log-stats.jar --path logs/**/2023-08-31.txt

Программа должна выполнять следующие задачи:

* Подсчитывать общее количество запросов
* Определять наиболее часто запрашиваемые ресурсы
* Определять наиболее часто встречающиеся коды ответа
* Рассчитывать средний размер ответа сервера

## Пример вывода

    #### Общая информация

    |        Метрика        |     Значение |
    |:---------------------:|-------------:|
    |       Файл(-ы)        | `access.log` |
    |    Начальная дата     |   31.08.2023 |
    |     Конечная дата     |            - |
    |  Количество запросов  |       10_000 |
    | Средний размер ответа |         500b |

    #### Запрашиваемые ресурсы

    |     Ресурс      | Количество |
    |:---------------:|-----------:|
    |  `/index.html`  |      5_000 |
    |  `/about.html`  |      2_000 |
    | `/contact.html` |      1_000 |

    #### Коды ответа

    | Код |          Имя          | Количество |
    |:---:|:---------------------:|-----------:|
    | 200 |          OK           |       8000 |
    | 404 |       Not Found       |       1000 |
    | 500 | Internal Server Error |        500 |

Программа-анализатор в конечном счете должна выглядеть как конвейер:

    user input (file names, URL, etc)
    =>
    Stream<LogRecord>
    =>
    LogReport
    =>
    текстовый отчёт в формате .md/.adoc

## Оценка

* Всего за проект можно получить 6 баллов
* За каждую дополнительную статистику можно получить + 1 балл, но не более 2 баллов за всё задание

## Примечания

#### Схема NGINX-логов

    '$remote_addr - $remote_user [$time_local] ' '"$request" $status $body_bytes_sent ' '"$http_referer" "$http_user_agent"'

* Логи нужно трансформировать в типизированное промежуточное представление
* Для работы с датами можно использовать классы **java.time.OffsetDateTime**
* Для чтения файлов можно использовать классы из пакета **NIO**
* Для сбора статистики используйте коллекции из стандартной библиотеки **Java**, например, **Map** и **List**

Примеры логов можно взять
по [ссылке](https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs) или
создать самостоятельно:

    docker run --rm -it -e 'RATE=10000' kscarlett/nginx-log-generator >> $HOME/logs.txt

После запуска подождите какое-то время (5-10с) и отправьте завершающий сигнал **(Ctrl+C)**. Ваши логи будут в файле
**$HOME/logs.txt**