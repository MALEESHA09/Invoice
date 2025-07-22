package com.example.AMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.AMS.model.Asset;
import com.example.AMS.model.Invoice;
import com.example.AMS.service.M_AssetService;
import com.example.AMS.service.M_InvoiceService;
import com.example.AMS.service.M_VendorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/invoices")
public class M_InvoiceController {

    private final M_InvoiceService invoiceService;
    private final M_VendorService vendorService;
    private final M_AssetService assetService;

    @Autowired
    public M_InvoiceController(M_InvoiceService invoiceService,
                            M_VendorService vendorService,
                            M_AssetService assetService) {
        this.invoiceService = invoiceService;
        this.vendorService = vendorService;
        this.assetService = assetService;
    }

    @GetMapping
    public String showInvoicePage(Model model, 
                                @RequestParam(required = false) String search) {
        model.addAttribute("vendor", vendorService.getAllVendors());
        model.addAttribute("asset", assetService.getAllAssets());
        model.addAttribute("invoice", new Invoice());
        
        List<Invoice> invoices;
        if (search != null && !search.isEmpty()) {
            invoices = invoiceService.searchInvoices("%" + search + "%");
        } else {
            invoices = invoiceService.getAllInvoices();
        }
        
        model.addAttribute("invoice", invoices);
        return "invoices/list";
    }

   @PostMapping("/save")
public String saveInvoice(@Valid @ModelAttribute("invoice") Invoice invoices,
                        BindingResult result,
                        RedirectAttributes redirectAttributes,
                        Model model) {
    
    if (result.hasErrors()) {
        model.addAttribute("vendor", vendorService.getAllVendors());
        model.addAttribute("asset", assetService.getAllAssets());
        model.addAttribute("invoice", invoiceService.getAllInvoices());
        return "invoices/list";
    }
    
    try {
        // Set the full vendor and asset objects
        invoices.setVendor(vendorService.getVendorById(invoices.getVendor().getVendorId()));

        
        invoiceService.saveInvoice(invoices);
        redirectAttributes.addFlashAttribute("success", "Invoice saved successfully");
        return "redirect:/invoices";
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("error", "Error saving invoice: " + e.getMessage());
        return "redirect:/invoices";
    }
}
    @GetMapping("/view/{id}")
    public String viewInvoice(@PathVariable String id, Model model) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        if (invoice == null) {
            return "redirect:/invoices?error=notfound";
        }
        model.addAttribute("invoice", invoice);
        model.addAttribute("asset", assetService.findByInvoiceId(id));
        return "invoices/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteInvoice(@PathVariable String id) {
        try {
            List<Asset> assets = assetService.findByInvoiceId(id);
            if (!assets.isEmpty()) {
                return "redirect:/invoices?error=cannotdelete";
            }
            
            invoiceService.deleteInvoice(id);
            return "redirect:/invoices?success=true";
        } catch (Exception e) {
            return "redirect:/invoices?error=deletefailed";
        }
    }
    
    @GetMapping("/assets/{invoiceId}")
    @ResponseBody
    public List<Asset> getAssetsByInvoiceId(@PathVariable String invoiceId) {
        return assetService.findByInvoiceId(invoiceId);
    }
} 