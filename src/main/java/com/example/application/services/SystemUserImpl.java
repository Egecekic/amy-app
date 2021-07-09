package com.example.application.services;

import com.example.application.Repositories.SystemUserRepository;
import com.example.application.models.Company;
import com.example.application.models.SystemUser;
import com.example.application.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SystemUserImpl implements SystemUserService{

    private final SystemUserRepository systemUserRepository;
    private final UserServices userServices;
    private final CompanyServices companyServices;

    public SystemUserImpl(SystemUserRepository systemUserRepository, UserServices userServices, CompanyServices companyServices) {
        this.systemUserRepository = systemUserRepository;
        this.userServices = userServices;
        this.companyServices = companyServices;
    }

    @Override
    public SystemUser login(String email, String password) {
        List<SystemUser> result = systemUserRepository.findByEmailAndPassword(email,password);
        if (result.size()==0){
            return new SystemUser(email,password);
        }

        return result.get(0);
    }

    @Override
    public SystemUser save(SystemUser systemUser) {
        return systemUserRepository.save(systemUser);
    }

    @Override
    public List<SystemUser> findById(Long id) {
        List<SystemUser> systemUsers=new ArrayList<>();
        systemUserRepository.findById(id).stream().iterator().forEachRemaining(systemUsers::add);
        return systemUsers;
    }

    @Override
    public void register(String email, String password,String name,String lastName,String companyName,String companyJop) {
        SystemUser systemUser=new SystemUser(email,password);
        systemUserRepository.save(systemUser);
        System.out.println(systemUser);
        Company company=companyServices.save(new Company(companyName,companyJop));
        User user=userServices.save(new User(name,lastName,email,"",Long.parseLong(password)));
        user.getCompanies().add(company);
        user.setSystemUser(systemUser);
    }
}
