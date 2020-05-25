package academy.ennate.repository;

import academy.ennate.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
public interface EmployeeRespository {

    List<Employee> findAll();
    Employee findOne(String id);
    Employee findByEmail(String email);
    Employee create(Employee emp);
    Employee update(String id, Employee emp);
    void delete(String id);
}
