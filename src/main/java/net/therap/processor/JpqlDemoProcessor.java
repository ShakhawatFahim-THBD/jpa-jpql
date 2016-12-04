package net.therap.processor;

import net.therap.command.EmployeeRowCmd;
import net.therap.command.Status;
import net.therap.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.*;

/**
 * @author shakhawat.hossain
 * @since 11/27/16
 */
public class JpqlDemoProcessor implements DbCommandProcessor {

    private static final Logger logger = LoggerFactory.getLogger(JpqlDemoProcessor.class);

    @Override
    public void process(EntityManager em) {
//        showHqlInjection(em);
//        showNamedQuery(em);
//        showConverter(em);
//        showConstructorResultMapping(em);
        showFetchGraph(em);
        /*
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

        String hql = "SELECT DISTINCT e.department FROM Employee e WHERE e.salary >= :salary";
        List<Department> departments = em.createQuery(hql, Department.class)
                .setParameter("salary", 2000.0)
                .getResultList();

        logger.debug("===================== Fetched Filtered Departments =============");
        for (Department department : departments) {
            logger.debug("Department : {}", department);
        }

        hql = "FROM Employee e JOIN FETCH e.department";

        List<Employee> employees = em.createQuery(hql, Employee.class).getResultList();

        logger.debug("===================== JOIN FETCH Employee and Department =============");
        for (Employee employee : employees) {
            logger.debug("Employee : {}", employee);
        }

        hql = "FROM Employee e WHERE e.department IS NOT NULL";

        List<Employee> employeeList1 = em.createQuery(hql, Employee.class).getResultList();

        logger.debug("===================== Employees having Department Assigned =============");
        for (Employee employee : employeeList1) {
            logger.debug("Employee : {}", employee);
        }

        hql = "SELECT NEW net.therap.command.EmployeeRowCmd(e.name, e.department.name, e.phoneNumber)" +
                " FROM Employee e";

        List<EmployeeRowCmd> employeeRowCmds = em.createQuery(hql, EmployeeRowCmd.class)
                .getResultList();

        logger.debug("===================== Constructor Expressions:  =============");
        for (EmployeeRowCmd employeeRowCmd : employeeRowCmds) {
            logger.debug("EmployeeRowCmd : {}", employeeRowCmd);
        }
*/
//        String hql = "FROM Employee e WHERE e.projects IS NOT EMPTY";
//
////        List<Employee> employeeList2 = em.createQuery(hql, Employee.class).getResultList();
//
//        hql = "SELECT e FROM Employee e, IN (e.projects) p WHERE p.projectType = :type";
//
//        List<Employee> employeeList3 = em.createQuery(hql, Employee.class)
//                .setParameter("type", ProjectType.PROTOTYPE)
//                .getResultList();
//
//        logger.debug("===================== Employees who work with Prototype Project =============");
//        for (Employee employee : employeeList3) {
//            logger.debug("Employee : {}", employee);
//        }
//
//        hql = "FROM Employee e WHERE SIZE(e.projects) >= :projectCount";
//        List<Employee> employeeList4 = em.createQuery(hql, Employee.class)
//                .setParameter("projectCount", 2)
//                .getResultList();
//
//        logger.debug("===================== Employees who work in more than 2 projects =============");
//        for (Employee employee : employeeList4) {
//            logger.debug("Employee : {}", employee);
//        }
//
//        hql = "SELECT AVG(e.salary) FROM Employee e";
//
//        double avgSalary = em.createQuery(hql, Double.class).getSingleResult();
//        logger.debug("Average Salary: {}", avgSalary);
//
//        hql = "SELECT e.id, e.name, COUNT (p), COUNT(DISTINCT p.projectType) FROM Employee e JOIN e.projects p GROUP BY e.id, e.name";
//
//        em.createQuery(hql).getResultList();


    }

