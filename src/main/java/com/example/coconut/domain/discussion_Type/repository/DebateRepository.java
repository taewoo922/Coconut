package com.example.coconut.domain.discussion_Type.repository;

import com.example.coconut.domain.discussion_Type.entity.Debate;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebateRepository extends JpaRepository<Debate, Long> {
    Page<Debate> findAll(Pageable pageable);
    Page<Debate> findAllByCategory_Id(Long categoryId, Pageable pageable);
    Page<Debate> findAll(Specification<Debate> spec, Pageable pageable);

    List<Debate> findAllByOrderByCreateDateDesc(); //자유토론의 모든 게시글을 최신순으로 가져와 줌

    List<Debate> findAllByAuthor(User user);

    List<Debate> findAllByCategoryId(Long categoryId);

    Page<Debate> findAllByCategory_IdAndSearch(Long categoryId, String kw, Pageable pageable);

}