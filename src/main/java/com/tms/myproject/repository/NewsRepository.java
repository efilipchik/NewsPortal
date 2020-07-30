package com.tms.myproject.repository;

import com.tms.myproject.model.News;
import net.bytebuddy.description.NamedElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    @Query("from News n where n.theme=?1")
    News findByTheme(String theme);

    @Query("from News n where n.author.fullName=?1")
    List<News> findAllByAuthor(String author);

    @Query("from News n where n.tag.tagName=?1")
    List<News> findAllByTag(String tag);

    void deleteById(Long id);

    @Query("from News n where n.id=?1")
    News findNewsById(Long id);

    @Query("from News n where n.active=true")
    List<News> findAllActiveNews();

}
