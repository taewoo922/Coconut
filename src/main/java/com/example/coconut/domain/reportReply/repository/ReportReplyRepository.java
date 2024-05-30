package com.example.coconut.domain.reportReply.repository;

import com.example.coconut.domain.reportReply.entity.ReportReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportReplyRepository extends JpaRepository<ReportReply, Long> {

}
