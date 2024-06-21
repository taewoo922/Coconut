package com.example.coconut.domain.scrap.service;

import com.example.coconut.domain.discussion_Type.entity.Debate;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.repository.DebateRepository;
import com.example.coconut.domain.discussion_Type.repository.FreedcsRepository;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import com.example.coconut.domain.scrap.entity.Scrap;
import com.example.coconut.domain.scrap.repository.ScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScrapService {

    private final FreedcsRepository freedcsRepository;
    private final DebateRepository debateRepository;
    private final ScrapRepository scrapRepository;

    public void addScrap(Long freedcsId) {
        Freedcs freedcs = freedcsRepository.findById(freedcsId).orElseThrow(() -> new IllegalArgumentException("유효하지 않는 id 입니다:" + freedcsId));
        Scrap scrap = new Scrap();
        scrap.setFreedcs(freedcs);
        scrapRepository.save(scrap);
    }

    public void addDebateScrap(Long debateId) {
        Debate debate = debateRepository.findById(debateId).orElseThrow(() -> new IllegalArgumentException("유효하지 않는 id 입니다:" + debateId));
        Scrap scrap = new Scrap();
        scrap.setDebate(debate);
        scrapRepository.save(scrap);
    }

    public List<Scrap> getScrapsByFreedcsId(Long freedcsId) {
        return scrapRepository.findByFreedcsId(freedcsId);
    }

    public List<Scrap> getScrapsByDebateId(Long debateId) {
        return scrapRepository.findByDebateId(debateId);
    }


}
