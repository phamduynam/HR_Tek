package com.toprate.hr_tek.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "status_id")
    private int statusId;

    @Column(name = "status_name")
    private String statusName;

    @ManyToOne
    @JoinColumn(name = "takecare_transaction_id") // thông qua khóa ngoại contact_candidate_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private TakeCareTransaction takeCareTransaction;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở).
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<NoteStatus> noteStatusList;

}
