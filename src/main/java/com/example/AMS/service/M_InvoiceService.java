package com.example.AMS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.AMS.model.Invoice;
import com.example.AMS.repository.M_InvoiceRepository;

@Service
public class M_InvoiceService {

    private final M_InvoiceRepository invoiceRepository;

    public M_InvoiceService(M_InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public List<Invoice> searchInvoices(String searchTerm) {
        return invoiceRepository.searchInvoices(searchTerm);
    }

    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public Invoice getInvoiceById(String id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    public void deleteInvoice(String id) {
        invoiceRepository.deleteById(id);
    }
}
