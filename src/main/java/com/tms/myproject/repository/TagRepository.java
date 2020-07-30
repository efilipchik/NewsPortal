package com.tms.myproject.repository;

import com.tms.myproject.model.Author;
import com.tms.myproject.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query("from Tag t where t.id=?1" )
    Tag findByTagId(Long id);

    @Query("from Tag t where t.tagName=?1" )
    Tag findTagTagName(String tagName);
}
