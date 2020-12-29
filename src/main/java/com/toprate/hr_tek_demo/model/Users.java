package com.toprate.hr_tek_demo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "user")
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
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
    @JoinColumn(name = "role_name" ,insertable = false, updatable = false) // thông qua khóa ngoại job_recruitment_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Role role;

    public List<Role> getRoles(){
        List<Role> roles = new ArrayList<>();
        roles.add(getRole());
        return roles;
    }

    public Users(Users users) {

    }

    public Users() {

    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }


    public List<AssignHr> getAssignHrListManager() {
        return assignHrListManager;
    }

    public void setAssignHrListManager(List<AssignHr> assignHrListManager) {
        this.assignHrListManager = assignHrListManager;
    }

    public List<AssignHr> getAssignHrListHr() {
        return assignHrListHr;
    }

    public void setAssignHrListHr(List<AssignHr> assignHrListHr) {
        this.assignHrListHr = assignHrListHr;
    }

    public List<JobRequirements> getJobRequirementsList() {
        return jobRequirementsList;
    }

    public void setJobRequirementsList(List<JobRequirements> jobRequirementsList) {
        this.jobRequirementsList = jobRequirementsList;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
