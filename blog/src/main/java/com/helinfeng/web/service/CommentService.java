package com.helinfeng.web.service;

import com.helinfeng.web.domain.Comment;

import java.util.List;


public interface CommentService {
    List<Comment> listCommentByBlogId(String id);
    int saveComment(Comment comment);
}
