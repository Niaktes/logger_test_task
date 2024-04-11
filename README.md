## logger_test_task

Тестовое задание на реализацию простого логгера с двумя путями вывода информации: в консоль и в файл.\
Разработка велась методом TDD.

### Используемые технологии:
* Java 17
* Maven 3.8
* Spring 3.1.0
* * Test
* Lombok 1.18.22
* Checkstyle
* Jacoco

### Описание:
Весь проект логгера состоит из синглтона **Logger**, который определяет соответствие обработчика сообщений уровню сообщения, и рассылает сообщение в нужные обработчики.

Обработчики сообщений - классы, имплементирующие интерфейс **Handler**: **ConsoleHandler** и **FileHandler**. Ожидаемо записывают сообщения в консоль и в файл.

Enum возможных уровней сообщений **Level** содержит уровни TRACE, DEBUG, INFO, WARN, ERROR и FATAL, и их числовые значения.

Последний интерфейс **MessageGatherer** реализован классом **StandardMessageGatherer**, который формирует строку для логгера из уровня и полученного сообщения.

### Контакты для связи: 
> <a href="https://github.com/Niaktes/">Захаренко Сергей</a> <br>
> Телефон: +7 995 299 07 34 <br>
<a href="https://t.me/Niaktes"><img src="https://seeklogo.com/images/T/telegram-logo-AD3D08A014-seeklogo.com.png" alt="Telegram" height="30"></a>
<a href="https://wa.me/89265900734"><img src="https://seeklogo.com/images/W/whatsapp-icon-logo-6E793ACECD-seeklogo.com.png" alt="Whatsapp" height="30"></a>
<a href="mailto:Sergei.Rabota@gmail.com"><img src="https://seeklogo.com/images/G/gmail-logo-0B5D69FF48-seeklogo.com.png" alt="Mail" height="30"></a>