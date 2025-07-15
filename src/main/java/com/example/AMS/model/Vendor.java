package com.example.AMS.model;
import jakarta.persistence.*;

@Entity
public class Vendor {
    @Id
    private String vendorId;
    private String vendorName;
    private String address;
    private int contactNo;

    // Getters and Setters
    public String getVendorId() { return vendorId; }
    public void setVendorId(String vendorId) { this.vendorId = vendorId; }
    public String getVendorName() { return vendorName; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public int getContactNo() { return contactNo; }
    public void setContactNo(int contactNo) { this.contactNo = contactNo; }
}
