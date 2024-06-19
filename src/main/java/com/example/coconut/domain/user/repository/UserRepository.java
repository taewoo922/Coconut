package com.example.coconut.domain.user.repository;

import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Page<User> findAll(Pageable pageable);

    Page<User> findAll(Specification<Freedcs> spec, Pageable pageable);

}
