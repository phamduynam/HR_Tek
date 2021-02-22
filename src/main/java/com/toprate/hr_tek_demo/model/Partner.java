package com.toprate.hr_tek_demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "partner")
public class Partner {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "partner_id")
    private int partnerId;

    @Column(name = "partner_name")
    private String partnerName;

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<JobRequirements> jobRequirementsList;
}
