
package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;



public class Project implements Serializable{

    public Project(){
        this.projectName = new String();
        this.projectStartDate = null ; //new GregorianCalendar(1,1,1);
        this.projectEndDate = null;
        this.participatingWorkers = new ArrayList<String>();
        this.participatingWorkersSet = new HashSet<String>();
        this.departmentLead = new String();
    }

    public Project(String name){
        this.projectName = name;
        this.projectStartDate = null ; //new GregorianCalendar(1,1,1);
        this.projectEndDate = null;
        this.participatingWorkers = new ArrayList<String>();
        this.participatingWorkersSet = new HashSet<String>();
        this.departmentLead = new String();
    }
    /** name of the Project */
    private String projectName;

    /** start date of the project */
    private Calendar projectStartDate;

    /** end date of the Project */
    private Calendar projectEndDate;

    /** ArrayList of Workers that Participate in the Project */
    private ArrayList<String> participatingWorkers;
    
    /**HashSet of Workers that Participate in the Project */
    private HashSet<String> participatingWorkersSet;

    /** Singular DepartmentLead leading the project, also represents the department here*/
    private String departmentLead; 
    
    /** Function that returns the Salary of all employees */
    public int getSalarySum(){
    	int salary = 0;
    	
    	return salary;
    }

    /**
     * @param projectStartDate the projectStartDate to set new end date for the project
     */
    public boolean setProjectStartDate(Calendar projectStartDate) {
    	
    	//null date handler
    	if(projectStartDate == null) return false;
        
        try {
        	
        	//is null test to for regular creations
	        if(projectEndDate == null) {
	     
	        	this.projectStartDate = new GregorianCalendar();
	        	this.projectStartDate.setLenient(false);
	        	this.projectEndDate = new GregorianCalendar();
	        	this.projectEndDate.setLenient(false);
	   
	        	this.projectStartDate = projectStartDate;
	        	
	        	return true;
	        } else {
	        	
	        	//if the the endDate is before the start than date is false
	        	if( projectStartDate.before(this.projectEndDate) ){
	                this.projectStartDate = projectStartDate;
	                return true;
	            } else { 
	            	return false; 
	            }
	        }
	        
        // if there was an eception the date must be wrong no dealing with parsing
        } catch(Exception e){
            return false;
        }
        
    }

    
    /**
     * @param projectEndDate the projectStartDate to set a new end date for the project
     */
    public boolean setProjectEndDate(Calendar projectEndDate) {
    	
    	//null date handler
    	if(projectEndDate == null) return false;
    	
    	try {
        	
        	//is null test to for regular creations
	        if(projectStartDate == null) {
	        	this.projectStartDate = new GregorianCalendar();
	        	this.projectStartDate.setLenient(false);
	        	this.projectEndDate = new GregorianCalendar();
	        	this.projectEndDate.setLenient(false);
	        	
	        	this.projectEndDate = projectEndDate;
	        	
	        	return true;
	        } else {
	        	
	        	//if the the endDate is before the start than date is false
	        	if( this.projectStartDate.before(projectEndDate) ){
	                this.projectEndDate = projectEndDate;
	                return true;
	            } else { 
	            	return false; 
	            }
	        }
	        
        // if there was an eception the date must be wrong no dealing with parsing
        } catch(Exception e){
            return false;
        }

    }
    
    
    public boolean addParticipatingWorker( String worker) {
    	
    	if (this.participatingWorkers.contains(worker))
    		return false;
    	this.participatingWorkers.add(worker);
    	return true;
    }
    
    public boolean RemoveParticipatingWorker( String worker) {
    	
    	if (!this.participatingWorkers.contains(worker))
    		return false;
    	this.participatingWorkers.remove(worker);
    	return true;
    }

    /**
     * @param projectName the projectName to set
     */
    public boolean setProjectName(String projectName, HashSet<String> projectNameHashSet){
    	// if the project name is null, empty or a space it's wrong
        if(projectName == null || projectName == "" || projectName == " ") { return false; }
        
        //if the name is an the hashset it should be false
        if( projectNameHashSet.contains( projectName ) ){ return false; }
        
        // if everything is good so far than ther project should name should be fine and return 
        this.projectName = projectName;
        return true;
    }

    /**
     * @param participatingWorkers the participatingWorkers to set
     */
    public void setParticipatingWorkers(ArrayList<String> participatingWorkers) {
        this.participatingWorkers = participatingWorkers;
    }

    /**
     * @param departmentLead the departmentLead to set
     */
    public void setDepartmentLead(String departmentLead) {
        this.departmentLead = departmentLead;
    }


    /**
     * @return String return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @return Calendar return the projectEndDate
     */
    public Calendar getProjectEndDate() {
        return projectEndDate;
    }

    /**
     * @return Calendar return the projectStartDate
     */
    public Calendar getProjectStartDate() {
        return projectStartDate;
    }

    /**
     * @return ArrayList<String> return the participatingWorkers
     */
    public ArrayList<String> getParticipatingWorkers() {
        return participatingWorkers;
    }

   /**
     * @return String return the departmentLead
     */
    public String getDepartmentLead() {
        return departmentLead;
    }

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((projectName == null) ? 0 : projectName.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Project other = (Project) obj;
	if (projectName == null) {
		if (other.projectName != null)
			return false;
	} else if (!projectName.equals(other.projectName))
		return false;
	return true;
}
    
    
}
