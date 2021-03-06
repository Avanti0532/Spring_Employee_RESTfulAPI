package academy.ennate.controller;

import academy.ennate.entity.Employee;
import academy.ennate.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;
    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> findAll(){
       return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public Employee findOne(@PathVariable("id") String empId){
           return service.findOne(empId);
    }
    @RequestMapping(method = RequestMethod.GET, value = "{email}")
    public Employee findbyEMail(@PathVariable("email") String email){
        return service.findByEmail(email);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Employee create(@RequestBody Employee employee){
        return service.create(employee);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public Employee update(@PathVariable("id") String empId, @RequestBody Employee employee){
        return service.update(empId,employee);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable("id") String empId){
        service.delete(empId);

    }
}
