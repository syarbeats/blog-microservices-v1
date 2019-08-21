package com.mitrais.cdc.blogmicroservices.repository;

import com.mitrais.cdc.blogmicroservices.entity.Post;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Post entity.
 */

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
