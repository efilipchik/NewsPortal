package com.tms.myproject.controller;

import com.tms.myproject.model.Author;
import com.tms.myproject.model.News;
import com.tms.myproject.model.Tag;
import com.tms.myproject.service.Author.AuthorService;
import com.tms.myproject.service.News.NewsService;
import com.tms.myproject.service.Tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class NewsController {

    private final NewsService newsService;
    private final AuthorService authorService;
    private final TagService tagService;

   @Autowired
    public NewsController(NewsService newsService, AuthorService authorService, TagService tagService) {
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

    @GetMapping(value = "saveNews")
    public String getSaveNews(){
        return "saveNews";
    }

    @PostMapping(value = "saveNews")
    public RedirectView postSaveNews(@RequestParam(required = false) String theme,
                                     @RequestParam(required = false) String content,
                                     @RequestParam(required = false) String tag,
                                     @RequestParam(required = false) Double rating) {
        News news = new News();
        news.setTheme(theme);
        news.setContent(content);
        news.setRating(rating);
        Author author = (Author) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        news.setAuthor(author);
        Tag tag1 = tagService.findTagTagName(tag);
        if(tag1 != null) {
            news.setTag(tag1);
        } else {
            Tag tag2 = new Tag();
            tag2.setTagName(tag);
            tagService.saveTag(tag2);
            news.setTag(tag2);
        }
        newsService.saveNews(news);
        return new RedirectView("getAllNews");
    }

    @GetMapping(value = "getAllNewsByAuthor")
    public String getGetAllNewsByAuthor(){
       return "getAllNewsByAuthor";
    }

    @PostMapping(value = "getAllNewsByAuthor")
    public String postGetAllNewsByAuthor(@RequestParam(required = false) String author, Model model) {
        List<News> allNewsByAuthor = newsService.findAllByAuthor(author);
        model.addAttribute("News", allNewsByAuthor);
        return "getAllNewsByAuthor";
    }

    @GetMapping(value = "getAllNewsByTag")
    public String getGetAllNewsByTag(){
        return "getAllNewsByTag";
    }

    @PostMapping(value = "getAllNewsByTag")
    public String postGetAllNewsByTag(@RequestParam(required = false) String tag, Model model) {
        List<News> allNewsByTag = newsService.findAllByTag(tag);
        model.addAttribute("News", allNewsByTag);
        return "getAllNewsByTag";
    }

}
