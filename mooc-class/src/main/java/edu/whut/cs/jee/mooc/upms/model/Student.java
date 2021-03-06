package edu.whut.cs.jee.mooc.upms.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 学生
 */
@Entity
@Data
@DiscriminatorValue("Student")
public class Student extends User {

    /**
     * 学号
     */
    @Column(name = "student_no")
    private String studentNo;

}