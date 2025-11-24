package com.narvee.service;

import com.narvee.entity.Company;
import java.util.List;

import org.springframework.data.domain.Page;

public interface CompanyService {
    List<Company> getAllCompanies();
    Company getCompanyById(Long id);
    Company createCompany(Company company);
    Company updateCompany(Long id, Company company);
    void deleteCompany(Long id);
    List<Company> searchCompanies(Company company);
    Page<Company> getCompaniesPaged(Company filter, int page, int size, String sortBy, String sortDir);

}
