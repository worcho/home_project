package root.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import root.demo.entities.Dish;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    @Query(value = "SELECT d.dish_id,description,name,url FROM Dish d join Dish_restaurant dr on d.dish_id = dr.dish_id\n" +
            "WHERE dr.restaurant_id =?1"
    , nativeQuery = true)
    List<Dish> getAllDishesFromRestaurant(Long id);


    @Query(value = "select dish_id,description,name,url\n" +
            "from dish\n" +
            "where dish_id = ?1"
            , nativeQuery = true)
    Dish getByDishId(Long id);

    @Query(value = "SELECT * FROM Dish d join Order_dish od on d.dish_id = od.dish_id\n" +
            "WHERE od.order_id=?1"
    ,nativeQuery = true)
    List<Dish> getAllDishesFromMyOrders(Long id);
}
