package com.example.coconut.domain.scrap.service;

import com.example.coconut.DataNotFoundException;
import com.example.coconut.domain.discussion_Type.entity.Debate;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.repository.DebateRepository;
import com.example.coconut.domain.discussion_Type.repository.FreedcsRepository;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import com.example.coconut.domain.scrap.entity.Scrap;
import com.example.coconut.domain.scrap.repository.ScrapRepository;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScrapService {

    private final FreedcsRepository freedcsRepository;
    private final DebateRepository debateRepository;
    private final ScrapRepository scrapRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addScrap(Long freedcsId,User user) {
        Freedcs freedcs = freedcsRepository.findById(freedcsId).orElseThrow(() -> new IllegalArgumentException("유효하지 않는 id 입니다:" + freedcsId));

        Scrap scrap = new Scrap();
        scrap.setFreedcs(freedcs);
        scrap.setTitle(freedcs.getTitle());
        scrap.setContent(freedcs.getContent());
        scrap.setThumbnailImg(freedcs.getThumbnailImg());
        scrap.setAuthor(user);

        scrapRepository.save(scrap);
    }

    @Transactional
    public void addDebateScrap(Long debateId, User user) {
        Debate debate = debateRepository.findById(debateId).orElseThrow(() -> new IllegalArgumentException("유효하지 않는 id 입니다:" + debateId));

        Scrap scrap = new Scrap();
        scrap.setDebate(debate);
        scrap.setAuthor(user);
        scrap.setTitle(debate.getTitle());
        scrap.setContent(debate.getContent());
        scrap.setThumbnailImg(debate.getThumbnailImg());

        scrapRepository.save(scrap);
    }




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


    public List<Scrap> getScraps() {
        return scrapRepository.findAll();
    }

    public List<Scrap> getListByUserId(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return scrapRepository.findAllByAuthor(user);
        } else {
            throw new DataNotFoundException("User not found with id: " + id);
        }
    }
}
