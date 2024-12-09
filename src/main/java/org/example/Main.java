package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // Создание контекста приложения на основе аннотаций, указав базовый пакет
        ApplicationContext context = new AnnotationConfigApplicationContext("org.example");

        // Получение бина основного приложения
        ConsoleApplication consoleApplication = context.getBean(ConsoleApplication.class);

        // Запуск основного метода приложения
        consoleApplication.run();
    }

}