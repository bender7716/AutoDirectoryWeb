package com.example.autodirectoryweb.service;

import com.example.autodirectoryweb.entity.Auto;


import java.util.List;

public interface AutoService {

    List<Auto> getAll();
    Auto getById(Long id);
    void create(Auto auto);
    void update(Long id, Auto auto);
    void delete(Long id);

}
