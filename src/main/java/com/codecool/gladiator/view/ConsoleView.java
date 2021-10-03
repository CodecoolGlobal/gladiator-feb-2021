package com.codecool.gladiator.view;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Basic console view implementation
 */
public class ConsoleView implements Viewable {


    @Override
    public void display(String text) {
        System.out.println(text);
    }

    @Override
    public int getNumberBetween(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        do {
            try {
                userInput = scanner.nextInt();
            } catch (InputMismatchException ignore) {
            }
            scanner.nextLine();
        }
        while (!(userInput >= min && userInput <= max));
        return userInput;
    }

}
