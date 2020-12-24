package com.toprate.hr_tek_demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cv")
public class CV {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "cv_id")
    private int cvId;

    @Column(name = "link_cv")
    private String linkCv;

    @Column(name = "cv_pdf")
    private String cvPdf;

    //FK
    @Column(name = "contact_candidate_id")
    private String contactCandidateId;

}
