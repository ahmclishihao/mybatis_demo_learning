package com.lsh.demo.mapper;

import com.lsh.demo.pojo.Actor;

public interface ActorMapper {

    Actor getActorById(Integer id);

    boolean addActor(Actor actor);

}
