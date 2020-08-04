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
    public String getSaveNews() {
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
        if (tag1 != null) {
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
    public String getGetAllNewsByAuthor() {
        return "getAllNewsByAuthor";
    }

    @PostMapping(value = "getAllNewsByAuthor")
    public String postGetAllNewsByAuthor(@RequestParam(required = false) String author, Model model) {
        List<News> allNewsByAuthor = newsService.findAllByAuthor(author);
        model.addAttribute("News", allNewsByAuthor);
        return "getAllNewsByAuthor";
    }

    @GetMapping(value = "getAllNewsByTag")
    public String getGetAllNewsByTag() {
        return "getAllNewsByTag";
    }

    @PostMapping(value = "getAllNewsByTag")
    public String postGetAllNewsByTag(@RequestParam(required = false) String tag, Model model) {
        List<News> allNewsByTag = newsService.findAllByTag(tag);
        model.addAttribute("News", allNewsByTag);
        return "getAllNewsByTag";
    }

    @GetMapping(value = "updateNews")
    public String getUpdateNews() {
        return "updateNews";
    }

    @PostMapping(value = "updateNews")
    public String postUpdateNews(@RequestParam(required = false) Long id,
                                 @RequestParam(required = false) String theme,
                                 @RequestParam(required = false) String content,
                                 @RequestParam(required = false) Double rating,
                                 @RequestParam(required = false) String tag,
                                 @RequestParam(required = false) String author,
                                 Model model) {
        Author author1 = (Author) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Author author2 = new Author();
        author2.setFullName(author);
        if (author2.equals(author1)) {
            News news = new News();
            news.setId(id);
            news.setTheme(theme);
            news.setContent(content);
            news.setRating(rating);
            Tag tag1 = new Tag();
            tag1.setTagName(tag);
            news.setTag(tag1);
            model.addAttribute("News", news);
            return "updateNews";
        } else return "notYourNews";
    }

    @PostMapping(value = "saveUpdateNews")
    public RedirectView postSaveUpdateNews(@RequestParam(required = false) Long id,
                                           @RequestParam(required = false) String theme,
                                           @RequestParam(required = false) String content,
                                           @RequestParam(required = false) Double rating,
                                           @RequestParam(required = false) String tag) {
        News news = newsService.findNewsById(id);
        news.setTheme(theme);
        news.setContent(content);
        news.setRating(rating);
        Tag tag1 = new Tag();
        tag1.setTagName(tag);
        tagService.saveTag(tag1);
        news.setTag(tag1);
        newsService.saveNews(news);
        return new RedirectView("getAllNews");
    }

    @PostMapping(value = "ratingNew")
    public Object postRatingNew(@RequestParam(required = false) Long id,
                                @RequestParam(required = false) Double rating,
                                @RequestParam(required = false) String ratingNew,
                                @RequestParam(required = false) String author) {
        Author author1 = (Author) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Author author2 = new Author();
        author2.setFullName(author);
        if (!author1.equals(author2)) {
            News news = newsService.findNewsById(id);
            boolean status = true;
            for (Author authorRated: news.getAuthorRated()) {
                if (authorRated.getFullName().equals(author1.getFullName())) {
                status = false;
                }
            }
            if (status) {
                Double ratingNew1 = new Double(ratingNew);
                if (rating != null) {
                    Double rating1 = (rating + ratingNew1) / 2;
                    news.setRating(rating1);
                } else {
                    news.setRating(ratingNew1);
                }
                List<Author> authorRatedList = news.getAuthorRated();

                authorRatedList.add(authorService.findByAuthorFullName(author1.getFullName()));
                news.setAuthorRated(authorRatedList);
                newsService.saveNews(news);
            }

                return new RedirectView("getAllNews");
            } else return "notYourNews";
    }

}