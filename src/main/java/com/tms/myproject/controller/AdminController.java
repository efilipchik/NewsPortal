package com.tms.myproject.controller;

import com.tms.myproject.model.News;
import com.tms.myproject.service.Author.AuthorService;
import com.tms.myproject.service.News.NewsService;
import com.tms.myproject.service.Tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RequestMapping("admin")
@Controller
public class AdminController {

    private final NewsService newsService;
    private final AuthorService authorService;
    private final TagService tagService;

    @Autowired
    public AdminController(NewsService newsService, AuthorService authorService, TagService tagService) {
        this.newsService = newsService;
        this.authorService = authorService;
        this.tagService = tagService;
    }

    @GetMapping(value = "getAllNews")
    public String getAllNews(Model model) {
        List<News> allNews = newsService.findAllNews();
        model.addAttribute("Newss", allNews);
        return "getAllNews";
    }

    @PostMapping(value = "deleteNews")
    public RedirectView postDeleteNews(@RequestParam Long id) {
        newsService.changeActive(id);
        return new RedirectView("getAllNews");
    }
}
