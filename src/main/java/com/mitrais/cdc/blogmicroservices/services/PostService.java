package com.mitrais.cdc.blogmicroservices.services;

import com.mitrais.cdc.blogmicroservices.payload.PostPayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface PostService {

    PostPayload save(PostPayload postDTO);
    Page<PostPayload> findAll(Pageable pageable);
    Optional<PostPayload> findOne(Long id);
    Optional<PostPayload> findByTitle(String title);
    void delete(Long id);
}
