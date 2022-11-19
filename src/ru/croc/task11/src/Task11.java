package ru.croc.task11.src;

import java.io.IOException;
import java.util.Scanner;

public class Task11 {
    public static void main(String[] args) throws IOException {
        String ip = "192.168.1.109";
        int port = 2022;
        int menu = 0;
        Scanner cin = new Scanner(System.in);
        ru.croc.task11.src.Server obj1 = new ru.croc.task11.src.Server();

        ru.croc.task11.src.Client client = new ru.croc.task11.src.Client();
        do {
            System.out.println("Кто я?");
            System.out.println("1. Сервер\n2. Клиен\n0. Выход");
            menu = cin.nextInt();
            switch (menu) {
                case 1: {
                    obj1.func(port);
                    break;
                }
                case 2: {
                    client.sendSmth(ip, port);
                    break;
                }
                default:{
                    break;
                }
            }
        }while(menu!=0);
    }
}
/*
Конкатенация потоков
InputStream in = new SequenceInputStream(
new FileInputStream(“input.txt.0”),
new FileInputStream(“input.txt.1”));

 */
/*
Создание сокета
Сервер
ServerSocket serverSocket = new ServerSocket(2021); // 0 auto port
Socket socket = serverSocket.accept(); // ожидание соединения

Клиент
Socket socket = new Socket("127.0.0.1", 2021);

* */
/*
Получение и отправка данных

Поток для чтения данных (получаем)
InputStream in = socket.getInputStream();

Поток для записи данных (отправляем)
OutputStream out = socket.getOutputStream();
* */
/*
try (Socket socket = new Socket("cat-fact.herokuapp.com", 80)) {
   Writer w = new OutputStreamWriter(socket.getOutputStream());
   w.write("GET /facts/random HTTP/1.1\r\n");
   w.write("Host: cat-fact.herokuapp.com\r\n");
   w.write("\r\n");
   w.flush();

   InputStream in = socket.getInputStream();
   int b;
   while ((b = in.read()) != -1)
       System.out.write(b);
}

*
 Напишите клиент-серверное приложение (это 2 отдельных приложения).
Сервер ждет подключения пользователей, принимает текстовые сообщения, которые пересылает всем подключенным клиентам.
Клиентское приложение сначала предлагает ввести никнейм, а далее отсылает на сервер каждую строку введённую пользователем
И выводит на консоль все сообщения полученные с сервера.
IP адрес - 127.0.0.1 - ведёт на так называемый localhost. Если запускаете клиент и сервер с одной машины - указывайте его. Если пробуете с кем то сконектиться - то надо указать или его IP адрес в локальной сети (Или если есть внешний IP - можно попробовать законектиться с любой точки мира указав его)

Ожидается в консоли пользователя увидеть чат.
Кому хочется посложнее: - выводите ник пользователя, и время в которое сообщение было отправлено. А кому и этого мало - учитывайте часовой пояс в котором клиентское приложение подключилось, и выводите локальное время в чат каждому пользователю. (Зону можно принять как аргумент запуска или предложить ввести пользователю после никнейма).

Идея не даёт запустить 2 клиента одновременно в своём окружении. Можно запустить через терминал (в том числе и через терминал идеи)

 */