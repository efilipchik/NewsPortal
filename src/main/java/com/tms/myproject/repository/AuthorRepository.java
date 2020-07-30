package com.tms.myproject.repository;

import com.tms.myproject.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("from Author a where a.id=?1" )
    Author findByAuthorId(Long id);

    @Query("from Author a where a.fullName=?1" )
    Author findByAuthorFullName(String fullName);
}


