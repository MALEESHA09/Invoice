package com.example.AMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.AMS.model.Invoice;

public interface M_InvoiceRepository extends JpaRepository<Invoice, String> {

    @Query("SELECT i FROM Invoice i WHERE i.invoiceId LIKE :searchTerm OR i.asset.name LIKE :searchTerm OR i.vendor.vendorName LIKE :searchTerm")
    List<Invoice> searchInvoices(@Param("searchTerm") String searchTerm);
}
