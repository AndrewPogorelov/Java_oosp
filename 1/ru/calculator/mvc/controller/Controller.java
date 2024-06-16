package ru.calculator.mvc.controller;

import ru.calculator.mvc.model.Model;
import ru.calculator.mvc.view.View;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void calculate() {
        String input = view.getUserInput();
        model.setExpression(input);

        double result = model.calculate();
        view.displayResult(result);
    }
}