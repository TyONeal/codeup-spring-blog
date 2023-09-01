package com.codeup.codeupspringblog.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("FROM Post p WHERE p.title LIKE %:term%")
    List<Post> searchByTitleLike(@Param("term") String term);
}