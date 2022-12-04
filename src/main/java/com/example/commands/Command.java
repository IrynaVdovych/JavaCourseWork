package com.example.commands;

public interface Command {
    void execute();
    void execute(String[] params);
}
