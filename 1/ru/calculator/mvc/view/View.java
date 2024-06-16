package ru.calculator.mvc.view;

import java.util.Scanner;

public class View {
    private Scanner scanner;

    public View() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        System.out.print("Enter expression: ");
        return scanner.nextLine();
    }

    public void displayResult(double result) {
        System.out.println("Result: " + result);
    }
}