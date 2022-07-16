package icu.kevin557.e4sc;

public final class Helper {

    public static void usage() {
        System.out.println("usage: java -jar e4sc.jar <file> <command> [<args>]");
        System.out.println();
        System.out.println("These are common E4SC commands used in various situations:");
        System.out.println("   find         Find text position in CSV header");
    }

    public static void notExists(String file) {
        System.out.println(file + " is not exists!");
    }

    public static void usageFind() {
        System.out.println("find <header>");
    }

    public static void notFound(String text) {
        System.out.println(text + " not found!");
    }

    public static void foundAt(String text, int pos) {
        System.out.println(text + " has been found at " + pos);
    }

    public static void usageGet() {
        System.out.println("get <line> <header>");
    }

    public static void notNum(String num) {
        System.out.println(num + " is not a number!");
    }

    public static void got(String line, String header, String value) {
        System.out.println("Position line " + line + ", " + header + " value is " + value);
    }

    public static void set(String line, String header, String value) {
        System.out.println("Position line " + line + ", " + header + " has been set to " + value);
    }

    public static void setFail() {
        System.out.println("Setting failed!");
    }

    public static void usageSet() {
        System.out.println("set <line> <header> <value>");
    }

    public static void usageSetAll() {
        System.out.println("setall <header> <value>");
    }
}
