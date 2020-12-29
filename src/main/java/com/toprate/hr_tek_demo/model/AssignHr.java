package com.toprate.hr_tek_demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "assign_hr")
public class AssignHr {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assign_hr_id")
    private int assignHrId;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    @OneToMany(mappedBy = "assignHr", cascade = CascadeType.ALL) // Quan hệ 1-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
    // MapopedBy trỏ tới tên biến Address ở trong Person.
    @EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
    @ToString.Exclude // Khoonhg sử dụng trong toString()
    private List<AssignHrJob> assignHrJobList;

    //FK
    @ManyToOne
    @JoinColumn(name = "contact_candidate_id") // thông qua khóa ngoại contact_candidate_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Contact contact;
    // FK
    @ManyToOne
    @JoinColumn(name = "manager_id") // thông qua khóa ngoại manager_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Users user_manager;

    // FK
    @ManyToOne
    @JoinColumn(name = "hr_id") // thông qua khóa ngoại hr_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Users user_hr;

    public int getAssignHrId() {
        return assignHrId;
    }

    public void setAssignHrId(int assignHrId) {
        this.assignHrId = assignHrId;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public List<AssignHrJob> getAssignHrJobList() {
        return assignHrJobList;
    }

    public void setAssignHrJobList(List<AssignHrJob> assignHrJobList) {
        this.assignHrJobList = assignHrJobList;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Users getUser_manager() {
        return user_manager;
    }

    public void setUser_manager(Users user_manager) {
        this.user_manager = user_manager;
    }

    public Users getUser_hr() {
        return user_hr;
    }

    public void setUser_hr(Users user_hr) {
        this.user_hr = user_hr;
    }
}
