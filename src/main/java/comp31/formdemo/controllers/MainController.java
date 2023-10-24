package comp31.formdemo.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import comp31.formdemo.model.Employee;
import comp31.formdemo.services.AdminService;
import comp31.formdemo.services.LoginService;

@Controller
public class MainController {

    Logger logger = LoggerFactory.getLogger(MainController.class);
    LoginService loginService;
    AdminService adminService;

    public MainController(LoginService loginService, AdminService adminService) {
        this.loginService = loginService;
        this.adminService = adminService;
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
        //userAuth - auth with password and id
        String returnPage = loginService.userValidation(employee, model);
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
    }

    @GetMapping("/all")
    public String getAllEmployees(Employee employee, Model model) {
        //heading
        String heading = "All-Employees";
        model.addAttribute("heading", heading);
        String loggedUser = loginService.findCurrect();
        logger.info("black" + employee.toString());
        //current user
        model.addAttribute("employee", loggedUser);
        List<Employee> hello = adminService.findAllEmployees();
        logger.info(hello.toString());
        model.addAttribute("emp", hello);
        return "admin";
    }

    @GetMapping("/employeeDepartment")
    public String getEmployeeDep(Model model, Employee emp) {

        String loggedUser = loginService.findCurrect();
        model.addAttribute("employee", loggedUser);
        model.addAttribute("employee", loggedUser);
        model.addAttribute("emp", emp);
        return "admin";
    }

    @PostMapping("/employeeDepartment")
    public String postEmployeeDep(Model model, Employee emp) {
        //returns employee by department
        List<Employee> empD = adminService.findEmployeesByDepartment(emp.getRole());
        logger.info(empD.toString());

        //current user
        String loggedUser = loginService.findCurrect();
        model.addAttribute("employee", loggedUser);

        model.addAttribute("emp", empD);
        //setting up heading
        model.addAttribute("heading", emp.getRole());
        return "admin";

    }

}
