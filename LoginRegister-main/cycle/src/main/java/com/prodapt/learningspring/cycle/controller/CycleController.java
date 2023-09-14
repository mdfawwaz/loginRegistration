package com.prodapt.learningspring.cycle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prodapt.learningspring.cycle.entity.Cycle;
import com.prodapt.learningspring.cycle.repository.CycleRepository;

import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CycleController {

    @Autowired
    private CycleRepository cycleRepo;

    private List<Cycle> cycleList;

    @PostConstruct
    public void init() {
        cycleList = new ArrayList<>();
        cycleRepo.findAll().forEach(cycleList::add);
    }

    @GetMapping("/addCycleForm")
    public String rent(Model model) {
        model.addAttribute("cycleList", cycleList);
        return "addCycleForm"; // Thymeleaf template path
    }
    
    @PostMapping("/borrow/{id}")
    public String borrowCycle(@PathVariable Long id, RedirectAttributes attr) {
        Cycle cycle = cycleRepo.findById(id).orElse(null);
        if (cycle != null && cycle.isAvailable()) {
            cycle.setAvailable(false); // Set available to false when rented
            cycleRepo.save(cycle);
            attr.addFlashAttribute("message", "Cycle rented successfully.");
        } else {
            attr.addFlashAttribute("error", "Cycle is not available for rent.");
        }
        return "redirect:/cycle/borrowCycle"; // Redirect to borrowCycle.html
    }

    @PostMapping("/return/{id}")
    public String returnCycle(@PathVariable Long id, RedirectAttributes attr) {
        Cycle cycle = cycleRepo.findById(id).orElse(null);
        if (cycle != null && !cycle.isAvailable()) {
            cycle.setAvailable(true); // Set available to true when returned
            cycleRepo.save(cycle);
            attr.addFlashAttribute("message", "Cycle returned successfully.");
        } else {
            attr.addFlashAttribute("error", "Cycle is not rented.");
        }
        return "redirect:/cycle/returnCycle"; // Redirect to returnCycle.html
    }
}
