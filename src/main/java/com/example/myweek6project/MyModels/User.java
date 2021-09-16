package com.example.myweek6project.MyModels;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private int user_id;
    private String first_name;
    private String last_name;
    private String password;
    private String email;
    private String username;
    private List<Post> userPost;
}
