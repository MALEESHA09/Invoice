package com.example.AMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public M_InvoiceController(M_InvoiceService invoiceService,
                             M_AssetService assetService,
                             M_VendorService vendorService) {
        this.invoiceService = invoiceService;
        this.assetService = assetService;
        this.vendorService = vendorService;
    }

    @GetMapping
    public String listInvoices(Model model) {
        model.addAttribute("invoices", invoiceService.getAllInvoices());
        return "invoices/invoice-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("invoices", new Invoice()); // Fixed attribute name
        model.addAttribute("assets", assetService.getAllAssets());
        model.addAttribute("vendors", vendorService.getAllVendors());
        return "invoices/invoice-add";
    }

    @PostMapping("/save")
    public String saveInvoice(@ModelAttribute("invoices") Invoice invoice,
                            RedirectAttributes redirectAttributes) {
        try {
            invoiceService.saveInvoice(invoice);
            redirectAttributes.addFlashAttribute("successMessage", "Invoice saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error saving invoice: " + e.getMessage());
        }
        return "redirect:/invoices";
    }

    @GetMapping("/view/{id}")
    public String viewInvoice(@PathVariable String id, Model model) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        if (invoice == null) {
            return "redirect:/invoices";
        }
        model.addAttribute("invoice", invoice);
        return "invoices/invoice-view";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        if (invoice == null) {
            return "redirect:/invoices";
        }
        model.addAttribute("invoice", invoice);
        model.addAttribute("assets", assetService.getAllAssets());
        model.addAttribute("vendors", vendorService.getAllVendors());
        return "invoices/invoice-add";
    }

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