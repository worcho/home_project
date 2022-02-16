package root.demo.services;

import root.demo.models.binding.OrderCreationBindingModel;
import root.demo.models.service.DishServiceModel;
import root.demo.models.service.MyOrdesServiceModel;
import root.demo.models.service.OrderServiceModel;

import java.util.List;

public interface OrderService {

    void createOrder(String userEmail, OrderCreationBindingModel orderCreationBindingModel);

    List<MyOrdesServiceModel> getAllMyOrders(String email);

    List<DishServiceModel> getAllDishesFromMyOrders(Long id);

    List<OrderServiceModel> getAllOrders();

    OrderServiceModel getById(Long id);

    void changeStatus(Long id);

    void deleteOrder(Long id);
}
