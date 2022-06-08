package com.practice.covidtracker.controller;

import com.practice.covidtracker.model.LocationStats;
import com.practice.covidtracker.services.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CovidDataService covidDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = covidDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(e->e.getLatestTotalCases()).sum();
        int newCasesReported = allStats.stream().mapToInt(e->e.getDiffFromPrevDay()).sum();
        System.out.println("covidDataService.getAllStats() = " + allStats);
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("newCasesReported", newCasesReported);
        return "home";
    }

}
