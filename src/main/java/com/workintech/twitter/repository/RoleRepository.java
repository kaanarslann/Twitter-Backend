package com.workintech.twitter.repository;

import com.workintech.twitter.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.authority = :authority")
    Role getRoleByAuthority(@Param("authority") String authority);
}
