/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employeemanagementsystem;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class employeeData {
    
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNum;
    private String position;
    private String image;
    private java.sql.Date date;
    
    public employeeData(Integer employeeId,String firstName,String lastName,String gender,String phoneNum,String position,String image,java.sql.Date date){
    
        this.employeeId=employeeId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.gender=gender;
        this.phoneNum=phoneNum;
        this.position=position;
        this.image=image;
        this.date=date;
    }
    
    public  Integer getEmployeeId(){
        
        return employeeId;
    }
    
    public String getFirstName(){
        
        return firstName;
    }
    
    public String getLastName(){
        
        return lastName;
    }
    
    public String getGender(){
    
        return gender;
    }
    
    public String getPhoneNum(){
    
        return phoneNum;
    }
    
    public String getPosition(){
    
        return position;
    }
    
    public String getImage(){
    
        return image;
    }
    
    public java.sql.Date getDate() {
    return date;
    }

    public void setDate(java.sql.Date date) {
    this.date = date;
    }
}

