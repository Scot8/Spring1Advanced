package comp31.formdemo.services;

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

    public LoginService(Accounts accounts) {
        this.accounts = accounts;

        // String[] userIds = { "admin", "sales", "orders" };

        // for (String userId : userIds) {
        //     addEmployee(userId);
        //     // TODO add more user info
        // }

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
            model.addAttribute("employee", employee);
            returnPage = "login-form";
        } else {
            if(currentUser.getRole().equals("sales")){
                model.addAttribute("employee", currentUser);
                returnPage = "sales";
            }
            else if(currentUser.getRole().equals("orders")){
                model.addAttribute("employee", currentUser);
                returnPage = "orders";
            }
            else if(currentUser.getRole().equals("repairs")){
                model.addAttribute("employee", currentUser);
                returnPage = "repairs";
            }
            else if(currentUser.getRole().equals("admin")){
                model.addAttribute("employee", currentUser);
                returnPage = "admin";
            }
        }
        return returnPage;
    }




}
