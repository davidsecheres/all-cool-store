package com.allcoolstore.model;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotNull
    private String firstName;
    //@NotNull
    private String lastName;
    //@NotNull
    private Date dateOfBirth;
    //@NotNull
    private String email;
    //@NotNull
    private String username;
    //@NotNull
    private String password;
    //@NotNull
    private String role;
    //@NotNull
    private String phone;
    private String city;
    private String county;
    private String postalCode;
    private String address1;
    private String address2;

    public User(String firstName, String lastName, Date dateOfBirth, String email, String username, String password, String role, String phone, String city, String county, String postalCode, String address1, String address2) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.city = city;
        this.county = county;
        this.postalCode = postalCode;
        this.address1 = address1;
        this.address2 = address2;
    }


    @OneToMany(mappedBy = "user")
    private List<Order> orderList;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
