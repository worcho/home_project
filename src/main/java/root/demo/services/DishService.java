package root.demo.services;

import root.demo.entities.Dish;
import root.demo.models.binding.DishCreationBindingModel;
import root.demo.models.service.DishForUserServiceModel;
import root.demo.models.service.DishServiceModel;

import java.util.List;

public interface DishService {

    void createDish(DishCreationBindingModel model);

    void deleteDish(Long id);

    void editDish(DishCreationBindingModel model, Long id);

    List<DishForUserServiceModel> getAllDishesFromRestaurant(Long id);

    List<DishServiceModel> getAllDishes();

    Dish findDishById(Long id);


}
