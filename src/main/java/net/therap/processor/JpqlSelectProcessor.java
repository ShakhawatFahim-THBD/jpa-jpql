package net.therap.processor;

import net.therap.domain.Department;
import net.therap.domain.Employee;
import net.therap.domain.Project;
import net.therap.domain.ProjectType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author shakhawat.hossain
 * @since 11/27/16
 */
public class JpqlSelectProcessor implements DbCommandProcessor {

    private static final Logger logger = LoggerFactory.getLogger(JpqlSelectProcessor.class);

    @Override
    public void process(EntityManager em) {
        showNamedQuery(em);
    }

    private void showNamedQuery(EntityManager em) {
        setupEmployees(em);

        List<Employee> employees = em.createNamedQuery("Employee.findByName", Employee.class)
                .setParameter("name", "BRIN")
                .getResultList();

        logger.debug("===================== Fetched Employees using Named Query =============");
        for (Employee employee : employees) {
            logger.debug("Employee : {}", employee);
        }
    }

    private void setup(EntityManager em) {
        Department department1 = new Department("ENGINEERING AND TECHNOLOGY");
        Department department2 = new Department("BUSINESS STRATEGY");

        em.persist(department1);
        em.persist(department2);

        Employee employee1 = new Employee("ADAM", department1, 1000.0);
        Employee employee2 = new Employee("BRIN", department1, 2000.0);
        Employee employee3 = new Employee("Larry", department2, 3000.0);
        Employee employee4 = new Employee("Yang", null, 1000.0);

        em.persist(employee1);
        em.persist(employee2);
        em.persist(employee3);
        em.persist(employee4);

        Project project1 = new Project("Prototype 1", ProjectType.PROTOTYPE);
        Project project2 = new Project("Prototype 2", ProjectType.PROTOTYPE);
        Project project3 = new Project("Product 1", ProjectType.PRODUCT);

        em.persist(project1);
        em.persist(project2);
        em.persist(project3);

        employee1.setProjects(new ArrayList<>(Arrays.asList(project1, project2)));
        employee2.setProjects(new ArrayList<>(Arrays.asList(project2)));

        em.merge(employee1);
        em.merge(employee2);

        em.flush();

    }

    private void setupEmployees(EntityManager em) {
        Department department1 = new Department("ENGINEERING AND TECHNOLOGY");
        Department department2 = new Department("BUSINESS STRATEGY");

        em.persist(department1);
        em.persist(department2);

        Employee employee1 = new Employee("ADAM", department1, 1000.0);
        Employee employee2 = new Employee("BRIN", department1, 2000.0);
        Employee employee3 = new Employee("Larry", department2, 3000.0);
        Employee employee4 = new Employee("Yang", null, 1000.0);

        em.persist(employee1);
        em.persist(employee2);
        em.persist(employee3);
        em.persist(employee4);
    }
}
