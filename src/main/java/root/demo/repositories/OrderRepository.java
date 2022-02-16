package root.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import root.demo.entities.Orders;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {


    @Query(value = "SELECT * FROM Orders where user_email=?1", nativeQuery = true)
    List<Orders> findByEmail(String email);
}
