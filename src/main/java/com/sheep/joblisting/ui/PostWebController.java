package com.sheep.joblisting.ui;

import com.sheep.joblisting.repository.PostRepository;
import com.sheep.joblisting.repository.SearchRepository;
import com.sheep.joblisting.controller.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostWebController {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private SearchRepository searchRepo;

    // Display the main UI page
    @GetMapping({"/", "/posts-ui"})
    public String viewPosts(Model model) {
        List<Post> posts = postRepo.findAll();
        model.addAttribute("posts", posts);
        return "posts-ui"; // refers to src/main/resources/templates/posts-ui.html
    }

    // Handle search queries submitted via the UI
    @GetMapping("/posts-ui/search")
    public String searchPosts(@RequestParam("keyword") String keyword, Model model) {
        List<Post> posts = searchRepo.findByText(keyword);
        model.addAttribute("posts", posts);
        model.addAttribute("keyword", keyword);
        return "posts-ui";
    }

    // Handle new post form submissions
    @PostMapping("/posts-ui/add")
    public String addPost(@RequestParam("profile") String profile,
                          @RequestParam("desc") String desc,
                          @RequestParam("exp") String exp,
                          @RequestParam("techs") String techs) {
        Post post = new Post();
        post.setProfile(profile);
        post.setDesc(desc);
        post.setExp(exp);
        // Expecting a comma-separated list of techs, split and trim them
        post.setTechs(techs.split("\\s*,\\s*"));
        postRepo.save(post);
        return "redirect:/posts-ui";
    }
}
