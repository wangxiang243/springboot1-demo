package com.wx.springbootdemo.service;

import com.mongodb.WriteResult;
import com.wx.springbootdemo.SpringbootDemoApplication;
import com.wx.springbootdemo.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootDemoApplication.class)
public class MongoServiceTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * insert对象时,id不能有值，如果存在id，则必须不能与已有id重复，否则报主键重复错误
     * save对象时，如果存在id，则根据id区更新对象
     */
    @Test
    public void testInsert() {

        Query query = new Query();
//        query.addCriteria(Criteria.where("name").is("Eric"));
        List<MongoUser> userInfoList = mongoTemplate.find(query, MongoUser.class);
        System.out.println("userInfoList:"+userInfoList);

        MongoDepart mongoDepart = new MongoDepart("1", "000", "软件部门1");
        mongoDepart.setId("bbb");
        mongoTemplate.save(mongoDepart);
        MongoRole mongoRole1 = new MongoRole("admin", "管理员");
        mongoTemplate.save(mongoRole1);
        MongoRole mongoRole2 = new MongoRole("orderadmin", "订单管理员");
        mongoTemplate.insert(mongoRole2);
        List<MongoRole> mongoRoleList = new ArrayList<>();
        mongoRoleList.add(mongoRole1);
        mongoRoleList.add(mongoRole2);
        MongoUser mongoUser = new MongoUser(this.getClass().toString(), "小飞", "123456", new Date().toString(), "123", mongoDepart, mongoRoleList);
        mongoTemplate.insert(mongoUser);
    }

    @Test
    public void testUpdate() {

        MongoUser mongoUser = new MongoUser();
        mongoUser.setName("小牛");
        WriteResult writeResult = mongoTemplate.updateFirst(new Query(new Criteria().orOperator(Criteria.where("id").is("aaa"), Criteria.where("code").is("113"))), Update.update("code", "114"), MongoDepart.class);
        System.out.println(writeResult);

        WriteResult writeResult1 = mongoTemplate.updateMulti(new Query(new Criteria().orOperator(Criteria.where("id").is("aaa"), Criteria.where("code").is("113"))), Update.update("code", "114"), MongoDepart.class);
        System.out.println(writeResult1);

    }

    @Test
    public void testQuery() {

        List<MongoDepart> mongoDepartList = mongoTemplate.find(new Query(Criteria.where("code").is("113")), MongoDepart.class);
        System.out.println(mongoDepartList);

        List<MongoDepart> mongoDepartList1 = mongoTemplate.findAll(MongoDepart.class);
        System.out.println(mongoDepartList1);
        System.out.println(mongoDepartList1);

        MongoDepart mongoDepart = mongoTemplate.findById("5b205a5ddab2286ce2aa618c", MongoDepart.class);
        System.out.println(mongoDepart);
        MongoDepart mongoDepart1 = mongoTemplate.findOne(new Query(Criteria.where("code").is("114")), MongoDepart.class);
        System.out.println(mongoDepart1);
    }

    @Test
    public void testremove() {

        MongoDepart mongoDepart = new MongoDepart();
        mongoDepart.setId("aaa");
        mongoDepart.setCode("114");
        WriteResult writeResult = mongoTemplate.remove(mongoDepart);
        System.out.println(writeResult);

    }

}
