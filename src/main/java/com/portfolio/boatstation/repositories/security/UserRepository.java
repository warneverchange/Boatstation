package com.portfolio.boatstation.repositories.security;

import com.portfolio.boatstation.entities.views.security.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<User, Long> {

    @Query(value = "select * from _user where username = :username ", nativeQuery = true)
    Optional<User> getUserByUsername(@Param("username") String username);

    @Query(value = "select ur.name from _user" +
            " inner join _user_role ur on _user._user_role_id = ur.id" +
            " where _user.id = :#{#user.id}", nativeQuery = true)
    Optional<String> getUserAuthority(User user);

    @Modifying
    @Query(value = "insert into _user(username, password, email, _user_role_id, client_data_id) " +
            "values (:#{#user.username}, :#{#user.password}, :#{#user.email}, :#{#user.userRoleId}, :#{#user.clientDataId})", nativeQuery = true)
    void saveNewUser(User user);

    @Query(value = "select id from _user" +
            " where" +
            " username = :#{#user.username} and" +
            " password = :#{#user.password} and " +
            "email = :#{#user.email} and" +
            " _user_role_id = :#{#user.userRoleId} and" +
            " client_data_id = :#{#user.clientDataId}", nativeQuery = true)
    Optional<Long> getUserIdByUser(User user);
}
