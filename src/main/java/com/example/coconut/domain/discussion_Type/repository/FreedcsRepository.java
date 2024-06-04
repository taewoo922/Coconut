package com.example.coconut.domain.discussion_Type.repository;

import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.report.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreedcsRepository extends JpaRepository<Freedcs, Long> {
    Page<Freedcs> findAll(Pageable pageable);
    Page<Freedcs> findAll(Specification<Freedcs> spec, Pageable pageable);
//    @Query("SELECT f FROM Freedcs f ORDER BY SIZE(f.voter) DESC")
//    List<Freedcs> findTop5ByOrderByVoterCountDesc(Pageable pageable);

}
