package com.lsh.demo.controller;

import com.lsh.demo.bean.ReturnBean;
import com.lsh.demo.pojo.Film;
import com.lsh.demo.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping("/")
    public String main(){
        return "/film/main";
    }

    @PostMapping("/page")
    @ResponseBody
    public Object page(int page,int limit){
        List<Film> all = filmService.findAll();
        List<Film> pageContext = all
                                    .stream()
                                    .skip((page - 1) * limit)
                                    .limit(limit)
                                    .collect(Collectors.toList());
        return ReturnBean.page(pageContext,all.size()).msg("查询成功");
    }

}
