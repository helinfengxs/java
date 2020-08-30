package com.helinfeng.web.service.impl;

import com.helinfeng.util.DateTimeUtil;
import com.helinfeng.util.UUIDUtil;
import com.helinfeng.web.dao.CommentDao;
import com.helinfeng.web.domain.Comment;
import com.helinfeng.web.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentDao commentDao;

    @Override
    public List<Comment> listCommentByBlogId(String blogId) {

        return  commentDao.selectById(blogId);
    }

    @Override
    public int saveComment(Comment comment) {
        if("-1".equals(comment.getParent_id()) ){
            comment.setParent_id(null);
        }
        comment.setId(UUIDUtil.getUUID());
        comment.setCreate_time(DateTimeUtil.getSysTime());
        comment.setAvatar("/static/images/header.jpg");
        int insertResult = commentDao.insertComment(comment);
        System.out.println(comment);
        return insertResult;
    }
}
