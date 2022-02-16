package root.demo.models.binding;

import root.demo.entities.Dish;

import javax.persistence.ManyToMany;
import java.util.Set;

public class RestaurantCreationBindingModel {

    private String name;
    private String description;
    private String url;
    private String address;

    public RestaurantCreationBindingModel() {
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
}
