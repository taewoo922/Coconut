package com.example.coconut.domain.discussion_Type.repository;

import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreedcsRepository extends JpaRepository<Freedcs, Integer> {


}
