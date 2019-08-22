package com.mitrais.cdc.blogmicroservices.repository;


import com.mitrais.cdc.blogmicroservices.entity.Category;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Category entity.
 */

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
