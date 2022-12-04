package com.example.commands;

import java.util.ArrayList;
import java.util.HashMap;

public class ConsoleMenuExecutor {
    private final HashMap<String, Command> commandMap = new HashMap<>();
    private final HashMap<String, String> nameMap = new HashMap<>();
    public void register(String commandName, Command command, String menuName) {
        commandMap.put(commandName, command);
        nameMap.put(commandName, menuName);
    }

    public void execute(String commandName) {
        Command command = commandMap.get(commandName);
        if (command == null) {
            System.out.println("Неправильна команда.");
            return;
        }
        command.execute();
    }

    public void execute(String commandName, String[] params) {
        Command command = commandMap.get(commandName);
        if (command == null) {
            System.out.println("Неправильна команда.");
            return;
        }
        command.execute(params);
    }


    public ArrayList<String> availableCommands() {
       ArrayList<String> commands = new ArrayList<String>();
       commands.addAll(commandMap.keySet());
       return commands;
    }

    public HashMap<String, String> getNameMap() {
        return nameMap;
    }
}
