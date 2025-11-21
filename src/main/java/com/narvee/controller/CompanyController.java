package com.narvee.controller;

import com.narvee.entity.Company;
import com.narvee.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return service.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Long id) {
        return service.getCompanyById(id);
    }

    @PostMapping("/save")
    public Company createCompany(@RequestBody Company company) {
        return service.createCompany(company);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable Long id, @RequestBody Company company) {
        return service.updateCompany(id, company);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {
        service.deleteCompany(id);
    }
    @PostMapping("/search")
    public List<Company> searchCompanies(@RequestBody Company company) {
        return service.searchCompanies(company);
    }

}
