package com.toprate.hr_tek_demo.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
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


    @OneToMany(mappedBy = "user_manager", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<AssignHr> assignHrListManager;

    @OneToMany(mappedBy = "user_hr", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<AssignHr> assignHrListHr;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<JobRequirements> jobRequirementsList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<Contact> contactList;

    @ManyToOne(optional=false)
    @JoinColumn(name = "role_name" ,nullable = false) // thông qua khóa ngoại job_recruitment_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Role role;

    public List<Role> getRoles(){
        List<Role> roles = new ArrayList<>();
        roles.add(getRole());
        return roles;
    }

}
