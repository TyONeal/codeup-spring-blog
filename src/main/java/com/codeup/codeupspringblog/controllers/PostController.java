package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.Services.AuthenticateService;
import com.codeup.codeupspringblog.Services.EmailService;
import com.codeup.codeupspringblog.entities.Post;
import com.codeup.codeupspringblog.entities.PostRepository;
import com.codeup.codeupspringblog.entities.User;
import com.codeup.codeupspringblog.entities.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;
    private final AuthenticateService authenticateService;
    public PostController(UserRepository userDao, PostRepository postDao, EmailService emailService, AuthenticateService authenticateService) {
        this.userDao = userDao;
        this.postDao = postDao;
        this.emailService = emailService;
        this.authenticateService = authenticateService;
    }


    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public String posts(Model model) {
        authenticateService.getAuthenticatedUser(model);
        model.addAttribute("post", postDao.findAll());
        return "posts/index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String postsById(@PathVariable long id, Model model) {

        Post post = postDao.searchPostsById(id);
        String email = post.getUser().getEmail();

        model.addAttribute("post", post);
        model.addAttribute("email", email);
        return "posts/show";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    public String viewCreatePost(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String createPost(@ModelAttribute Post post) {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        post.setUser(authenticatedUser);
        postDao.save(post);
        emailService.prepareAndSend(post, "send this email", "email body");
        return "redirect:/posts";
    }
//    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
//    public String publishPost(
//           @ModelAttribute@Valid Post post,
//            Errors validation,
//            Model model
//    ) {
//        //authentication
//        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if(authenticatedUser != null) {
//
//
//            if(validation.hasErrors()) {
//                model.addAttribute("errors", validation);
//                model.addAttribute("post", post);
//                return "posts/create";
//            }
//            return "redirect:/";
//
//        }
//
//
//
//
//
//        }
//
//
//        //validation
//       if(validation.hasErrors()) {
//           model.addAttribute("errors", validation);
//           model.addAttribute("post", post);
//           return "posts/create";
//       }
//        return "redirect:/";
//
//    }

    @RequestMapping(path = "/posts/edit", method = RequestMethod.GET)
    public String viewEditPost(Model model) {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authenticatedUser != null) {
            Post emptyPost = new Post();
            model.addAttribute("post", emptyPost);
            return "posts/edit";
        } else {
            return "redirect:/login";
        }
    }


    @RequestMapping(path = "/posts/edit", method = RequestMethod.POST)
    public String handleEditForm(@RequestParam(name = "id") Long postId, Model model) {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authenticatedUser != null) {
            // Retrieve the post using the postId parameter
            Post postToEdit = postDao.searchPostsById(postId);

            if (postToEdit != null) {
                model.addAttribute("post", postToEdit);
                return "posts/edit";
            } else {
                return "redirect:/error";
            }
        } else {
            return "redirect:/login";
        }
    }

}
