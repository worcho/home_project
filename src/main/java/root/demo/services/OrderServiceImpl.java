package root.demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import root.demo.entities.Dish;
import root.demo.entities.Orders;
import root.demo.models.binding.OrderCreationBindingModel;
import root.demo.models.service.DishServiceModel;
import root.demo.models.service.MyOrdesServiceModel;
import root.demo.models.service.OrderServiceModel;
import root.demo.repositories.DishRepository;
import root.demo.repositories.OrderRepository;
import root.demo.repositories.UserRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final DishRepository dishRepository;
    private final ModelMapper modelMapper;


    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, DishRepository dishRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.dishRepository = dishRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createOrder(String userEmail, OrderCreationBindingModel orderCreationBindingModel) {
        Orders order = new Orders();
        order.setStatus("PENDING");
        order.setUser(userRepository.findByEmail(userEmail));
        Set<Dish> dishes1 = new HashSet<>();
        order.setDishes(dishes1);
        Set<Dish> dishes = new HashSet<>();
        for (int i = 0; i < orderCreationBindingModel.getId().size(); i++) {
            dishes.add(dishRepository.getByDishId(orderCreationBindingModel.getId().get(i)));
        }
        order.getDishes().addAll(dishes
                .stream()
                .map(dish -> {
                    Dish dish1 = dishRepository.getByDishId(dish.getId());
                    dish1.getOrders().add(order);
                    return dish1;
                }).collect(Collectors.toList()));
        orderRepository.save(order);
    }

    @Override
    public List<MyOrdesServiceModel> getAllMyOrders(String email) {
        return orderRepository.findByEmail(email)
                .stream()
                .map(orders -> modelMapper.map(orders, MyOrdesServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DishServiceModel> getAllDishesFromMyOrders(Long id) {
        return dishRepository.getAllDishesFromMyOrders(id)
                .stream()
                .map(o -> modelMapper.map(o, DishServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderServiceModel> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orders -> modelMapper.map(orders, OrderServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderServiceModel getById(Long id) {
        return modelMapper.map(orderRepository.getById(id), OrderServiceModel.class);
    }

    @Override
    public void changeStatus(Long id) {
        Orders orders = orderRepository.getById(id);
        orders.setStatus("ACCEPTED");
        orderRepository.save(orders);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
