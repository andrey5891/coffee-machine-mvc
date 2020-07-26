package views;

import models.CoffeeMachineModel;

public class CoffeeMachineView {
    private CoffeeMachineModel coffeeMachineModel;

    private static final String REMAINING_INGREDIENTS_TEMPLATE = "The coffee machine has:\n" +
            "%d of water\n" +
            "%d of milk\n" +
            "%d of coffee beans\n" +
            "%d of disposable cups\n" +
            "%d of money";

    public CoffeeMachineView(CoffeeMachineModel coffeeMachineModel) {
        this.coffeeMachineModel = coffeeMachineModel;
    }

    public void printRemainingIngredients() {
        System.out.println(String.format(
                REMAINING_INGREDIENTS_TEMPLATE,
                coffeeMachineModel.getAvailableWaterVolume(),
                coffeeMachineModel.getAvailableMilkVolume(),
                coffeeMachineModel.getAvailableCoffeeWeight(),
                coffeeMachineModel.getAvailableCupNumber(),
                coffeeMachineModel.getAvailableCashAmount()
        ));
    }

    public void printCurrentStateMessage() {
        System.out.println(coffeeMachineModel.getState().getMessage());
    }

    public void printReceivingAvailableMoneyMessage() {
        System.out.println(String.format("I gave you $%d", coffeeMachineModel.getAvailableCashAmount()));
    }

    public void printResultOfBuyMessage(String message) {
        if (message != null) {
            System.out.println(message);
        }
    }
}
