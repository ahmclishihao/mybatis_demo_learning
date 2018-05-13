package com.lsh.demo.mapper;

import com.lsh.demo.pojo.Film;

import java.util.List;

public interface FilmMapper {

    Film getFilmById(Integer id);

    List<Film> getFilmAll();

    boolean updateFilm(Film film);

    boolean insertFilm(Film film);

    boolean deleteFilm(Integer id);

}
