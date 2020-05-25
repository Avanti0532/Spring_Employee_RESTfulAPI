package academy.ennate.repository;

import academy.ennate.entity.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
@Repository
public class EmployeeRepositoryImpl implements EmployeeRespository{

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Employee> findAll() {

        TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findAll", Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee findOne(String id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee findByEmail(String email) {
        TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findByEmail",Employee.class);
        query.setParameter("paramEmail", email);
        List<Employee> resultList = query.getResultList();
        if(resultList!=null && resultList.size()==1){
            return resultList.get(0);
        }else{
            return null;
        }

    }

    @Override
    public Employee create(Employee emp) {
        entityManager.persist(emp);
        return emp;
    }

    @Override
    public Employee update(String id, Employee emp) {
        return entityManager.merge(emp);
    }

    @Override
    public void delete(String id) {
        entityManager.remove(id);
    }
}
