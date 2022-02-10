package org.elsys.ip.blogpost.controllers;

import org.elsys.ip.blogpost.blogpost.Blogpost;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BlogpostController {

    Map<String, Blogpost> db = new HashMap<>();

    @GetMapping("/blogpost/create")
    public String blogpost(Model model) {
        model.addAttribute("blogpost", new Blogpost());
        return "create";
    }

    @PostMapping("/blogpost/create")
    public String createBlogpost(@ModelAttribute Blogpost blogpost, Model model) {

        db.put(blogpost.getId(), blogpost);

        return blogpost(model);
    }
}
