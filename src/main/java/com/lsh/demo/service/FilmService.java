package com.lsh.demo.service;

import com.lsh.demo.pojo.Film;

import java.util.List;

public interface FilmService {

    Film findOneById(Integer id);

    List<Film> findAll();

    boolean modify(Film film) throws Exception;

    boolean add(Film film);

    boolean delete(Integer id);

}
