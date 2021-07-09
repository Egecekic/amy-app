package com.example.application.Repositories;

import com.example.application.models.Company;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Optional;

public interface CompanyRepositories extends CrudRepository<Company,Long> {
    List<Company> findByCompanyName(String companyName);
}
