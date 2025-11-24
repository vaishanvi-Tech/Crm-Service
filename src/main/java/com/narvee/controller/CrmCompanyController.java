package com.narvee.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.narvee.commons.PaginatedResponse;
import com.narvee.commons.RestAPIResponse;
import com.narvee.entity.Company;
import com.narvee.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CrmCompanyController {

    private final CompanyService service;

    public CrmCompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public ResponseEntity<RestAPIResponse> getAllCompanies() {
        List<Company> companies = service.getAllCompanies();
        return ResponseEntity.ok(new RestAPIResponse("Success", "Companies retrieved successfully", companies));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestAPIResponse> getCompanyById(@PathVariable Long id) {
        Company company = service.getCompanyById(id);
        if (company != null) {
            return ResponseEntity.ok(new RestAPIResponse("Success", "Company retrieved successfully", company));
        } else {
            return ResponseEntity.status(404).body(new RestAPIResponse("Failed", "Company not found"));
        }
    }

    @PostMapping("/save")
    public ResponseEntity<RestAPIResponse> createCompany(@RequestBody Company company) {
        try {
            Company savedCompany = service.createCompany(company);
            return ResponseEntity.ok(new RestAPIResponse("Success", "Company created successfully", savedCompany));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new RestAPIResponse("Failed", "Error creating company: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestAPIResponse> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        try {
            Company updatedCompany = service.updateCompany(id, company);
            return ResponseEntity.ok(new RestAPIResponse("Success", "Company updated successfully", updatedCompany));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new RestAPIResponse("Failed", "Error updating company: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestAPIResponse> deleteCompany(@PathVariable Long id) {
        try {
            service.deleteCompany(id);
            return ResponseEntity.ok(new RestAPIResponse("Success", "Company deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(new RestAPIResponse("Failed", "Error deleting company: " + e.getMessage()));
        }
    }

    @PostMapping("/search")
    public ResponseEntity<RestAPIResponse> searchCompanies(@RequestBody Company company) {
        List<Company> results = service.searchCompanies(company);
        return ResponseEntity.ok(new RestAPIResponse("Success", "Search completed successfully", results));
    }
    @GetMapping("/paged")
    public ResponseEntity<RestAPIResponse> getPagedCompanies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            Company filter
    ) {
        Page<Company> paged = service.getCompaniesPaged(filter, page, size, sortBy, sortDir);

        return ResponseEntity.ok(
                new RestAPIResponse(
                        "Success",
                        "Paged Companies Retrieved Successfully",
                        new PaginatedResponse(
                                paged.getContent(),
                                paged.getNumber(),
                                paged.getSize(),
                                paged.getTotalElements(),
                                paged.getTotalPages(),
                                paged.hasNext(),
                                paged.hasPrevious()
                ))
        );
    }

}