    private void showFetchGraph(EntityManager em) {
        setupEmployeeData(em);

//        EntityGraph graph = em.getEntityGraph("graph.Employee.department");
        EntityGraph graph = em.getEntityGraph("graph.Employee.projects");
        Map<String, Object> hints = Collections.<String, Object>singletonMap("javax.persistence.fetchgraph", graph);

        Employee employee = em.find(Employee.class, 1L, hints);

        logger.debug("===================== Fetched Employee : Fetch Graph =============");
        logger.debug("Employee: {}", employee);

    }

    @SuppressWarnings("unchecked")
    private void showConstructorResultMapping(EntityManager em) {
        setupEmployeeData(em);

        String sql = "SELECT DISTINCT e.name AS name, d.name AS departmentName, e.phone_number AS phoneNumber" +
                " FROM table_employee e" +
                " JOIN table_department d ON (e.dept_id = d.id)";

        List<EmployeeRowCmd> employeeRowCmds = em.createNativeQuery(sql, "EmployeeRowCmdMapping").getResultList();

        logger.debug("=====================  Constructed EmployeeRowCmd by SqlResultSetMapping  =============");
        for (EmployeeRowCmd employeeRowCmd : employeeRowCmds) {
            logger.debug("EmployeeRowCmd : {}", employeeRowCmd);
        }
    }

    private void showConverter(EntityManager em) {
        setupReleaseNoteData(em);

        List<ReleaseNote> releaseNotes = em.createQuery("FROM ReleaseNote rn", ReleaseNote.class)
                .getResultList();

        logger.debug("===================== Fetched Release Notes (Status converted) =============");
        for (ReleaseNote releaseNote : releaseNotes) {
            logger.debug("ReleaseNote : {}", releaseNote);
        }
    }

    private void setupReleaseNoteData(EntityManager em) {
        ReleaseNote releaseNote1 = new ReleaseNote("Release Note 2017.0.0", Status.IN_PREP);
        ReleaseNote releaseNote2 = new ReleaseNote("Release Note 2016.6.0", Status.SUBMITTED);

        em.persist(releaseNote1);
        em.persist(releaseNote2);

        em.flush();
    }

    private void showNamedQuery(EntityManager em) {
        setupEmployeeData(em);

        List<Employee> employees = em.createNamedQuery("Employee.findByName", Employee.class)
                .setParameter("name", "BRIN")
                .getResultList();

        logger.debug("===================== Fetched Employees using Named Query =============");
        for (Employee employee : employees) {
            logger.debug("Employee : {}", employee);
        }
    }

    private void showHqlInjection(EntityManager em) {
        setupLoginData(em);

        String userName = "'asimov' union"; // "'asimov' )"
        String password = "who cares";

        String dirtyHql = "FROM Login l WHERE l.userName = " + userName + " AND l.password = '" + password + "'";

        Login login;
        try {
            login = em.createQuery(dirtyHql, Login.class).getSingleResult();
        } catch (NoResultException e) {
            login = null;
        }

        logger.debug("===================== Fetched Login : HQL Injection =============");
        logger.debug("Login: {}", login);

        String hql = "FROM Login l WHERE l.userName = :userName AND l.password = :password";
        try {
            login = em.createQuery(hql, Login.class)
                    .setParameter("userName", userName)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            login = null;
        }

        logger.debug("===================== Fetched Login : HQL Injection prevented =============");
        logger.debug("Login: {}", login);
    }

    private void setupEmployeeData(EntityManager em) {
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

        employee1.setProjects(new ArrayList<>(Arrays.asList(project1, project2, project3)));
        employee2.setProjects(new ArrayList<>(Collections.singletonList(project2)));

        employee1 = em.merge(employee1);
        employee2 = em.merge(employee2);

        em.flush();
        em.clear();

    }

    private void setupLoginData(EntityManager em) {
        Login login = new Login("Isaac Asimov", "asimov", "VerY sTr0NG pAsSW0RD 1NDEED !");
        em.persist(login);
    }
}
