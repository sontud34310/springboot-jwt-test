package com.sontud.jwt.repository;
import com.sontud.jwt.model.UserDao;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDao, String> {
    UserDao findByUsername(String username);
}