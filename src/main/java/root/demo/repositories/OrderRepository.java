package root.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import root.demo.entities.Orders;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query(value = "SELECT * FROM Orders where user_email=?1", nativeQuery = true)
    List<Orders> findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "delete FROM order_dish where order_id = ?1"
            , nativeQuery = true)
    void deleteFromOrderDish(Long id);

    @Transactional
    @Modifying
    @Query(value = "delete FROM orders where order_id = ?1"
            , nativeQuery = true)
    void deleteFromOrders(Long id);
}
