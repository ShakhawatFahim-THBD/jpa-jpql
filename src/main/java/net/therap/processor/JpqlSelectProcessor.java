package net.therap.processor;

import net.therap.domain.Department;
import net.therap.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author shakhawat.hossain
 * @since 11/27/16
 */
public class JpqlSelectProcessor implements DbCommandProcessor {

    private static final Logger logger = LoggerFactory.getLogger(JpqlSelectProcessor.class);

    @Override
    public void process(EntityManager em) {
        setup(em);

        List<Employee> employeeList = em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();

        logger.debug("===================== Fetched All Employees =============");
        for (Employee employee : employeeList) {
            logger.debug("Employee : {}", employee);
        }

        List<String> names = em.createQuery("SELECT e.name FROM Employee e", String.class).getResultList();

        logger.debug("===================== Fetched All Employee Names =============");
        for (String name : names) {
            logger.debug("Employee Name : {}", name);
        }
    }

    private void setup(EntityManager em) {
        Department department1 = new Department("ENGINEERING AND TECHNOLOGY");
        Department department2 = new Department("BUSINESS STRATEGY");

        em.persist(department1);
        em.persist(department2);
        em.flush();

        Employee employee1 = new Employee("ADAM", department1, 1000.0);
        Employee employee3 = new Employee("BRIN", department1, 2000.0);
        Employee employee2 = new Employee("Larry", department2, 3000.0);

        em.persist(employee1);
        em.persist(employee2);
        em.persist(employee3);
    }
}
