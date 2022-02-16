package root.demo.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "restaurant_id", nullable = false, updatable = false)
    private Long id;
    private String name;
    private String description;
    private String url;
    private String address;
    @ManyToMany(mappedBy = "restaurants")
    private Set<Dish> dishes;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Dish> getDishesSet() {
        return dishes;
    }

    public void setDishesSet(Set<Dish> dishSet) {
        this.dishes = dishSet;
    }
}
