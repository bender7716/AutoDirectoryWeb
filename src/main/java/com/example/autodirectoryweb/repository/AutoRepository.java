package com.example.autodirectoryweb.repository;

import com.example.autodirectoryweb.entity.Auto;

import java.util.List;

public interface AutoRepository {
    List<Auto> findAll();
    Auto findById(Long id);
    void create(Auto auto);
    void update(Long id, Auto auto);
    void delete(Long id);
    Auto findByRegistrationNumber(String number);
}
