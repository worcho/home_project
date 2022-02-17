package root.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import root.demo.models.binding.UserEditBindingModel;
import root.demo.models.binding.UserRegisterBindingModel;
import root.demo.services.UserService;
import javax.validation.Valid;

@Controller
public class UserController {

    String emailForEditing;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    ModelAndView register(ModelAndView modelAndView, UserRegisterBindingModel model){
        return modelAndView;
    }

    @PostMapping("/register")
    ModelAndView confirmRegister(ModelAndView modelAndView, @Valid @ModelAttribute UserRegisterBindingModel userRegisterBindingModel,  BindingResult result){
        if (result.hasErrors()){
            ModelAndView modelAndView1 = new ModelAndView();
            modelAndView1.setViewName("register");
            return modelAndView1;
        }
        if (!userService.isPasswordsMatch(userRegisterBindingModel)){
            ModelAndView modelAndView1 = new ModelAndView();
            modelAndView1.addObject("passError", "Passwords do not match!");
            modelAndView1.setViewName("register");
            return modelAndView1;
        }else {
            userService.register(userRegisterBindingModel);
            modelAndView.setViewName("redirect:/register");
            return modelAndView;
        }
    }

    @GetMapping("/users")
    ModelAndView getUsers(ModelAndView modelAndView){
        modelAndView.setViewName("users");
        modelAndView.addObject("users" , userService.getAllUsers());
        return modelAndView;
    }

    @GetMapping("/user/delete/{email}")
    ModelAndView deleteUser(ModelAndView modelAndView, @PathVariable String email){
        userService.deleteUser(email);
        modelAndView.setViewName("redirect:/users");
        return modelAndView;
    }

    @GetMapping("/user/edit/{email}")
    ModelAndView editUser(ModelAndView modelAndView, @PathVariable String email){
        emailForEditing = email;
        modelAndView.addObject("currentUser", userService.getUserById(email));
        modelAndView.setViewName("edit-user");
        return modelAndView;
    }

    @PostMapping("/user/edit")
    ModelAndView confirmEditUser(ModelAndView modelAndView, @ModelAttribute UserEditBindingModel userEditBindingModel){
        userService.editUser(userEditBindingModel,emailForEditing);
        modelAndView.setViewName("redirect:/users");
        return modelAndView;
    }

}
