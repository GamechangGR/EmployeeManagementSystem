package com.example.EmployeManagementSyatem.controller;


import com.example.EmployeManagementSyatem.EmployeeRepository;
import com.example.EmployeManagementSyatem.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employeeManagementSystem/")
public class ManagementController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("employee")
    public List<Employee> getAllEmployeeList(){
        try{
            return employeeRepository.findAll();
        }catch (Exception e){
            System.out.println("Exception in GetAll method" + e);
            return null;
        }
    }

    @GetMapping("employee/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id")long id){
        try{
            return employeeRepository.getById(id);
        }catch (Exception e1){
            System.out.println("Exception in GetById method "  + e1);
            return new Employee();
        }
    }

    //PostMethod

    @PostMapping("employee")
    public Employee postEmployee(@RequestBody Employee employee){
        try {
            return employeeRepository.save(employee);
        }catch (Exception e2){
            System.out.println("post Method Error"  + e2);
            return new Employee();
        }
    }

    //PutMethod

    @PutMapping("/employee/{id}")
    public Employee putEmployeeById(@PathVariable(value = "id")long id,@RequestBody Employee employeeUpdatImformation){
        Employee employee = employeeRepository.getById(id);
        try{
            employee.setFirstName(employeeUpdatImformation.getFirstName());
            employee.setLastName(employeeUpdatImformation.getLastName());
            employee.setEmail(employeeUpdatImformation.getEmail());
            final Employee updatedEmployee = employeeRepository.save(employee);
            return updatedEmployee;
        }catch (Exception e3){
            System.out.println("Put Method Exception");
            return new Employee();
        }
    }

    @DeleteMapping("employe/{id}")
    public String deleteEmployeeById(@PathVariable(value = "id") long id){
        Employee deleteEmployeeVar = new Employee();
        try{
             deleteEmployeeVar = employeeRepository.getById(id);
        }catch (Exception e4){
            System.out.println("no Employee present on id "  + id);
        }
        employeeRepository.delete(deleteEmployeeVar);
        return "Deleted";
    }
}
