package com.toprate.hr_tek_demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NoArgsConstructor
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "role_name")
    private String roleName;

}
