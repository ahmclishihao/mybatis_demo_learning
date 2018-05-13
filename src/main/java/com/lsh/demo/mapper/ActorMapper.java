package com.lsh.demo.mapper;

import com.lsh.demo.pojo.Actor;

public interface ActorMapper {

//    @ResultType(Actor.class)
//    @Select({"select * from actor where actor_id = #{id}"})
    Actor getActorById(Integer id);

    boolean addActor(Actor actor);

}
