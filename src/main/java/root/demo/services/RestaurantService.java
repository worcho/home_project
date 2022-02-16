package root.demo.services;

import root.demo.models.binding.AddDishesToRestaurantModel;
import root.demo.models.binding.RestaurantCreationBindingModel;
import root.demo.models.service.DishForUserServiceModel;
import root.demo.models.service.RestaurantServiceModel;

import java.util.List;

public interface RestaurantService {

    void createRestaurant(RestaurantCreationBindingModel model);

    void deleteRestaurant(Long id);

    void editRestaurant(RestaurantCreationBindingModel restaurantCreationBindingModel,Long id);

    List<RestaurantServiceModel> getAllRestaurant();

    RestaurantServiceModel getRestaurantById(Long id);

    List<DishForUserServiceModel> getAllDishesFromRestaurant(Long id);

    void addDishesToRestaurant(AddDishesToRestaurantModel model, Long id);

    void deleteDishFromRestaurant(Long id1, Long id2);
}
