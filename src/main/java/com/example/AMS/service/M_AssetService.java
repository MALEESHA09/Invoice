package com.example.AMS.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AMS.model.Asset;
import com.example.AMS.repository.M_AssetRepository;

@Service
public class M_AssetService {
    private final M_AssetRepository assetRepository;

    @Autowired
    public M_AssetService(M_AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<Asset> getAllAssets() {
        return assetRepository.findAll();
    }

    public Asset getAssetById(String assetId) {
        return assetRepository.findById(assetId).orElse(null);
    }

   public Asset saveAsset(Asset asset) {
    if(asset.getName() == null || asset.getName().isEmpty()) {
        
        throw new IllegalArgumentException("Asset name cannot be empty");
    }
    
    return assetRepository.save(asset);
}

    public void deleteAsset(String assetId) {
        assetRepository.deleteById(assetId);
    }

    public List<Asset> searchAssets(String keyword) {
        return assetRepository.searchAssets(keyword);
    }

    public List<Asset> findByInvoiceId(String invoiceId) {
        return assetRepository.findByInvoiceId(invoiceId);
    }

    public List<Asset> findByName(String name) {
        return assetRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Asset> findByType(String type) {
        return assetRepository.findByType(type);
    }

    public List<Asset> findByActivityStatus(boolean status) {
        return assetRepository.findByActivityStatus(status);
    }
}