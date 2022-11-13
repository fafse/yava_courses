package ru.croc.task8.src;

import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class Task8 {
    public static void main(String[] args) {
        double number;
        do{
        try {
            System.out.println("Enter a double");
            Scanner cin = new Scanner(System.in);
            number = cin.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println(e);
            System.out.println("You entered not a number.");
            number = -1;
        }
        }while(number<0);
        NumberFormat formattedNumber = NumberFormat.getCurrencyInstance(Locale.JAPAN);
        System.out.println(formattedNumber.format(number));
    }
}
/*
* Предположим, вам необходимо написать небольшую утилиту, которая могла бы печатать ценники в читаемом виде.

Напишите простую программу, которая предлагала бы пользователю ввести число в консоль и выводила его обратно в отформатированном виде.

Чтобы считать число из консоли можно воспользоваться классом Scanner (у него есть удобные методы nextDouble, nextLine, nextInt), указав ему в кач-ве входного потока System.in.

Locale для вывода можете выбрать самостоятельно.

Предусмотрите обработку исключений при некорректном вводе числа.



Примеры работы подобной программы:

Enter a double: 52,30
Result: ₩52


Enter a double: 2,50
Result: 2,50 €


Enter a double: 63,52
Result: ¥63.52


Усложнение, если вам скучновато (необязательно к выполнению)

Доработайте свою программу следующим образом:

Шаг 1: предлагаем пользователю ввести данные, которые позволят нам создать Locale. Если пользователь пропустил ввод этих данных (пустая строка), то используем дефолтную Locale (любую, как и в условии выше).

Шаг 2: просим ввести число.

Шаг 3: Выводим число, отформатированное в соответствии с введёнными пользователем данными.
Предусмотрите обработку возможных ошибок при вводе данных на шаге 1.

* */