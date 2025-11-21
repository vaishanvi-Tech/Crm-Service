package com.narvee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.narvee.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

}
