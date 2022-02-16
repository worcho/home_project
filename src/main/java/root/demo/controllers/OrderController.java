package root.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import root.demo.entities.Dish;
import root.demo.entities.Orders;
import root.demo.models.binding.OrderCreationBindingModel;
import root.demo.models.binding.SearchOrderBindingModel;
import root.demo.models.service.MyOrdesServiceModel;
import root.demo.models.service.OrderServiceModel;
import root.demo.services.OrderService;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    ModelAndView getAllOrders(ModelAndView modelAndView) {
        modelAndView.addObject("orders", orderService.getAllOrders());
        List<OrderServiceModel> orders = orderService.getAllOrders();
        modelAndView.setViewName("orders");
        return modelAndView;
    }

    @GetMapping("/orders/accept/{id}")
    ModelAndView acceptOrders(ModelAndView modelAndView, @PathVariable Long id) {
        orderService.changeStatus(id);
        modelAndView.setViewName("redirect:/orders");
        return modelAndView;
    }

    @GetMapping("/orders/delete/{id}")
    ModelAndView deleteOrder(ModelAndView modelAndView, @PathVariable Long id) {
        orderService.deleteOrder(id);
        modelAndView.setViewName("redirect:/orders");
        return modelAndView;
    }

    @PostMapping("/order/create")
    ModelAndView createOrder(ModelAndView modelAndView, @ModelAttribute OrderCreationBindingModel orderCreationBindingModel, Principal principal) {
        OrderCreationBindingModel model = orderCreationBindingModel;
        orderService.createOrder(principal.getName(), orderCreationBindingModel);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @GetMapping("/myOrders")
    ModelAndView getMyOrders(ModelAndView modelAndView, Principal principal) {
        modelAndView.addObject("orders", orderService.getAllMyOrders(principal.getName()));
        modelAndView.setViewName("my-orders");
        return modelAndView;
    }

    @PostMapping("/search")
    ModelAndView searchOrders(ModelAndView modelAndView, @ModelAttribute SearchOrderBindingModel searchOrderBindingModel) {
        modelAndView.addObject("orders", orderService.getById(searchOrderBindingModel.getId()));
        modelAndView.setViewName("orders");
        return modelAndView;
    }
}
