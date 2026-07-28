package com.luyingqinhua.service;

import com.luyingqinhua.entity.Product;
import com.luyingqinhua.entity.ProductStat;
import com.luyingqinhua.entity.ProductTag;
import com.luyingqinhua.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAll() {
        return productRepo.findAllByOrderBySortOrderAsc();
    }

    public Product getById(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("产品不存在: " + id));
    }

    @Transactional
    public Product update(Long id, Product updated) {
        Product product = getById(id);
        if (updated.getSubLabel() != null) product.setSubLabel(updated.getSubLabel());
        if (updated.getName() != null) product.setName(updated.getName());
        if (updated.getNamePrefix() != null) product.setNamePrefix(updated.getNamePrefix());
        if (updated.getDescription() != null) product.setDescription(updated.getDescription());
        if (updated.getImageUrl() != null) product.setImageUrl(updated.getImageUrl());
        if (updated.getImagePlaceholder() != null) product.setImagePlaceholder(updated.getImagePlaceholder());

        // 更新标签
        if (updated.getTags() != null && !updated.getTags().isEmpty()) {
            product.getTags().clear();
            for (ProductTag tag : updated.getTags()) {
                tag.setProduct(product);
                product.getTags().add(tag);
            }
        }

        // 更新数据
        if (updated.getStats() != null && !updated.getStats().isEmpty()) {
            product.getStats().clear();
            for (ProductStat stat : updated.getStats()) {
                stat.setProduct(product);
                product.getStats().add(stat);
            }
        }

        return productRepo.save(product);
    }
}
