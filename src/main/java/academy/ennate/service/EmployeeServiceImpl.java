package academy.ennate.service;

import academy.ennate.entity.Employee;
import academy.ennate.exception.BadRequestException;
import academy.ennate.exception.EmployeeNotFound;
import academy.ennate.exception.ResourceNotFoundException;
import academy.ennate.repository.EmployeeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRespository empRespository;
    @Override
    public List<Employee> findAll() {
       return empRespository.findAll();
    }

    @Override
    public Employee findOne(String id) {
      Employee emp = empRespository.findOne(id);
        if(emp == null){
            throw new EmployeeNotFound("Employee not found "+id);
        }else{
            return emp;
        }
    }

    @Override
    public Employee findByEmail(String email) {
        return empRespository.findByEmail(email);
    }

    @Override
    @Transactional
    public Employee create(Employee emp) {
        Employee existing = empRespository.findByEmail(emp.getEmail());
        if(existing!=null){
            throw new BadRequestException("Employee with email: "+emp.getEmail()+" already exist");
        }
        return empRespository.create(emp);
    }

    @Override
    @Transactional
    public Employee update(String id, Employee emp) {
        Employee existing = empRespository.findOne(id);
        if(existing == null){
            throw new ResourceNotFoundException("Employee with id "+id+" doesn't exist");
        }
        return empRespository.update(id, emp);
    }

    @Override
    @Transactional
    public void delete(String id) {
        Employee existing = empRespository.findOne(id);
        if(existing == null){
            throw new ResourceNotFoundException("Employee with id "+id+" doesn't exist");
        }
        empRespository.delete(id);
    }
}
