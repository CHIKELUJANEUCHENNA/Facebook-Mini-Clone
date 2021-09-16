package com.example.myweek6project.MyModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Post {
    private int post_id;
    private int user_id;
    private String post_messages;
    private User user;
    private List<String> comments;
}
