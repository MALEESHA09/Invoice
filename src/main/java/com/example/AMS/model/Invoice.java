package com.example.AMS.model;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Invoice {
    @Id
    private String invoiceId;
   
    @ManyToOne
    @JoinColumn(name = "vendor_Id")
    private Vendor vendor;

    @ManyToOne
    @JoinColumn(name = "asset_Id")
    private Asset asset;
    

    private Date invoiceDate;
    private int itemCount;
    

    // Getters and Setters
    public String getInvoiceId() { return invoiceId; }
    public void setInvoiceId(String invoiceId) { this.invoiceId = invoiceId; }
    public Date getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(Date invoiceDate) { this.invoiceDate = invoiceDate; }
    public int getItemCount() { return itemCount; }
    public void setItemCount(int itemCount) { this.itemCount = itemCount; }
     public Asset getAsset() { return asset; }
    public void setAsset(Asset asset) { this.asset = asset; }
    public Vendor getVendor() { return vendor; }
    public void setVendor(Vendor vendor) { this.vendor = vendor; }
    
}