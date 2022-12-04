package com.example.commands;

import com.example.db.SqLite;
import com.example.taxopark.Taxopark;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleMenu {
    public static void mainMenu() {
        SqLite sqlConnector = new SqLite();
        Taxopark tp = sqlConnector.readTaxopark();
        if(tp == null) {
            tp = new Taxopark();
        }
        else {
            sqlConnector.readAuto(tp);
        }

        Command create = new TaxoparkCreate(tp);
        Command addAuto = new TaxoparkAddAuto(tp);
        Command removeAuto = new TaxoparkRemoveAuto(tp);
        Command showList = new TaxoparkShowList(tp);
        Command sortList = new TaxoparkSortList(tp);
        Command searchAuto = new TaxoparkSearchAuto(tp);
        Command totalCost = new TaxoparkShowCost(tp);

        ConsoleMenuExecutor adminConsoleMenuExecutor = new ConsoleMenuExecutor();
        adminConsoleMenuExecutor.register("create", create, "створити таксопарк.");
        adminConsoleMenuExecutor.register("addAuto", addAuto, "додати автомобіль.");
        adminConsoleMenuExecutor.register("delAuto", removeAuto, "вилучити автомобіль.");
        adminConsoleMenuExecutor.register("showList", showList, "показати список автомобілів.");
        adminConsoleMenuExecutor.register("sortList", sortList, "відсортувати автомобілі за зростанням витрати пального.");
        adminConsoleMenuExecutor.register("searchAuto", searchAuto, "знайти автомобіль.");
        adminConsoleMenuExecutor.register("totalCost", totalCost, "показати вартість таксопарку.");

        ConsoleMenuExecutor userConsoleMenuExecutor = new ConsoleMenuExecutor();
        userConsoleMenuExecutor.register("showList", showList, "показати список автомобілів.");
        userConsoleMenuExecutor.register("sortList", sortList, "відсортувати автомобілі за зростанням витрати пального.");
        userConsoleMenuExecutor.register("searchAuto", searchAuto, "знайти автомобіль.");
        userConsoleMenuExecutor.register("totalCost", totalCost, "показати вартість таксопарку.");


        boolean continueСycle = true;
        while (continueСycle) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введіть логін:");
            String login = sc.next();
            switch (login) {
                case "admin":
                    runMenu(login, adminConsoleMenuExecutor);
                    continueСycle = false;
                    sqlConnector.saveAll(tp);
                    break;
                case "user":
                    runMenu(login, userConsoleMenuExecutor);
                    continueСycle = false;
                    break;
                default:
                    System.out.println("Неправильний логін.\n");
            }
        }
    }

    private static void runMenu(String login, ConsoleMenuExecutor adminConsoleMenuExecutor) {
        boolean commandInterface = login.equals("admin");

        HashMap<String, String> nameMap = adminConsoleMenuExecutor.getNameMap();
        int k = 0;
        HashMap<Integer, String> menu = new HashMap<Integer, String>();
        HashMap<Integer, String> commands = new HashMap<Integer, String>();
        for(Map.Entry<String, String> pair : nameMap.entrySet()) {
            String commandName = pair.getKey();
            String menuName = pair.getValue();
            k++;
            menu.put(k, menuName);
            commands.put(k, commandName);
        }

        while (true) {
            Scanner sc = new Scanner(System.in);
            if(!commandInterface) {
                System.out.println("Виберіть дію:");
                for(Map.Entry<Integer, String> pair : menu.entrySet()) {
                    System.out.println("\t" + pair.getKey() + " - " + pair.getValue());
                }
                System.out.println("\t8 - перейти у командний інтерфейс.");
                System.out.println("\t9 - вийти із системи.");
                int choice = sc.nextInt();

                switch (choice) {
                    case 9:
                        return;
                    case 8:
                        commandInterface = true;
                        break;
                    default:
                        if(menu.containsKey((Integer)choice)) {
                            adminConsoleMenuExecutor.execute(commands.get((Integer) choice));
                        }
                        else {
                            System.out.println("Необхідно ввести 1-9 варіанти\n");
                        }
                }
            }
            else {
                System.out.println("Введіть команду (help - підказка):");
                String txt = sc.nextLine();
                String[] array = txt.split("\s");
                String command = array[0];
                String[] parameters = new String[array.length - 1];
                for(int i = 1; i < array.length; i++) {
                    parameters[i-1] = array[i];
                }
                switch (command) {
                    case "exit":
                        return;
                    case "menu":
                        commandInterface = false;
                        break;
                    case "help":
                        ArrayList<String> arCommands = adminConsoleMenuExecutor.availableCommands();
                        for(String cmd: arCommands) {
                            System.out.println(cmd);
                        }
                        System.out.println("menu");
                        System.out.println("help");
                        System.out.println("exit");
                        break;
                    default:
                        adminConsoleMenuExecutor.execute(command, parameters);
                }
            }
        }
    }
}