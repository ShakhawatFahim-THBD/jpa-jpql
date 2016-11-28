package net.therap.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author shakhawat.hossain
 * @since 11/27/16
 */

@Entity
@Table(name = "table_department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "tmpDepartmentSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tmpDepartmentSeq", sequenceName = "tmp_department_seq", allocationSize = 1)
    private long id;

    private String name;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
