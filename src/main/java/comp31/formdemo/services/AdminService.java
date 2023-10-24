package comp31.formdemo.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import comp31.formdemo.model.Employee;
import comp31.formdemo.repositories.Accounts;

@Service
public class AdminService {

   Logger logger = LoggerFactory.getLogger(AdminService.class);

   Accounts accounts;

   

   public AdminService(Accounts accounts){
    this.accounts = accounts;
   }

//    public Employee findAllEmployees(){

//    }

   public List<Employee> findAllEmployees() {
        return accounts.findAllEmployees();
    }
    
}
