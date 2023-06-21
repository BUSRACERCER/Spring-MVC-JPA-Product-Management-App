package com.works.mvcdata.services;

import com.works.mvcdata.entities.ProductImage;
import com.works.mvcdata.repositories.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    final ProductImageRepository imageRepository;

    public ProductImage addImage(ProductImage productImage) {
        imageRepository.save(productImage);
        return productImage;

    }

    public List<ProductImage> list(Long pid) {
        return imageRepository.findByPidEquals(pid);
    }

}
