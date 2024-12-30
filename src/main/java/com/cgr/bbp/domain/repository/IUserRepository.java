package com.cgr.bbp.domain.repository;

import com.cgr.bbp.domain.models.UserModel;

public interface IUserRepository {
     
    UserModel findBySAMAccountName(String sAMAccountName);
    
    // UserModel save(UserModel user);
    // UserModel update(UserModel user);
    // void delete(UserModel user);
    // List<UserModel> findAll();
}
