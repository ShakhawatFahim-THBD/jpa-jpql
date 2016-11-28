package net.therap.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author shakhawat.hossain
 * @since 11/27/16
 */

@Entity
@Table(name = "table_address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "tmpAddressSeq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tmpAddressSeq", sequenceName = "tmp_address_seq", allocationSize = 1)
    private long id;

    private String street;
    private String city;
    private String state;
    private String zip;

    public Address() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
