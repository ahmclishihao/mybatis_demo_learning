package com.lsh.demo.service;

import com.lsh.demo.mapper.FilmMapper;
import com.lsh.demo.pojo.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public boolean modify(Film film) throws Exception{
        filmMapper.updateFilm(film);
        if(film.getCategory() != null){
            filmMapper.updateFilmCategory(film.getFilmId(),film.getCategory().getCategoryId());
        }
        return true;
    }

    @Override
    public boolean add(Film film) {
        return filmMapper.insertFilm(film);
    }

    @Override
    public boolean delete(Integer id) {
        try {
            return filmMapper.deleteFilm(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
