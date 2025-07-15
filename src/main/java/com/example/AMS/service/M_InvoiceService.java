package com.example.AMS.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.AMS.model.Invoice;
import com.example.AMS.repository.M_AssetRepository;
import com.example.AMS.repository.M_InvoiceRepository;
import com.example.AMS.repository.M_VendorRepository;

@Service
@Transactional
public class M_InvoiceService {

    private final M_InvoiceRepository invoiceRepository;
    private final M_AssetRepository assetRepository;
    private final M_VendorRepository vendorRepository;

    public M_InvoiceService(M_InvoiceRepository invoiceRepository,
                          M_AssetRepository assetRepository,
                          M_VendorRepository vendorRepository) {
        this.invoiceRepository = invoiceRepository;
        this.assetRepository = assetRepository;
        this.vendorRepository = vendorRepository;
    }

    // Save or update invoice
    public Invoice saveInvoice(Invoice invoice) {
        validateInvoiceRelationships(invoice);
        return invoiceRepository.save(invoice);
    }

    // Get all invoices
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    // Get invoice by ID
    public Invoice getInvoiceById(String invoiceId) {
        return invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + invoiceId));
    }

    // Delete invoice
    public void deleteInvoice(String invoiceId) {
        if (!invoiceRepository.existsByInvoiceId(invoiceId)) {
            throw new RuntimeException("Invoice not found with id: " + invoiceId);
        }
        invoiceRepository.deleteById(invoiceId);
    }

    // Get invoices by vendor
    public List<Invoice> getInvoicesByVendor(String vendorId) {
        return invoiceRepository.findByVendorId(vendorId);
    }

    // Get invoices by asset
    public List<Invoice> getInvoicesByAsset(Long assetId) {
        return invoiceRepository.findByAssetId(assetId);
    }

    // Get invoices by date range
    public List<Invoice> getInvoicesByDateRange(Date startDate, Date endDate) {
        return invoiceRepository.findInvoicesBetweenDates(startDate, endDate);
    }

    // Validate invoice relationships
    private void validateInvoiceRelationships(Invoice invoice) {
        if (invoice.getAsset() == null || !assetRepository.existsById(invoice.getAsset().getAssetId())) {
            throw new RuntimeException("Invalid or missing Asset");
        }
        if (invoice.getVendor() == null || !vendorRepository.existsById(invoice.getVendor().getVendorId())) {
            throw new RuntimeException("Invalid or missing Vendor");
        }
    }
}