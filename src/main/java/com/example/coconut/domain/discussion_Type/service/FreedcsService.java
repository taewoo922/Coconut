package com.example.coconut.domain.discussion_Type.service;




import com.example.coconut.DataNotFoundException;
import com.example.coconut.domain.category.entity.Category;



import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.repository.FreedcsRepository;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FreedcsService {
    private final FreedcsRepository freedcsRepository;

    @Value("${custom.fileDirPath}")
    private String fileDirPath;

    public List<Freedcs> getList() {
        return freedcsRepository.findAll();
    }

    public Freedcs getFreedcs(Long id) {
        Optional<Freedcs> freedcs = this.freedcsRepository.findById(id);
        if (freedcs.isPresent()) {
            return freedcs.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public Freedcs free_create(String title, String content, User user){
        Freedcs f = new Freedcs();
        f.setTitle(title);
        f.setContent(content);
        f.setAuthor(user);
        this.freedcsRepository.save(f);
        return f;
    }



//    public void free_create(String title, String content,MultipartFile thumbnail) {
//
//
//        String thumbnailRelPath = "freedcs/" + UUID.randomUUID().toString() + ".jpg";
//        File thumbnailFile = new File(fileDirPath + "/" +thumbnailRelPath);
//
//        try {
//            thumbnail.transferTo(thumbnailFile);
//        } catch( IOException e ) {
//            throw new RuntimeException(e);
//        }
//
//        Freedcs freedcs = Freedcs.builder()
//                .title(title)
//                .content(content)
//                .thumbnailImg(thumbnailRelPath)
////                .category(category)
//                .build();
//        freedcsRepository.save(freedcs);
//    }

    public void modify(Freedcs freedcs, String title, String content) {
        freedcs.setTitle(title);
        freedcs.setContent(content);
        this.freedcsRepository.save(freedcs);
    }

    public void delete(Freedcs freedcs){
        this.freedcsRepository.delete(freedcs);
    }



    public void vote(Freedcs freedcs, User user) {
        freedcs.getVoter().add(user);

        this.freedcsRepository.save(freedcs);

    }

//    public void vote(Freedcs freedcs, User voter) {
//        freedcs.addVoter(voter);
//
//        this.freedcsRepository.save(freedcs);
//
//    }



}
