package com.narvee.serviceimpl;

import com.narvee.entity.Company;
import com.narvee.repository.CompanyRepository;
import com.narvee.service.CompanyService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository repository;

    public CompanyServiceImpl(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return repository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }

    @Override
    public Company createCompany(Company company) {
        return repository.save(company);
    }

    @Override
    public Company updateCompany(Long id, Company companyDetails) {
        Company company = getCompanyById(id);
        company.setName(companyDetails.getName());
        company.setEmail(companyDetails.getEmail());
        company.setAddress(companyDetails.getAddress());
        company.setPhoneNo(companyDetails.getPhoneNo());
        company.setWebsiteUrl(companyDetails.getWebsiteUrl());
        company.setCountry(companyDetails.getCountry());
        return repository.save(company);
    }

    @Override
    public void deleteCompany(Long id) {
        repository.deleteById(id);
    }
    @Override
    public List<Company> searchCompanies(Company company) {
        return repository.findAll().stream()
                .filter(c -> company.getName() == null ||
                             c.getName().toLowerCase().contains(company.getName().toLowerCase()))
                .filter(c -> company.getEmail() == null ||
                             c.getEmail().toLowerCase().contains(company.getEmail().toLowerCase()))
                .filter(c -> company.getPhoneNo() == null ||
                             c.getPhoneNo().equals(company.getPhoneNo()))
                .filter(c -> company.getAddress() == null ||
                             c.getAddress().toLowerCase().contains(company.getAddress().toLowerCase()))
                .filter(c -> company.getWebsiteUrl() == null ||
                             c.getWebsiteUrl().toLowerCase().contains(company.getWebsiteUrl().toLowerCase()))
                .filter(c -> company.getCountry() == null ||
                             c.getCountry().toLowerCase().contains(company.getCountry().toLowerCase()))
                .collect(Collectors.toList());
    }
}
