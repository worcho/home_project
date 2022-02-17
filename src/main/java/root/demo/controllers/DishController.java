package root.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import root.demo.models.binding.DishCreationBindingModel;
import root.demo.services.DishService;

@Controller
public class DishController {

    Long idForEditing;

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping("/dishes")
    ModelAndView getDishes(ModelAndView modelAndView){
        modelAndView.setViewName("dishes");
        modelAndView.addObject("dishes", dishService.getAllDishes());
        return modelAndView;
    }

    @GetMapping("/dishes/create")
    ModelAndView createDishes(ModelAndView modelAndView){
        modelAndView.setViewName("create-dish");
        return modelAndView;
    }

    @PostMapping("/dishes/create")
    ModelAndView confirmCreateDishes(ModelAndView modelAndView, @ModelAttribute DishCreationBindingModel dishCreationBindingModel){
        dishService.createDish(dishCreationBindingModel);
        modelAndView.setViewName("redirect:/dishes");
        return modelAndView;
    }

    @GetMapping("/dishes/delete/{id}")
    ModelAndView deleteDishes(ModelAndView modelAndView, @PathVariable Long id){
        dishService.deleteDish(id);
        modelAndView.setViewName("redirect:/dishes");
        return modelAndView;
    }

    @GetMapping("/dishes/edit/{id}")
    ModelAndView editDishes(ModelAndView modelAndView, @PathVariable Long id){
        idForEditing = id;
        modelAndView.addObject("currentDish", dishService.findDishById(id));
        modelAndView.setViewName("edit-dish");
        return modelAndView;
    }

    @PostMapping("/dishes/edit")
    ModelAndView confirmEditDishes(ModelAndView modelAndView, @ModelAttribute DishCreationBindingModel dishCreationBindingModel){
        dishService.editDish(dishCreationBindingModel, idForEditing);
        modelAndView.setViewName("redirect:/dishes");
        return modelAndView;
    }
}
