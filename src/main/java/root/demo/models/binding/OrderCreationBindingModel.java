package root.demo.models.binding;

import java.util.List;

public class OrderCreationBindingModel {

    List<Long> id;

    public OrderCreationBindingModel() {
    }

    public List<Long> getId() {
        return id;
    }

    public void setId(List<Long> id) {
        this.id = id;
    }
}
