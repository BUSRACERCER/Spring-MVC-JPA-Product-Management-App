package com.works.mvcdata.repositories;

import com.works.mvcdata.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCidEqualsAllIgnoreCase(Long cid, Pageable pageable);

    List<Product> findByCidEquals(Long cid);

    boolean existsByPidEqualsAndCidEquals(Long pid, Long cid);

}