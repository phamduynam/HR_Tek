package com.toprate.hr_tek_demo.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user")
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "my_generatorUser")
    @GenericGenerator(name = "my_generatorUser", strategy = "com.toprate.hr_tek_demo.generators.GeneratorUserId")
    @Column(name = "user_id",length = 20)
    private String userId;

    @Column(name = "name")
    private String name;

    @Column(name = "gmail")
    private String gmail;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birth_day")
    private Date birthDay;

    @Column(name = "enable")
    private int enable;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "user_manager", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<AssignHrContact> assignHrContactContactListManager;

    @OneToMany(mappedBy = "user_hr", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<AssignHrContact> assignHrContactListHrContact;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<JobRequirements> jobRequirementsList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Contact> contactList;

    @ManyToOne
    @JoinColumn(name = "role_name" ,nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Role role;

    public List<Role> getRoles(){
        List<Role> roles = new ArrayList<>();
        roles.add(getRole());
        return roles;
    }

    public Users(){
        this.enable = 1;
        this.status = "ACTIVE";
    }

}
