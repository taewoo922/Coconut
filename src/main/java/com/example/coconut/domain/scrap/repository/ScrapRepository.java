package com.example.coconut.domain.scrap.repository;

import com.example.coconut.domain.scrap.entity.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    List<Scrap> findByFreedcsId(Long freedcsId);

    List<Scrap> findByDebateId(Long debateId);
}
