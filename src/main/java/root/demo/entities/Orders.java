package root.demo.entities;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", nullable = false, updatable = false)
    private Long id;
    private String status;
    @ManyToOne
    private User user;
    @ManyToMany
    @JoinTable(
            name = "order_dish",
            joinColumns = @JoinColumn(
                    name = "order_id",
                    referencedColumnName = "order_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "dish_id",
                    referencedColumnName = "dish_id"
            )
    )
    private Set<Dish> dishes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }
}
