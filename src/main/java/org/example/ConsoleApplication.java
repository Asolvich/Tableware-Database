package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

/**
 * Основной класс для запуска консольного приложения.
 * Предоставляет интерфейс для работы с пользователем и взаимодействия с сервисом.
 */
@Component
public class ConsoleApplication {

    @Autowired
    private TablewareService service;

    /**
     * Метод для запуска консольного приложения.
     * Реализует простейший интерфейс для работы с пользователем.
     *
     * @param args Массив аргументов командной строки (не используется).
     */
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Добавить посуду");
            System.out.println("2. Показать все записи");
            System.out.println("3. Редактировать запись");
            System.out.println("4. Удалить запись");
            System.out.println("5. Поиск по типу");
            System.out.println("0. Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addTableware(scanner);
                case 2 -> showAll();
                case 3 -> updateTableware(scanner);
                case 4 -> deleteTableware(scanner);
                case 5 -> searchByType(scanner);
                case 0 -> {
                    System.out.println("Выход...");
                    return;
                }
                default -> System.out.println("Неверный выбор!");
            }
        }
    }

    /**
     * Метод для добавления новой посуды в таблицу.
     *
     * @param scanner Объект для чтения ввода пользователя.
     */
    private void addTableware(Scanner scanner) {
        try {
            System.out.println("Введите название:");
            String name = scanner.nextLine();

            System.out.println("Введите материал:");
            String material = scanner.nextLine();

            System.out.println("Введите тип:");
            String type = scanner.nextLine();

            System.out.println("Введите объем в мл:");
            int volumeMl = scanner.nextInt();

            System.out.println("Введите цену в рублях:");
            int priceRub = scanner.nextInt();

            Tableware tableware = new Tableware(name, material, type, volumeMl, priceRub);
            service.add(tableware);
            System.out.println("Запись добавлена!");
        }catch (Exception e) {
            System.out.println("Ошибка при добавлении посуды: " + e.getMessage());
        }
    }

    /**
     * Метод для отображения всех записей.
     */
    private void showAll() {
        List<Tableware> tablewareList = service.getAll();
        if (tablewareList.isEmpty()){
            System.out.println("Посуда не найдена.");
        }
        tablewareList.forEach(System.out::println);
    }

    /**
     * Метод для редактирования записи по ID.
     *
     * @param scanner Объект для чтения ввода пользователя.
     */
    private void updateTableware(Scanner scanner) {
        try {
            System.out.println("Введите ID записи для редактирования:");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Введите новое название:");
            String name = scanner.nextLine();

            System.out.println("Введите новый материал:");
            String material = scanner.nextLine();

            System.out.println("Введите новый тип:");
            String type = scanner.nextLine();

            System.out.println("Введите новый объем в мл:");
            int volumeMl = scanner.nextInt();

            System.out.println("Введите новую цену в рублях:");
            int priceRub = scanner.nextInt();

            Tableware updated = new Tableware(name, material, type, volumeMl, priceRub);
            service.update(id, updated);
            System.out.println("Запись обновлена!");

        }catch (Exception e) {
            System.out.println("Ошибка при обновлении записи: " + e.getMessage());
        }
    }

    /**
     * Метод для удаления записи по ID.
     *
     * @param scanner Объект для чтения ввода пользователя.
     */
    private void deleteTableware(Scanner scanner) {
        try {
        System.out.println("Введите ID записи для удаления:");
        int id = scanner.nextInt();
        service.delete(id);
        System.out.println("Запись удалена!");
        }catch (Exception e) {
            System.out.println("Ошибка при удалении записи: " + e.getMessage());
        }
    }

    /**
     * Метод для поиска посуды по материалу.
     *
     * @param scanner Объект для чтения ввода пользователя.
     */
    private void searchByType(Scanner scanner) {
        System.out.println("Введите материал для поиска:");
        String type = scanner.nextLine();
        List<Tableware> results = service.searchByType(type);
        if (results.isEmpty()){System.out.println("Совпадения не найдены.");}
        results.forEach(System.out::println);
    }
}
