package com.example.coconut.domain.discussion_Type.repository;

import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreedcsRepository extends JpaRepository<Freedcs, Long>, JpaSpecificationExecutor<Freedcs> {
    Page<Freedcs> findAll(Pageable pageable);
    Page<Freedcs> findAllByCategory_Id(Long categoryId, Pageable pageable);
    Page<Freedcs> findAll(Specification<Freedcs> spec, Pageable pageable);

    List<Freedcs> findAllByOrderByCreateDateDesc(); //자유토론의 모든 게시글을 최신순으로 가져와 줌

    List<Freedcs> findAllByAuthor(User user);

    List<Freedcs> findAllByCategoryId(Long categoryId);

    Page<Freedcs> findAllByCategory_IdAndSearch(Long categoryId, String kw, Pageable pageable);

    List<Freedcs> findAllByOrderByViewDesc(); //자유토론이 조회수 순으로 가져와 줌

//    @Query("SELECT f FROM Freedcs f ORDER BY SIZE(f.voter) DESC")
//    List<Freedcs> findTop5ByOrderByVoterCountDesc(Pageable pageable);

    @Query("SELECT f FROM Freedcs f WHERE LOWER(f.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(f.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Freedcs> findByKeyword(@Param("keyword") String keyword);




}
