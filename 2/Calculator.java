package ru.calculator.mvc;

import ru.calculator.mvc.controller.Controller;
import ru.calculator.mvc.model.Model;
import ru.calculator.mvc.view.View;

public class Calculator {
    public static void main(String[] args) {
        Model model = new Model("");
        View view = new View();
        Controller controller = new Controller(model, view);
        controller.calculate();
    }
}