package comp31.formdemo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import comp31.formdemo.model.Employee;
import comp31.formdemo.repositories.Accounts;

@Service
public class LoginService {

    Logger logger = LoggerFactory.getLogger(LoginService.class);

    Accounts accounts;

    List<String> userC = new ArrayList<>(Arrays.asList("null"));

    public LoginService(Accounts accounts) {
        this.accounts = accounts;

        addEmployee("sam", "sales", "sam");
        addEmployee("sally", "sales", "sally");
        addEmployee("ollie", "orders", "ollie");
        addEmployee("olivia", "orders", "olivia");
        addEmployee("rachel", "repairs", "rachel");
        addEmployee("ralph", "repairs", "ralph");
        addEmployee("abbie", "admin", "abbie");
        addEmployee("arthur", "admin", "arthur");

    }

    public void addEmployee(String userId, String role, String password) {
        logger.info("Adding user: " + userId);
        Employee employee = new Employee();
        employee.setUserId(userId);
        employee.setRole(role);
        employee.setPassword(password);
        accounts.add(employee);
    }

    public void addEmployee(Employee employee) {
        accounts.add(employee);
    }

    public Employee findByUserId(String userId, String password) {
        return accounts.findByUserId(userId, password);
    }

    public String userValidation(Employee employee, Employee currentUser, Model model){
        String returnPage = "login-form";

        if (currentUser == null) {
           // model.addAttribute("employee", employee);
            returnPage = "login-form";
        } else {
            addCurrectC(currentUser.getUserId());
            if(currentUser.getRole().equals("sales")){

                returnPage = "sales";
            }
            else if(currentUser.getRole().equals("orders")){

                returnPage = "orders";
            }
            else if(currentUser.getRole().equals("repairs")){

                returnPage = "repairs";
            }
            else if(currentUser.getRole().equals("admin")){
;
                returnPage = "admin";
            }
        }
        return returnPage;
    }

    public void addCurrectC(String name){
        userC.add(0, name);
    }

    public String findCurrect(){
        return userC.get(0).toString();
    }




}
