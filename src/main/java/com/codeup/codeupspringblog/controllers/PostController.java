package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.entities.Post;
import com.codeup.codeupspringblog.entities.PostRepository;
import jakarta.security.auth.message.callback.PrivateKeyCallback;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public String posts(Model model) {

        model.addAttribute("post", postDao.findAll());
        return "posts/index";
    }

    @RequestMapping(path = "/singlepost", method = RequestMethod.GET)
    public String postsById(Model model) {
        Post post = new Post();
        post.setTitle("test title 1");
        post.setBody("test body 1");

        model.addAttribute("post", post);
        return "posts/show";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    public String viewCreatePost() {
        return "posts/create";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String createPost(@RequestParam(name = "post-title") String postTitle, @RequestParam(name = "post-body") String postBody, Model model) {
        Post createdPost = new Post();
        createdPost.setTitle(postTitle);
        createdPost.setBody(postBody);
        model.addAttribute("post", createdPost);
        postDao.save(createdPost);
        return "redirect:/posts";
    }
}
