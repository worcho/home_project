package root.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import root.demo.services.DishService;
import root.demo.services.RestaurantService;

@Controller
public class UserRestaurantController {

    private final RestaurantService restaurantService;
    private final DishService dishService;

    public UserRestaurantController(RestaurantService restaurantService, DishService dishService) {
        this.restaurantService = restaurantService;
        this.dishService = dishService;
    }

    @GetMapping("/user-restaurants")
    ModelAndView getRestaurants(ModelAndView modelAndView){
        modelAndView.setViewName("user-restaurants");
        modelAndView.addObject("restaurants", restaurantService.getAllRestaurant());
        return modelAndView;
    }

    @GetMapping("/orderFromRestaurant/{id}")
    ModelAndView orderFromRestaurant(ModelAndView modelAndView, @PathVariable Long id){
        modelAndView.setViewName("order-from-restaurant");
        modelAndView.addObject("dishes", dishService.getAllDishesFromRestaurant(id));
        return modelAndView;
    }
}
