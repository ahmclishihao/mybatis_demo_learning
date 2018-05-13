package com.lsh.demo;

import com.lsh.demo.mapper.ActorMapper;
import com.lsh.demo.pojo.Actor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class UpStart {

    public static void main(String[] args) throws IOException {
        InputStream configInput = Resources.getResourceAsStream(UpStart.class.getClassLoader(),
                                                                     "mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configInput);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            ActorMapper mapper = sqlSession.getMapper(ActorMapper.class);

            Actor actor = new Actor();
            actor.setFirstName("W");
            actor.setLastName("M");
            actor.setLastUpdate(new Date());
            boolean i = mapper.addActor(actor);

            System.out.println(actor);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }

}
