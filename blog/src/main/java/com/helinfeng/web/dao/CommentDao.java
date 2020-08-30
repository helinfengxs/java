package com.helinfeng.web.dao;

import com.helinfeng.web.domain.Comment;

import java.util.List;

public interface CommentDao {
    List<Comment> selectById(String blogId);

    int insertComment(Comment comment);
}
