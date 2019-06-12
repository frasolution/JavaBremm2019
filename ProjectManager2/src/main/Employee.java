package main;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Mandatory:
 * - Name
 * - Salary per month
 * Recommended:
 * - Projects of employee
 * - Department lead of employee
 */

public class Employee {
    
    /** this is the Name of the Employee */
    private String employeeName;

    /** this is the Salary of the Employee */
    private Integer employeeSalary;

    /** this is the List of projects an employee partakes in */
    private ArrayList<String> employeeProjectList;

    private HashSet<String> employeeProjectSet;

    /** this is a String that saves the name of the DepartmentLead */
    private String employeeDepartmentLead;

    //create empty Empolyee Object
    public Employee(){
        this.employeeName = new String();
        this.employeeSalary = null;
        this.employeeProjectList = new ArrayList<String>();
        this.employeeDepartmentLead = new String();
    }

    /**
     * @param employeeName the employeeName to set
     */
    public boolean setEmployeeName(String employeeName, HashSet<String> employeeNameHashSet) {
    	// if the project name is null, empty or a space it's wrong
        if(employeeName == null || employeeName == "" || employeeName == " ") return false;
        
        //if the name is an the hashset it should be false
        if( employeeNameHashSet.contains( employeeName ) ){
        	 return false;
        }
        
        // if everything is good so far than ther project should name should be fine and return 
        this.employeeName = employeeName;
        return true;
    }


    /**
     * @param employeeSalary the employeeSalary to set
     */
    public boolean setEmployeeSalary(int employeeSalary) {
        if (employeeSalary < 0 ) { return false; }
        this.employeeSalary = employeeSalary;
        return true;
    }

    /**
     * to be kept for possible tricks of copying later down the pipe
     * @param employeeProjectList the employeeProjectList to set
     */
    public void setEmployeeProjectList(ArrayList<String> employeeProjectList) {
        this.employeeProjectList = employeeProjectList;
    }

    public boolean addEmployeeProjectListe(String projectName) {
        if(projectName == null || projectName == "" || projectName == " ") return false;
        if(employeeProjectSet.contains(projectName)) return false;
        this.employeeProjectList.add(projectName);
        return true;
    }

    /**
     * @param employeeDepartmentLead the employeeDepartmentLead to set
     */
    public boolean setEmployeeDepartmentLead(String employeeDepartmentLead) {
        this.employeeDepartmentLead = employeeDepartmentLead;
        return true;
    }

    //GETTERS

    /**
     * @return int return the employeeSalary
     */
    public int getEmployeeSalary() {
        return employeeSalary;
    }

    /**
     * @return ArrayList<String> return the employeeProjectList
     */
    public ArrayList<String> getEmployeeProjectList() {
        return employeeProjectList;
    }

    /**
     * @return String return the employeeDepartmentLead
     */
    public String getEmployeeDepartmentLead() {
        return employeeDepartmentLead;
    }

    /**
     * @return String return the employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

}
