package controllers;

import models.CoffeeMachineModel;
import views.CoffeeMachineView;

import java.util.Scanner;

import static enums.CoffeeVariantEnum.*;
import static enums.StateEnum.*;

public class CoffeeMachineController {
    private CoffeeMachineModel coffeeMachineModel;
    private CoffeeMachineView coffeeMachineView;
    private Scanner scanner;

    public CoffeeMachineController(CoffeeMachineModel coffeeMachineModel, CoffeeMachineView coffeeMachineView) {
        this.coffeeMachineModel = coffeeMachineModel;
        this.coffeeMachineView = coffeeMachineView;
    }

    public void run() {
        scanner = new Scanner(System.in);
        do {
            coffeeMachineView.printCurrentStateMessage();
            performCurrentStateAction();
        } while (!coffeeMachineModel.isSwitchOffState());
        scanner.close();
    }

    private void performCurrentStateAction() {
        switch (coffeeMachineModel.getState()) {
            case ACTION_CHOOSING:
                performInputAction();
                break;
            case COFFEE_VARIANT_CHOOSING:
                buy();
                coffeeMachineModel.setState(ACTION_CHOOSING);
                break;
            case WATER_FILLING:
                coffeeMachineModel.fillWater(getUserInput());
                coffeeMachineModel.setState(MILK_FILLING);
                break;
            case MILK_FILLING:
                coffeeMachineModel.fillMilk(getUserInput());
                coffeeMachineModel.setState(COFFEE_FILLING);
                break;
            case COFFEE_FILLING:
                coffeeMachineModel.fillCoffee(getUserInput());
                coffeeMachineModel.setState(CUPS_FILLING);
                break;
            case CUPS_FILLING:
                coffeeMachineModel.fillCups(getUserInput());
                coffeeMachineModel.setState(ACTION_CHOOSING);
                break;
        }
    }

    private void performInputAction() {
        switch (getUserInput()) {
            case "buy":
                coffeeMachineModel.setState(COFFEE_VARIANT_CHOOSING);
                break;
            case "fill":
                coffeeMachineModel.setState(WATER_FILLING);
                break;
            case "take":
                coffeeMachineView.printReceivingAvailableMoneyMessage();
                coffeeMachineModel.setAvailableCashAmount(0);
                break;
            case "remaining":
                coffeeMachineView.printRemainingIngredients();
                break;
            case "exit":
                coffeeMachineModel.setState(SWITCH_OFF);
                break;
        }
    }

    private String getUserInput() {
        return scanner.next();
    }

    private void buy() {
        String message = null;
        switch (getUserInput()) {
            case "1":
                message = coffeeMachineModel.makeCoffeeIfAvailableAndGetMessage(ESPRESSO);
                break;
            case "2":
                message = coffeeMachineModel.makeCoffeeIfAvailableAndGetMessage(LATTE);
                break;
            case "3":
                message = coffeeMachineModel.makeCoffeeIfAvailableAndGetMessage(CAPPUCCINO);
                break;
            case "back":
                break;
        }
        coffeeMachineView.printResultOfBuyMessage(message);
    }
}
