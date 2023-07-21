package com.example.autodirectoryweb.controller;

import com.example.autodirectoryweb.entity.Auto;
import com.example.autodirectoryweb.service.AutoServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AutoControllerImpl implements AutoController {
    private final AutoServiceImpl service;

    public AutoControllerImpl(AutoServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/filter")
    public String filter(@ModelAttribute("filter") Auto filter, Model model) {
        List<Auto> autos = service.getFilteredAll(filter);
        model.addAttribute("autos", autos);
        model.addAttribute("filter", filter);
        return "home";
    }
    @Override
    @GetMapping("/")
    public String list(Model model) {
        List<Auto> autos = service.getAll();
        model.addAttribute("autos", autos);
        model.addAttribute("filter", new Auto());
        return "home";
    }
    @Override
    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("auto", new Auto());
        return "edit";
    }
    @Override
    @GetMapping("/{id}")
    public String edit(@PathVariable(value="id")Long id, Model model) {
        Auto auto = service.getById(id);
        model.addAttribute("auto", auto);
        return "edit";
    }
    @Override
    @PostMapping()
    public String save(@RequestParam(name = "id", required = false) Long id,
                       @ModelAttribute("auto") Auto auto) {
        if (id == null) {
            service.create(auto);
        } else {
            service.update(id, auto);
        }
        return "redirect:/";
    }
    @Override
    @PostMapping("/{id}")
    public String delete(@PathVariable(value="id")Long id) {
        service.delete(id);
        return "redirect:/";
    }
}
