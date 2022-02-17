package root.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import root.demo.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);

    @Query(value = "delete from orders where user_email = ?1"
            , nativeQuery = true)
    void deleteAllOrdersFromUser(String id);
}
