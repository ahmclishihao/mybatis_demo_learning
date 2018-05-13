package com.lsh.demo.service;

import com.lsh.demo.mapper.FilmMapper;
import com.lsh.demo.pojo.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmMapper filmMapper;

    @Override
    public Film findOneById(Integer id) {
        return filmMapper.getFilmById(id);
    }

    @Override
    public List<Film> findAll() {
        return filmMapper.getFilmAll();
    }

    @Override
    public boolean modify(Film film) {
        return filmMapper.updateFilm(film);
    }

    @Override
    public boolean add(Film film) {
        return filmMapper.insertFilm(film);
    }

    @Override
    public boolean delete(Integer id) {
        return filmMapper.deleteFilm(id);
    }
}
