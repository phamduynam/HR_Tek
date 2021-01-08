package com.toprate.hr_tek.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "takecare_transaction")
public class TakeCareTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "takecare_transaction_id")
    private int takecareTransacntion;

    @Column(name = "takecare_transaction_name")
    private String takecareTransacntionName;

    @Column(name = "description")
    private String description;


    @OneToMany(mappedBy = "takeCareTransaction", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở).
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<BlackListAction> blackListActionList;

    @OneToMany(mappedBy = "takeCareTransaction", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở).
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<Note> noteList;

    @OneToMany(mappedBy = "takeCareTransaction", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở).
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<Status> statusList;

    @OneToMany(mappedBy = "takeCareTransaction", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở).
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<TodoTask> todoTaskList;

    @ManyToOne
    @JoinColumn(name = "candidate_id") // thông qua khóa ngoại candidate_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Contact contact;

    //FK
    @ManyToOne
    @JoinColumn(name = "job_id") // thông qua khóa ngoại job_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private JobRequirements  jobRequirements;
}
