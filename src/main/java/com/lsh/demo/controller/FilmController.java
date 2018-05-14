package com.lsh.demo.controller;

import com.lsh.demo.bean.ReturnBean;
import com.lsh.demo.pojo.Category;
import com.lsh.demo.pojo.Film;
import com.lsh.demo.pojo.FilmRatingEnum;
import com.lsh.demo.service.CategoryService;
import com.lsh.demo.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FilmController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String main(ModelMap modelMap){
        List<Category> categories = categoryService.findAll();
        FilmRatingEnum[] ratings = FilmRatingEnum.values();

        modelMap.addAttribute("categories",categories);
        modelMap.addAttribute("ratings",ratings);
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

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(int id){
        boolean deleteResult = filmService.delete(id);
        return ReturnBean.ok(deleteResult).msg("操作完成");
    }

    @RequestMapping("/info")
    @ResponseBody
    public Object info(int id){
        Film oneById = filmService.findOneById(id);
        return ReturnBean.ok(oneById).msg("操作完成");
    }


}
