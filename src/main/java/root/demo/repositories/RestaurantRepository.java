package root.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import root.demo.entities.Dish;
import root.demo.entities.DishFromRestaurant;
import root.demo.entities.Restaurant;
import root.demo.models.service.DishForUserServiceModel;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE from dish_restaurant where restaurant_id =?1 and dish_id=?2"
            , nativeQuery = true)
    void deleteDishFromRestaurant(Long id1, Long id2);

    @Transactional
    @Modifying
    @Query(value = "delete from dish_restaurant where restaurant_id = ?1"
            , nativeQuery = true)
    void deleteAllDishFromRestaurant(Long id);

    @Query(value = "select name, description, url\n" +
            "from dish\n" +
            "join dish_restaurant on dish.dish_id = dish_restaurant.dish_id\n" +
            "where dish_restaurant.restaurant_id = ?1"
            , nativeQuery = true)
    List<DishFromRestaurant> getAllDishesFromRestaurant(Long id);
}
