package com.tms.myproject.service.News;

import com.tms.myproject.model.News;

import java.util.List;

public interface NewsService {
    News saveNews(News news);
    News findByTheme(String theme);
    List<News> findAllNews();
    List<News> findAllByAuthor(String author);
    List<News> findAllByTag(String tag);
    void deleteById(Long id);
    News findNewsById(Long id);
    void changeActive(Long id);

}
