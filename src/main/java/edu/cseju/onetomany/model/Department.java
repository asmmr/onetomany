package edu.cseju.onetomany.model;

import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity
public class Department {
    @Id
    private String deptId;
    private String deptName;
    private String deptCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private Faculty faculty;

    public String getDeptId() {
        return deptId;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", deptCode='" + deptCode + '\'' +
                ", faculty=" + faculty +
                '}';
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
