package com.toprate.hr_tek.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "contact_position")
public class ContactPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_position_id")
    private int contactPositionId;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "contact_id") // thông qua khóa ngoại job_recruitment_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "position_id") // thông qua khóa ngoại job_recruitment_id
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Position position;

    public ContactPosition() {
    }

    public ContactPosition(Contact contact, Position position) {
        this.contact = contact;
        this.position = position;
    }

    public ContactPosition(Contact contact) {
        this.contact = contact;
    }

    public ContactPosition(Position position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getContactPositionId() {
        return contactPositionId;
    }

    public void setContactPositionId(int contactPositionId) {
        this.contactPositionId = contactPositionId;
    }

}
