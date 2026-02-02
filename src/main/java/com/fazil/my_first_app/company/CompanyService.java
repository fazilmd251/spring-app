package com.fazil.my_first_app.company;

import java.util.List;

public interface CompanyService {
    List<Company> getCompanies();
    Company getCompany(Long id);
    Company createCompany(Company company);
    Company updateCompany(Company company,Long id);
    boolean deleteCompany(Long id);
}
