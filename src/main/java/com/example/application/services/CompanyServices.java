package com.example.application.services;

import com.example.application.models.Company;


import java.util.Set;

public interface CompanyServices {
    Company save (Company c);
    void delete(Company company);
    Set<Company> getList();
    Set<Company> getRecipes();
    Company findById(long id);
}
