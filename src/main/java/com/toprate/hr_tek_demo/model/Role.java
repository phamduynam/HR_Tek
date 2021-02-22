package com.toprate.hr_tek_demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "role_name",nullable=false)
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Users> userList;

}
