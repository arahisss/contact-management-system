package school.sorokin.contactmanagement;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ContactManagementSystem {
    public static String[] names = new String[100];
    public static String[] phoneNumbers  = new String[100];

    public static void getAll(int contactCount) {
        System.out.println("Список контактов:");
        for (int i = 0; i < contactCount; i++) {
            System.out.println(names[i] + " " + phoneNumbers[i]);
        }
    }

    public static int addContact(Scanner scanner, int contactCount) {
        if (contactCount >= 5) {
            return contactCount;
        }
        System.out.println("Введите имя: ");
        String name = scanner.nextLine();

        System.out.println("Введите номер телефона: ");
        String phoneNumber = scanner.nextLine();

        names[contactCount] = name;
        phoneNumbers[contactCount] = phoneNumber;

        return ++contactCount;
    }

    public static void removeContact() {
    }

    public static void findContactByName(Scanner scanner) {
        System.out.println("Введите имя контакта для поиска:");
        String name = scanner.nextLine();
        int contactIndex = -1;

        for (int i = 0; i < names.length; i++) {
            if (names[i] != null && names[i].equals(name)) {
                contactIndex = i;
                System.out.println("asd");
                break;
            }
        }
        if (contactIndex == -1) {
            System.out.println("Контакт не найден");
        } else {
            System.out.println(names[contactIndex] + " " + phoneNumbers[contactIndex]);
        }
    }

    public static void getMenu() {
        String menu = """
                
                Выберите действие:
                1. Добавить контакт
                2. Просмотреть контакты
                3. Найти контакт
                4. Удалить контакт
                5. Выйти""";
        System.out.println(menu);
    }

    public static int inputInt(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Введите число от 1 до 5");
                scanner.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        int contactCount = 0;

        while (contactCount < 100 && isRunning) {
            getMenu();

            int choice = inputInt(scanner);
            scanner.nextLine();

            if (choice < 1 || choice > 5) {
                System.out.println("Некорректный выбор.");
                getMenu();
            }

            switch (choice) {
                case 1:
                    contactCount = addContact(scanner, contactCount);
                    break;
                case 2:
                    getAll(contactCount);
                    break;
                case 3:
                    findContactByName(scanner);
                    break;
                case 4:
                    continue;
                case 5:
                    isRunning =  false;
                    break;
                default:
            }
        }

    }
}
