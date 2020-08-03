package com.tms.myproject.service.News;

import com.tms.myproject.model.News;
import com.tms.myproject.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService{
    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public News saveNews(News news) {
       return newsRepository.save(news);
    }

    @Override
    public News findByTheme(String theme) {
        return newsRepository.findByTheme(theme);
    }

    @Override
    public List<News> findAllNews() {
        return newsRepository.findAllActiveNews();
    }

    @Override
    public List<News> findAllByAuthor(String author) {
        return newsRepository.findAllByAuthor(author);
    }

    @Override
    public List<News> findAllByTag(String tag) {
        return newsRepository.findAllByTag(tag);
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public News findNewsById(Long id) {
        return newsRepository.findNewsById(id);
    }

    @Override
    public void changeActive(Long id) {
        News news = newsRepository.findNewsById(id);
        news.setActive(!news.isActive());
        newsRepository.save(news);
    }
}
