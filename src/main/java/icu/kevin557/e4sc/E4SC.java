package icu.kevin557.e4sc;

import java.io.*;

public class E4SC {

    public static void main(String[] args) {
        parse(args);
    }

    public static void parse(String[] args) {

        File file;
        String fileName = null;

        try {
            fileName = args[0];
            file = new File(fileName);
            String command = args[1];

            switch (command) {
                case "find": {
                    if (args.length == 3) {
                        int pos = Processes.find(file, args[2]);
                        if (pos == -1) Helper.notFound(args[2]);
                        else Helper.foundAt(args[2], pos);
                    } else Helper.usageFind();
                    break;
                }
                case "get": {
                    if (args.length == 4) {
                        String value = Processes.get(file, args[2], args[3]);
                        if (value == null) Helper.notFound(args[3]);
                        else Helper.got(args[2], args[3], value);
                    } else Helper.usageGet();
                    break;
                }
                case "set": {
                    if (args.length == 5) {
                        boolean flag = Processes.set(file, args[2], args[3], args[4]);
                        if (flag) Helper.set(args[2], args[3], args[4]);
                        else Helper.setFail();
                    } else Helper.usageSet();
                    break;
                }
                case "setall":
                case "setAll": {
                    if (args.length == 4) {
                        Processes.setAll(file, args[2], args[3]);
                    } else Helper.usageSetAll();
                    break;
                }
                default: {
                    Helper.usage();
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            Helper.notExists(fileName);
        } catch (ArrayIndexOutOfBoundsException e) {
            Helper.usage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
