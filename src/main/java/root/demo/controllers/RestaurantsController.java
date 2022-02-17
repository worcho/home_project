package root.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import root.demo.models.binding.AddDishesToRestaurantModel;
import root.demo.models.binding.RestaurantCreationBindingModel;
import root.demo.services.DishService;
import root.demo.services.RestaurantService;

@Controller
public class RestaurantsController {

    Long idForEditting;

    private final RestaurantService restaurantService;
    private final DishService dishService;

    public RestaurantsController(RestaurantService restaurantService, DishService dishService) {
        this.restaurantService = restaurantService;
        this.dishService = dishService;
    }

    @GetMapping("/restaurants")
    ModelAndView getRestaurants(ModelAndView modelAndView){
        modelAndView.setViewName("restaurants");
        modelAndView.addObject("restaurants", restaurantService.getAllRestaurant());
        return modelAndView;
    }

    @GetMapping("/restaurants/details/{id}")
    ModelAndView getRestaurant(ModelAndView modelAndView,@PathVariable Long id){
        modelAndView.setViewName("restaurants-details");
        modelAndView.addObject("restaurant", restaurantService.getRestaurantById(id));
        return modelAndView;
    }

    @GetMapping("/restaurants/create")
    ModelAndView createRestaurants(ModelAndView modelAndView){
        modelAndView.setViewName("create-restaurant");
        return modelAndView;
    }

    @PostMapping("/restaurants/create")
    ModelAndView confirmCreateRestaurants(ModelAndView modelAndView, @ModelAttribute RestaurantCreationBindingModel restaurantCreationBindingModel){
        this.restaurantService.createRestaurant(restaurantCreationBindingModel);
        modelAndView.setViewName("redirect:/restaurants");
        return modelAndView;
    }

    @GetMapping("/restaurants/delete/{id}")
    ModelAndView deleteRestaurant(ModelAndView modelAndView, @PathVariable Long id){
        restaurantService.deleteRestaurant(id);
        modelAndView.setViewName("redirect:/restaurants");
        return modelAndView;
    }

    @GetMapping("/restaurants/edit/{id}")
    ModelAndView editRestaurant(ModelAndView modelAndView, @PathVariable Long id){
        idForEditting = id;
        modelAndView.addObject("currentRestaurant", restaurantService.getRestaurantById(id));
        modelAndView.setViewName("edit-restaurant");
        return modelAndView;
    }

    @PostMapping("/restaurants/edit")
    ModelAndView confirmEditRestaurant(ModelAndView modelAndView, @ModelAttribute RestaurantCreationBindingModel restaurantCreationBindingModel){
        restaurantService.editRestaurant(restaurantCreationBindingModel, idForEditting);
        modelAndView.setViewName("redirect:/restaurants");
        return modelAndView;
    }

    @GetMapping("/restaurant/add-dishes/{id}")
    ModelAndView addDishesToRestaurants(ModelAndView modelAndView, @PathVariable Long id){
        modelAndView.setViewName("restaurants-add-dishes");
        modelAndView.addObject("id", id);
        modelAndView.addObject("dishes", dishService.getAllDishes());
        return modelAndView;
    }

    @PostMapping("/restaurant/add-dishes/{id}")
    ModelAndView confirmAddDishesToRestaurants(ModelAndView modelAndView, @PathVariable Long id,
                                               @ModelAttribute AddDishesToRestaurantModel addDishesToRestaurantModel){
        modelAndView.setViewName("redirect:/restaurants/details/{id}");
        restaurantService.addDishesToRestaurant(addDishesToRestaurantModel,id);
        return modelAndView;
    }
    @GetMapping("/restaurants/delete/dish/{id1}/{id2}")
    ModelAndView deleteDishFromRestaurant(ModelAndView modelAndView, @PathVariable Long id1, @PathVariable Long id2){
        restaurantService.deleteDishFromRestaurant(id1,id2);
        modelAndView.setViewName("redirect:/restaurants/details/{id1}");
        return modelAndView;
    }
}
