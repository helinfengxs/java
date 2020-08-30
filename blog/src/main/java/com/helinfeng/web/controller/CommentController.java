package com.helinfeng.web.controller;

import com.helinfeng.web.domain.Comment;
import com.helinfeng.web.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CommentController {
    @Resource
    private CommentService commentService;
    @GetMapping("/comments/{blog_id}")
    public String commentList(@PathVariable String blog_id, Model model){
        List<Comment> commentList = commentService.listCommentByBlogId(blog_id);
        model.addAttribute("comments",commentList);
        return "info001 :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment){
        commentService.saveComment(comment);
        System.out.println(comment.getBlog_id());
        return "redirect:/comments/" + comment.getBlog_id();

    }
}
