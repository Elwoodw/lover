package org.lover.mapper;

import cn.hutool.core.util.IdUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lover.domain.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    public void countByExample() {
    }

    @Test
    @Transactional
    public void deleteByExample() {
    }

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {


    }

    @Test
    @Transactional
    public void insertSelective() {
        User user=new User();
        user.setUid(IdUtil.getSnowflake().nextId());
        user.setName("Mike");
        user.setSex(true);
        user.setHeight(180);
        user.setWeight(180);
        user.setProvince("江苏");
        user.setBirth(new Date(1999-1900,5-1,10));
        user.setCity("盐城");
        user.setDistrict("滨海");
        user.setEducation("本科");
        user.setPhone("13815519527");
        user.setEmail("yeahtql@qq.com");
        user.setWechat("hello_abc");
        user.setJob("java");
        user.setHobbies("健身");
        user.setSign("我就是我，不一样的烟火");
        user.setPhoto("927f0861741bd2135df3cbac979cdded.jpeg");
        user.setUpdateTime(new Date());
        int count=mapper.insertSelective(user);
        assertEquals(1,count);
    }

    @Test
    public void selectByExample() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByExampleSelective() {
    }

    @Test
    public void updateByExample() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}