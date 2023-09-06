package com.codeup.codeupspringblog.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query
    List<Post> findByTitleLike(String term);

    @Query("FROM Post p WHERE p.id LIKE ?1")
    Post searchPostsById(long id);

}
