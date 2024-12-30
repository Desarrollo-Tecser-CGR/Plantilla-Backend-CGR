package com.cgr.bbp.infrastructure.persistence.adapter;

import org.springframework.stereotype.Component;

import com.cgr.bbp.application.auth.mapper.UserMapper;
import com.cgr.bbp.domain.models.UserModel;
import com.cgr.bbp.domain.repository.IUserRepository;
import com.cgr.bbp.infrastructure.persistence.entity.UserEntity;
import com.cgr.bbp.infrastructure.persistence.repository.auth.IAuthRepositoryJpa;

@Component
public class AuthRepositoryAdapterImpl implements IUserRepository {

  private final IAuthRepositoryJpa authRepositoryJpa;

  public AuthRepositoryAdapterImpl(IAuthRepositoryJpa authRepositoryJpa) {
    this.authRepositoryJpa = authRepositoryJpa;
  }

  @Override
  public UserModel findBySAMAccountName(String sAMAccountName) {

    try {
      UserEntity userEntity = authRepositoryJpa.findBysAMAccountName(sAMAccountName);
      if (userEntity.hashCode() > 0) {
        return UserMapper.INSTANCE.toUserEntity(userEntity);
      }

    } catch (Exception e) {
      // TODO: handle exception
    }
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findBySAMAccountName'");
  }

}
