package comp31.formdemo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import comp31.formdemo.model.Employee;
import comp31.formdemo.services.LoginService;

@Controller
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);
    LoginService loginService;

    public MainController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/")
    String getRoot(Model model) {
        logger.info("---- At root.");
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "login-form";
    }

    @PostMapping("/login")
    public String getForm(Employee employee, Model model) {
        logger.info("---- At /login.");
        logger.info("---- " + employee.toString());
        Employee currentUser = loginService.findByUserId(employee.getUserId(), employee.getPassword());
        String returnPage = loginService.userValidation(employee, currentUser, model);
        return returnPage;
    }

    @GetMapping("/add-employee")
    public String getRegister(Model model) {
        model.addAttribute("employee", new Employee());
        return "login-form";
    }

    @PostMapping("/add-employee")

    public String postRegister(Model model, Employee newPerson) {
        loginService.addEmployee(newPerson);
        model.addAttribute("employee", new Employee());
        return "redirect:/add-employee";

        //Previous //return "login-form";
        
    }

}
