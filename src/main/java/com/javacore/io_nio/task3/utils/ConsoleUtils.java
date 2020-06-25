package main.java.com.javacore.io_nio.task3.utils;

public final class ConsoleUtils {

    public static void printSuccessMessage(boolean result) {
        if (result) {
            System.out.println("Данная операция выполнена успешно");
        } else {
            System.out.println("Данная операция не выполнена");
        }
    }

    public static Integer parseStringForId(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static void processDeleteRequest(String result) {
        if (result.equals("OK")) {
            System.out.println("Данная операция выполнена успешно");
        } else {
            System.out.println(result);
        }
    }

    public static String printDeleteMessage(int projects, int devs, int accs) {
        return "Удалено проектов: " + projects + ", удалено разработчиков: " + devs +
            ", удалено счетов: " + accs;
    }
}
