package com.example.application.services;

import com.example.application.Repositories.CompanyRepositories;
import com.example.application.models.Company;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CompanyServicesImp implements CompanyServices {
    final CompanyRepositories companyRepositories;

    public CompanyServicesImp(CompanyRepositories companyRepositories) {
        this.companyRepositories = companyRepositories;
    }

    @Override
    public Company save(Company c) {
        return companyRepositories.save(c);
    }

    @Override
    public void delete(Company company) {
        companyRepositories.delete(company);
    }

    @Override
    public Set<Company> getList() {
        Set<Company> companySet=new HashSet<>();
        companyRepositories.findAll().iterator().forEachRemaining(companySet::add);
        return companySet;
    }

    @Override
    public Set<Company> getRecipes() {
        return null;
    }

    @Override
    public Company findById(long id) {
        Optional<Company> company=companyRepositories.findById(id);
        if (!company.isPresent()){
            throw new RuntimeException("hata");
        }
        return company.get();
    }
}
