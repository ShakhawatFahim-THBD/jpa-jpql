package net.therap.command;

import java.io.Serializable;

/**
 * @author safat
 * @since 11/29/16.
 */
public class EmployeeRowCmd implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String departmentName;
    private String phoneNumber;

    public EmployeeRowCmd(String name, String departmentName, String phoneNumber) {
        this.name = name;
        this.departmentName = departmentName;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "EmployeeRowCmd{" +
                "name='" + name + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
