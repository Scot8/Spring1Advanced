package comp31.formdemo.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import comp31.formdemo.model.Employee;

@Component
public class Accounts extends ArrayList<Employee> {

    public Employee findByUserId(String userId, String password) {
        for (Employee employee : this) {
            if (employee.getUserId().equals(userId) && employee.getPassword().equals(password))
                return employee;
        }
        return null;
    }

    // TODO add findByDepartment
    // TODO add findAllEmployees
    //this doesn't return password - protection of user
    public List<Employee> findAllEmployees() {
        List<Employee> employeeNames = new ArrayList<>();
        for (Employee employee : this) {
            String name = employee.getUserId();
            String role = employee.getRole();
            Employee employeeInfo = new Employee(name, role, null);
            employeeNames.add(employeeInfo);
        }
        return employeeNames;
    }





    // public List<String> findAllEmployeeNames() {
    //     List<String> employeeNames = new ArrayList<>();
    //     for (Employee employee : this) {
    //         String names = employee.getUserId();
    //         employeeNames.add(names);
    //     }
    //     return employeeNames;
    // }

}
