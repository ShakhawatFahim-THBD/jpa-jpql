package net.therap.domain;

import net.therap.command.EmployeeRowCmd;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author shakhawat.hossain
 * @since 11/27/16
 */
@Entity
@Table(name = "table_employee")
@NamedQueries({
        @NamedQuery(name = "Employee.findAll",
                query = "SELECT e FROM Employee e"),
        @NamedQuery(name = "Employee.findByName",
                query = "SELECT e FROM Employee e WHERE e.name = :name"),
})
@SqlResultSetMapping(
        name = "EmployeeRowCmdMapping",
        classes = @ConstructorResult(
                targetClass = EmployeeRowCmd.class,
                columns = {
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "departmentName", type = String.class),
                        @ColumnResult(name = "phoneNumber", type = String.class)}))
@NamedEntityGraph(name = "graph.Employee.projects",
        attributeNodes = @NamedAttributeNode("projects"))
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "tmpEmployeeSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tmpEmployeeSeq", sequenceName = "tmp_employee_seq", allocationSize = 1)
    private long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY) // default optional = true
    @JoinColumn(name = "address_id") // default nullable = true
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY) // default optional = true
    @JoinColumn(name = "dept_id") // default nullable = true
    private Department department;

    @ManyToMany
    @JoinTable(name = "table_employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    @OrderColumn(name = "idx")
    private List<Project> projects;

    private Double salary;

    private String phoneNumber;

    public Employee() {
    }

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public Employee(String name, Department department, Double salary) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", department=" + department +
                ", projects=" + projects +
                ", salary=" + salary +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
