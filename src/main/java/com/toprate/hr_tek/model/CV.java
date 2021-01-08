package com.toprate.hr_tek.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cv")
public class CV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cv_id")
    private int cvId;


    @Column(name = "link_cv")
    private String linkCv;

    @Column(name = "cv_pdf")
    private String cvPdf;

    //FK
    @ManyToOne
    @JoinColumn(name = "contact_id") // thông qua khóa ngoại job_recruitment_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Contact contact;

}
