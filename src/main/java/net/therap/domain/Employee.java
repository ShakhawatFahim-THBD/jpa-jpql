package net.therap.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author shakhawat.hossain
 * @since 11/27/16
 */
@Entity
@Table(name = "table_employee")
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

    private Double salary;

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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", department=" + department +
                ", salary=" + salary +
                '}';
    }
}
