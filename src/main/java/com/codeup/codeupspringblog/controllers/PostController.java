package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.entities.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public String posts(Model model) {
        ArrayList<Post> postList = new ArrayList<>();

        Post post1 = new Post();
        post1.setTitle("test title 1");
        post1.setBody("test body 1");

        Post post2 = new Post();
        post2.setTitle("test title 2");
        post2.setBody("test body 2");

        postList.add(post1);
        postList.add(post2);

        model.addAttribute("post", postList);
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
    @ResponseBody
    public String viewCreatePost() {
        return "view the form for creating a post";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String createPost() {
        return "create a new post";
    }

}
