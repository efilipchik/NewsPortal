package com.tms.myproject.service.Tag;

import com.tms.myproject.model.Tag;
import com.tms.myproject.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService{
    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag findTagTagName(String tagName) {
        return tagRepository.findTagTagName(tagName);
    }
}
