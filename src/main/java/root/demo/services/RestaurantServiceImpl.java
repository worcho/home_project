package root.demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import root.demo.entities.Dish;
import root.demo.entities.Restaurant;
import root.demo.models.binding.AddDishesToRestaurantModel;
import root.demo.models.binding.RestaurantCreationBindingModel;
import root.demo.models.service.DishForUserServiceModel;
import root.demo.models.service.RestaurantServiceModel;
import root.demo.repositories.DishRepository;
import root.demo.repositories.RestaurantRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    private final RestaurantRepository restaurantRepository;
    private final DishService dishService;
    private final ModelMapper modelMapper;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, DishService dishService, ModelMapper modelMapper) {
        this.restaurantRepository = restaurantRepository;
        this.dishService = dishService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createRestaurant(RestaurantCreationBindingModel model) {
        Restaurant restaurant = modelMapper.map(model,Restaurant.class);
        this.restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteAllDishFromRestaurant(id);
        restaurantRepository.deleteById(id);
    }

    @Override
    public void editRestaurant(RestaurantCreationBindingModel restaurantCreationBindingModel, Long id) {
        Restaurant restaurant = restaurantRepository.getById(id);
        restaurant.setName(restaurantCreationBindingModel.getName());
        restaurant.setDescription(restaurantCreationBindingModel.getDescription());
        restaurant.setUrl(restaurantCreationBindingModel.getUrl());
        restaurant.setAddress(restaurantCreationBindingModel.getAddress());
        restaurantRepository.save(restaurant);
    }

    @Override
    public List<RestaurantServiceModel> getAllRestaurant() {
        return this.restaurantRepository.findAll()
                .stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantServiceModel getRestaurantById(Long id) {
        Optional<Restaurant> restaurant = this.restaurantRepository.findById(id);
        RestaurantServiceModel model = modelMapper.map(this.restaurantRepository.findById(id).get(),RestaurantServiceModel.class);

        return model;
    }

    @Override
    public void addDishesToRestaurant(AddDishesToRestaurantModel model, Long id) {
        Optional<Restaurant> restaurant = this.restaurantRepository.findById(id);
        Restaurant restaurant2 = modelMapper.map(restaurant.get(), Restaurant.class);
        restaurant2.getDishesSet()
                .addAll(model.getDishes()
                .stream()
                .map(dish -> {
                    Dish dish1 = dishService.findDishById(dish.getId());
                    dish1.getRestaurants().add(restaurant2);
                    return dish1;
                }).collect(Collectors.toList()));
        this.restaurantRepository.save(restaurant2);
    }

    @Override
    public void deleteDishFromRestaurant(Long id1, Long id2) {
        this.restaurantRepository.deleteDishFromRestaurant(id1,id2);
    }
}
