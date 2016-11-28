package net.therap.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author shakhawat.hossain
 * @since 11/30/16
 */
@Entity
@Table(name = "table_login")
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "tmpLoginSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tmpLoginSeq", sequenceName = "tmp_login_seq", allocationSize = 1)
    private long id;

    private String name;
    private String userName;
    private String password;

    public Login(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
