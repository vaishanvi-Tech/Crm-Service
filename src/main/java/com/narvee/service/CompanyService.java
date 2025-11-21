package com.narvee.service;

import com.narvee.entity.Company;
import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    Company getCompanyById(Long id);
    Company createCompany(Company company);
    Company updateCompany(Long id, Company company);
    void deleteCompany(Long id);
    List<Company> searchCompanies(Company company);
}
