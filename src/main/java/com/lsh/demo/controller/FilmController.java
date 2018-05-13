package com.lsh.demo.controller;

import com.lsh.demo.mapper.ActorMapper;
import com.lsh.demo.pojo.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FilmController {

    @Autowired
    ActorMapper actorMapper;

    @RequestMapping("/")
    public String main(){
        System.out.println("coming coming coming");
        Actor actorById = actorMapper.getActorById(1);
        return "/film/main";
    }

}
