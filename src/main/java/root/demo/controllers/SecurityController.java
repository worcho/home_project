package root.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SecurityController {

    @GetMapping("/login")
    ModelAndView login(@RequestParam(required = false) String error, ModelAndView modelAndView){
        modelAndView.setViewName("login");
        if (error != null){
            modelAndView.addObject("error","Wrong username or password.");
        }
        return modelAndView;
    }
    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
    @GetMapping("/accessError")
    public ModelAndView getAccessDenied(ModelAndView modelAndView) {
        modelAndView.setViewName("access-error");
        return modelAndView;
    }
}
