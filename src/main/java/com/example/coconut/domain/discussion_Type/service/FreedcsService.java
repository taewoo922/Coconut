package com.example.coconut.domain.discussion_Type.service;

import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.repository.FreedcsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
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

    public void free_create(String title, String content,MultipartFile thumbnail) {
        String thumbnailRelPath = "freedcs/" + UUID.randomUUID().toString() + ".jpg";
        File thumbnailFile = new File(fileDirPath + "/" +thumbnailRelPath);

        try {
            thumbnail.transferTo(thumbnailFile);
        } catch( IOException e ) {
            throw new RuntimeException(e);
        }

        Freedcs freedcs = Freedcs.builder()
                .title(title)
                .content(content)
                .thumbnailImg(thumbnailRelPath)
                .build();
        freedcsRepository.save(freedcs);
    }


}
