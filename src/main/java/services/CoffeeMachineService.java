package services;

import controllers.CoffeeMachineController;
import models.CoffeeMachineModel;
import views.CoffeeMachineView;

public class CoffeeMachineService {
    public void start() {
        CoffeeMachineModel coffeeMachineModel = new CoffeeMachineModel(400, 540, 120, 9, 550);
        CoffeeMachineView coffeeMachineView = new CoffeeMachineView(coffeeMachineModel);
        CoffeeMachineController coffeeMachineController = new CoffeeMachineController(coffeeMachineModel, coffeeMachineView);
        coffeeMachineController.run();
    }
}
