package com.mitrais.cdc.blogmicroservices.repository;

import com.mitrais.cdc.blogmicroservices.entity.Category;
import com.mitrais.cdc.blogmicroservices.entity.Post;
import com.mitrais.cdc.blogmicroservices.payload.CategoryPayload;
import com.mitrais.cdc.blogmicroservices.payload.PostPayload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;


/**
 * Spring Data  repository for the Post entity.
 */

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByTitle(String title);
    //Page<Post> findByCategory(Pageable pageable, String category);
}
