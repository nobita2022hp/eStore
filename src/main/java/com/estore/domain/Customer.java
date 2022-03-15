package com.estore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Customers")
public class Customer {

    @Id
    @NotEmpty
    private String id;

    @Length(min = 6, max = 10, message = "Mat khau dai tu 6 den 10 ki tu")
    private String password;

    @NotEmpty
    private String fullName;

    @NotEmpty
    @Email
    private String email;

    private String photo;

    @Column(name = "isActivated")
    private Boolean isActivated;

    @Column(name = "isAdmin")
    private Boolean isAdmin;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
