package root.demo.models.binding;

import root.demo.entities.Dish;

import java.util.Set;

public class AddDishesToRestaurantModel {

    private Set<Dish> dishes;

    public AddDishesToRestaurantModel() {
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }
}
