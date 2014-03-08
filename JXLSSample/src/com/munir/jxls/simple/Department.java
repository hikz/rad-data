package com.munir.jxls.simple;

import java.util.ArrayList;
import java.util.List;


public class Department {

	public double deptName;
	public List employees = new ArrayList();
	
	public Department(){
		System.out.println("in constr");
		this.deptName = deptName;
        this.employees = employees;
	}
	public Department(double deptName, List employees) {
        this.deptName = deptName;
        this.employees = employees;
    }
	
	public double getDeptName() {
		return deptName;
	}
	public void setDeptName(double deptName) {
		this.deptName = deptName;
	}
	public List getEmployees() {
		return employees;
	}
	public void setEmployees(List employees) {
		this.employees = employees;
	}
}
