package school.sorokin.contactmanagement;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ContactManagementSystem {
    private static final String[] names = new String[100];
    private static final String[] phoneNumbers  = new String[100];

    public static int addContact(Scanner scanner, int contactCount) {
        if (contactCount >= 100) {
            System.out.println("Список контактов переполнен");
            return contactCount;
        }
        System.out.println("Введите имя: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Ошибка! Имя контакта не должно быть пустыми");
            return contactCount;
        }

        if (getIndexByName(name, contactCount) != -1) {
            System.out.println("Ошибка! Контакт с именем " + name + " уже добавлен.");
            return contactCount;
        }

        System.out.println("Введите номер телефона: ");
        String phoneNumber = scanner.nextLine().trim();

        if (phoneNumber.isEmpty()) {
            System.out.println("Ошибка! Номер телефона не должен быть пустыми");
            return contactCount;
        }

        names[contactCount] = name;
        phoneNumbers[contactCount] = phoneNumber;

        return ++contactCount;
    }

    public static void getAll(int contactCount) {
        if (contactCount == 0) {
            System.out.println("Список контактов пуст.");
        } else {
            for (int i = 0; i < contactCount; i++) {
                System.out.printf("%d.%s - %s\n", i + 1, names[i], phoneNumbers[i]);
            }
        }
    }

    public static void findContactByName(Scanner scanner, int contactCount) {
        System.out.println("Введите имя контакта для поиска:");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Ошибка! Имя контакта не должно быть пустым.");
            return;
        }

        int contactIndex = getIndexByName(name, contactCount);
        if (contactIndex == -1) {
            System.out.printf("Контакт с именем %s не найден.\n", name);
        } else {
            System.out.printf("Телефон %s: %s\n", names[contactIndex], phoneNumbers[contactIndex]);
        }
    }

    public static int getIndexByName(String name, int contactCount) {
        for (int i = 0; i < contactCount; i++) {
            if (names[i].equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean removeContact(Scanner scanner, int contactCount) {
        System.out.println("Имя для удаления:");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Ошибка! Имя контакта не должно быть пустым.");
            return false;
        }

        int index = getIndexByName(name, contactCount);
        if (index == -1) {
            System.out.println("Контакт с именем " + name + " не найден");
            return false;
        }

        for (int i = index; i < contactCount - 1; i++) {
            names[i] = names[i + 1];
            phoneNumbers[i] = phoneNumbers[i + 1];
        }
        names[contactCount - 1] = null;
        phoneNumbers[contactCount - 1] = null;

        System.out.printf("Контакт \"%s\" удален.\n", name);
        return true;
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
                System.out.println("Ошибка! Введите целое число.");
                scanner.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;
        int contactCount = 0;

        while (isRunning) {
            getMenu();

            int choice = inputInt(scanner);
            scanner.nextLine();

            if (choice < 1 || choice > 5) {
                System.out.println("Некорректный выбор.");
                continue;
            }

            switch (choice) {
                case 1:
                    contactCount = addContact(scanner, contactCount);
                    break;
                case 2:
                    getAll(contactCount);
                    break;
                case 3:
                    findContactByName(scanner, contactCount);
                    break;
                case 4:
                    if (removeContact(scanner, contactCount)) contactCount--;
                    break;
                case 5:
                    isRunning =  false;
                    break;
            }
        }
    }
}
