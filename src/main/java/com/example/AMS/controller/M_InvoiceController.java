package com.example.AMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.AMS.model.Invoice;
import com.example.AMS.service.M_AssetService;
import com.example.AMS.service.M_InvoiceService;
import com.example.AMS.service.M_VendorService;

@Controller
@RequestMapping("/invoices")
public class M_InvoiceController {

    private final M_InvoiceService invoiceService;
    private final M_AssetService assetService;
    private final M_VendorService vendorService;

    public M_InvoiceController(M_InvoiceService invoiceService,
                             M_AssetService assetService,
                             M_VendorService vendorService) {
        this.invoiceService = invoiceService;
        this.assetService = assetService;
        this.vendorService = vendorService;
    }

    // List all invoices
    @GetMapping
    public String listInvoices(Model model) {
        model.addAttribute("invoices", invoiceService.getAllInvoices());
        return "invoices/invoice-list";
    }

    // Show add form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("invoice", new Invoice());
        model.addAttribute("assets", assetService.getAllAssets());
        model.addAttribute("vendors", vendorService.getAllVendors());
        return "invoices/invoice-add";
    }

    // Save invoice
    @PostMapping("/save")
    public String saveInvoice(@ModelAttribute Invoice invoice,
                            RedirectAttributes redirectAttributes) {
        try {
            invoiceService.saveInvoice(invoice);
            redirectAttributes.addFlashAttribute("successMessage", "Invoice saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error saving invoice: " + e.getMessage());
        }
        return "redirect:/invoices";
    }

    // View invoice details
    @GetMapping("/view/{id}")
    public String viewInvoice(@PathVariable String id, Model model) {
        model.addAttribute("invoice", invoiceService.getInvoiceById(id));
        return "invoices/invoice-view";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        model.addAttribute("invoice", invoiceService.getInvoiceById(id));
        model.addAttribute("assets", assetService.getAllAssets());
        model.addAttribute("vendors", vendorService.getAllVendors());
        return "invoices/invoice-add"; // Reuse the add form for editing
    }

    // Delete invoice
    @GetMapping("/delete/{id}")
    public String deleteInvoice(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            invoiceService.deleteInvoice(id);
            redirectAttributes.addFlashAttribute("successMessage", "Invoice deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting invoice: " + e.getMessage());
        }
        return "redirect:/invoices";
    }
}