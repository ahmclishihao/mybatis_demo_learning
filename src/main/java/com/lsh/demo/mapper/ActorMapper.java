package com.lsh.demo.mapper;

import com.lsh.demo.pojo.Actor;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorMapper {

    Actor getActorById(Integer id);

    boolean addActor(Actor actor);

}
