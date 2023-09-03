package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.entities.Post;
import com.codeup.codeupspringblog.entities.PostRepository;
import com.codeup.codeupspringblog.entities.User;
import com.codeup.codeupspringblog.entities.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private User user;
    public PostController(UserRepository userDao, PostRepository postDao) {
        this.userDao = userDao;
        this.postDao = postDao;
    }


    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public String posts(Model model) {

        model.addAttribute("post", postDao.findAll());
        return "posts/index";
    }

    @RequestMapping(path = "/singlepost", method = RequestMethod.GET)
    public String postsById(Model model) {

        Post post = postDao.searchPostsById(11);
        String email = post.getUser().getEmail();

        model.addAttribute("post", post);
        model.addAttribute("email", email);
        return "posts/show";
    }

    @RequestMapping(path = "posts/create", method = RequestMethod.GET)
    public String viewCreatePost(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @RequestMapping(path = "posts/create", method = RequestMethod.POST)
    public String createPost(@ModelAttribute Post post) {
        postDao.save(post);
        return "redirect:/posts";
    }

    @RequestMapping(path = "posts/{id}/edit", method = RequestMethod.GET)
    public String viewEditPost(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.searchPostsById(id));
        return "posts/create";
    }

    @RequestMapping(path = "posts/{id}/edit", method = RequestMethod.POST)
        public String editPost(@ModelAttribute Post post) {
        postDao.save(post);
        return "redirect:/posts";
    }
}
