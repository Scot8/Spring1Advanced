package comp31.formdemo.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import comp31.formdemo.model.Employee;

@Component
public class Accounts extends ArrayList<Employee> {

    //userAuth
    public Employee findByUserId(String userId, String password) {
        for (Employee employee : this) {
            if (employee.getUserId().equals(userId) && employee.getPassword().equals(password))
                return employee;
        }
        return null;
    }

    // TODO add findByDepartment
    public List<Employee> findEmployeesByDepartment(String department) {
        List<Employee> departmentEmployees = new ArrayList<>();

        for (Employee employee : this) {
            if (employee.getRole().equals(department)) {
                departmentEmployees.add(employee);
            }
        }

        return departmentEmployees;
    }

    // TODO add findAllEmployees
    // this doesn't return password - protection of user
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

    // findDepartment
    public List<Employee> findDepartment() {
        List<Employee> employeeD = new ArrayList<>();
        for (Employee employee : this) {
            Employee employeeInfo = new Employee(null, employee.getRole(), null);
            employeeD.add(employeeInfo);
        }
        return employeeD;
    }


}
