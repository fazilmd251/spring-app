package com.fazil.my_first_app.company.impl;

import com.fazil.my_first_app.company.Company;
import com.fazil.my_first_app.company.CompanyRepository;
import com.fazil.my_first_app.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImp implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImp(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getCompanies() {
        return this.companyRepository.findAll();
    }

    @Override
    public Company getCompany(Long id) {
        return this.companyRepository.findById(id).orElse(null);
    }

    @Override
    public Company createCompany(Company company) {
        return this.companyRepository.save(company);
    }

    @Override
    public Company updateCompany(Company company, Long id) {
        Company toUpdate = this.companyRepository.findById(id).orElse(null);
        if (toUpdate != null) {
            toUpdate.setName(company.getName());
            return this.companyRepository.save(toUpdate);
        }
        return null;
    }

    @Override
    public boolean deleteCompany(Long id) {
        try {
            this.companyRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
