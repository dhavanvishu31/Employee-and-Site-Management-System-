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
public class SiteData {
    private  String siteName;
    private  String siteAddress;
    private  String assignedTo;
    private java.sql.Date startDate;
    private java.sql.Date endDate;

    public SiteData(String siteName, String siteAddress, String assignedTo, Date startDate, Date endDate) {
        this.siteName = siteName;
        this.siteAddress = siteAddress;
        this.assignedTo = assignedTo;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getSiteName() {
        return siteName;
    }

    public String getSiteAddress() {
        return siteAddress;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    
    
    

    
}
