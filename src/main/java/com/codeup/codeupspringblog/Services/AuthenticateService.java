package com.codeup.codeupspringblog.Services;

import com.codeup.codeupspringblog.entities.User;
import com.codeup.codeupspringblog.entities.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service("authenticateService")
public class AuthenticateService {
    private User authenticatedUser;
    private UserRepository userDao;

    public AuthenticateService(UserRepository userDao) {
        this.userDao = userDao;
    }


    public void getAuthenticatedUser(Model model) {
        Object authenticatedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (authenticatedUser instanceof UserDetails) {
            User castedUser = (User) authenticatedUser;
            long userId = castedUser.getId();
            User loginUser = userDao.getUserById(userId);
            model.addAttribute("user", loginUser);
        }
    }


//    public String getAuthenticatedUserName() {
//        return SecurityContextHolder.getContext().getAuthentication().getName();
//    }
//
//    public Authentication getAuthentication() {
//        return SecurityContextHolder.getContext().getAuthentication().;
//    }
}
