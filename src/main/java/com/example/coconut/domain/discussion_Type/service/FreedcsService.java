package com.example.coconut.domain.discussion_Type.service;

import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.repository.FreedcsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class FreedcsService {
    private final FreedcsRepository freedcsRepository;

    public List<Freedcs> getList() {
        return freedcsRepository.findAll();
    }

//    public void create(String title, String content) {
//        Freedcs freedcs = Freedcs.builder()
//                .title(title)
//                .content(content)
//                .build();
//        freedcsRepository.save(freedcs);
//    }

}
