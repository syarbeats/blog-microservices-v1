package com.mitrais.cdc.blogmicroservices.repository;


import com.mitrais.cdc.blogmicroservices.entity.Comment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Comment entity.
 */

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
