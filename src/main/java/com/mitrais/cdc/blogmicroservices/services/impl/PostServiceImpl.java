package com.mitrais.cdc.blogmicroservices.services.impl;

import com.mitrais.cdc.blogmicroservices.entity.Post;
import com.mitrais.cdc.blogmicroservices.mapper.PostMapper;
import com.mitrais.cdc.blogmicroservices.mapper.PostMapperV1;
import com.mitrais.cdc.blogmicroservices.payload.PostPayload;
import com.mitrais.cdc.blogmicroservices.repository.PostRepository;
import com.mitrais.cdc.blogmicroservices.services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

    private final PostRepository postRepository;

    private final PostMapper postMapper;
    private final PostMapperV1 postMapperV1;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper, PostMapperV1 postMapperV1) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.postMapperV1 = postMapperV1;
    }

    @Override
    public PostPayload save(PostPayload postDTO) {
        log.debug("Request to save Post : {}", postDTO);
        Post post = postMapper.toEntity(postDTO);
        post = postRepository.save(post);
        return postMapper.toDto(post);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PostPayload> findAll(Pageable pageable) {
        log.debug("Request to get all Posts");
        return postRepository.findAll(pageable)
            .map(postMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PostPayload> findOne(Long id) {
        log.debug("Request to get Post : {}", id);
        return postRepository.findById(id)
            .map(postMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PostPayload> findByTitle(String title) {
        log.debug("Request to get Post : {}", title);
        return postRepository.findByTitle(title)
                .map(postMapperV1::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Post : {}", id);
        postRepository.deleteById(id);
    }
}
