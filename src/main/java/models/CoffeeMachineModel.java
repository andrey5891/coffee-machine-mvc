package models;

import enums.CoffeeVariantEnum;
import enums.StateEnum;

import static enums.StateEnum.ACTION_CHOOSING;
import static enums.StateEnum.SWITCH_OFF;
import static java.lang.Integer.parseInt;

public class CoffeeMachineModel {
    private int availableWaterVolume;
    private int availableMilkVolume;
    private int availableCoffeeWeight;
    private int availableCupNumber;
    private int availableCashAmount;

    private StateEnum state;

    public CoffeeMachineModel(
            int availableWaterVolume,
            int availableMilkVolume,
            int availableCoffeeWeight,
            int availableCupNumber,
            int availableCashAmount
    ) {
        this.availableWaterVolume = availableWaterVolume;
        this.availableMilkVolume = availableMilkVolume;
        this.availableCoffeeWeight = availableCoffeeWeight;
        this.availableCupNumber = availableCupNumber;
        this.availableCashAmount = availableCashAmount;
        this.state = ACTION_CHOOSING;
    }

    public int getAvailableWaterVolume() {
        return availableWaterVolume;
    }

    public void setAvailableWaterVolume(int availableWaterVolume) {
        this.availableWaterVolume = availableWaterVolume;
    }

    public int getAvailableMilkVolume() {
        return availableMilkVolume;
    }

    public void setAvailableMilkVolume(int availableMilkVolume) {
        this.availableMilkVolume = availableMilkVolume;
    }

    public int getAvailableCoffeeWeight() {
        return availableCoffeeWeight;
    }

    public void setAvailableCoffeeWeight(int availableCoffeeWeight) {
        this.availableCoffeeWeight = availableCoffeeWeight;
    }

    public int getAvailableCupNumber() {
        return availableCupNumber;
    }

    public void setAvailableCupNumber(int availableCupNumber) {
        this.availableCupNumber = availableCupNumber;
    }

    public int getAvailableCashAmount() {
        return availableCashAmount;
    }

    public void setAvailableCashAmount(int availableCashAmount) {
        this.availableCashAmount = availableCashAmount;
    }

    public void setState(StateEnum state) {
        this.state = state;
    }

    public StateEnum getState() {
        return state;
    }

    public boolean isSwitchOffState() {
        return state == SWITCH_OFF;
    }

    public String makeCoffeeIfAvailableAndGetMessage(CoffeeVariantEnum coffeeVariant) {
        String message = checkAvailableResourcesAndGetMessage(coffeeVariant);
        if (message.equals("OK")) {
            takeMoneyAndMakeCoffee(coffeeVariant);
            message = "I have enough resources, making you a coffee!";
        }
        return message;
    }

    private String checkAvailableResourcesAndGetMessage(CoffeeVariantEnum coffeeVariant) { //вынести печать и формирование строк в представление
        String resultMessage = "Sorry, not enough ";

        if (availableWaterVolume < coffeeVariant.getWaterVolume()) {
            resultMessage += "water!";
        } else if (availableMilkVolume < coffeeVariant.getMilkVolume()) {
            resultMessage += "milk!";
        } else if (availableCoffeeWeight < coffeeVariant.getCoffeeWeight()) {
            resultMessage += "coffee beans!";
        } else if (availableCupNumber < 1) {
            resultMessage += "cups!";
        } else {
            return "OK";
        }
        return resultMessage;
    }


    private void takeMoneyAndMakeCoffee(CoffeeVariantEnum coffeeVariant) {
        if (isMoneyReceived(coffeeVariant)) {
            availableWaterVolume -= coffeeVariant.getWaterVolume();
            availableMilkVolume -= coffeeVariant.getMilkVolume();
            availableCoffeeWeight -= coffeeVariant.getCoffeeWeight();
            availableCupNumber--;
        }
    }

    public boolean isMoneyReceived(CoffeeVariantEnum coffeeVariant) {
        availableCashAmount += coffeeVariant.getPrice();
        return true;
    }

    public void fillWater(String waterVolume) {
        availableWaterVolume += parseInt(waterVolume);
    }

    public void fillMilk(String milkVolume) {
        availableMilkVolume += parseInt(milkVolume);
    }

    public void fillCoffee(String coffeeWeight) {
        availableCoffeeWeight += parseInt(coffeeWeight);
    }

    public void fillCups(String cupNumber) {
        availableCupNumber += parseInt(cupNumber);
    }
}
