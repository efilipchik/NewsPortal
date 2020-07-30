package com.tms.myproject.service.Author;

import com.tms.myproject.model.Author;

public interface AuthorService {
    Author saveAuthor(Author author);
    Author findByAuthorFullName (String fullName);
}
