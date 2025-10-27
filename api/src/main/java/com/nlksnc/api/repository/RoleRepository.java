package com.nlksnc.api.repository;

import com.nlksnc.api.model.ERole;
import com.nlksnc.api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("select r from Role r where r.name = :name")
    Optional<Role> findByName(@Param("name") ERole name);

    @Query("select u.id from User u where u.id = :id")
    List<Role> findByUserId(@Param("id") Long userId);
}
