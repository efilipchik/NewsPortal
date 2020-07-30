package com.tms.myproject.service.Tag;

import com.tms.myproject.model.Tag;

public interface TagService {
    Tag saveTag(Tag tag);
    Tag findTagTagName (String tagName);
}
