package com.tms.myproject.service.Author;

import com.tms.myproject.model.Author;
import com.tms.myproject.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author findByAuthorFullName(String fullName) {
        return authorRepository.findByAuthorFullName(fullName);
    }
}
