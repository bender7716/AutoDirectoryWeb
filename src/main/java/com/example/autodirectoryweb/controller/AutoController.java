package com.example.autodirectoryweb.controller;

import com.example.autodirectoryweb.entity.Auto;
import org.springframework.ui.Model;

public interface AutoController {

    String list(Model model);

    String create(Model model);

    String edit(Long id,
                Model model);

    String save(Long id,
                Auto auto);

    String delete(Long id);

}
