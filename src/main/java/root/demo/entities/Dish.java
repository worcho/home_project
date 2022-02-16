package root.demo.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dish_id", nullable = false, updatable = false)
    private Long id;
    private String name;
    private String description;
    private String url;
    @ManyToMany()
    @JoinTable(
            name = "dish_restaurant",
            joinColumns = @JoinColumn(
                    name = "dish_id",
                    referencedColumnName = "dish_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "restaurant_id",
                    referencedColumnName = "restaurant_id"
            )
    )
    private Set<Restaurant> restaurants;
    @ManyToMany(mappedBy = "dishes")
    private Set<Orders> orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }
}
