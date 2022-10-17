package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
//@RunWith：表示启动这个单元测试类（单元测试类是不能够运行的），需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class AddressMapperTests {

    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insertAddress(){
        Address address = new Address();
        address.setUid(6);
        address.setPhone("1234567890");
        address.setAddress("郑州");
        address.setName("sgh");
        Integer integer = addressMapper.insertAddress(address);
    }

    @Test
    public void countAddressByUid(){
        Integer integer = addressMapper.countByUid(6);
        System.out.println(integer);
    }

    @Test
    public void getString(){
        List<String> list1 = new ArrayList<>();
        list1.add("12,34,45");
        list1.add("111.175699,222,333");
        list1.add("666,777,998");
        List<String> list2 = new ArrayList<>();
        list1.add("12.11223,34.44577,45");
        list1.add("111.175699,222,333");
        list1.add("980,383.888,998");
        List<String> list3 = new ArrayList<>();
        list1.add("88.998675,78.654677,45");
        list1.add("123.175369,173.33745,333");
        list1.add("549.7878,494.489373,998");

        List<String> list = Stream.of(list1,list2,list3).flatMap(Collection::stream).collect(Collectors.toList());


        List<Point2D.Double> points = new ArrayList<>();
        for (String s : list) {
            String[] strings = s.split(",");
            Point2D.Double point = new Point2D.Double(Double.parseDouble(strings[0]),Double.parseDouble(strings[1]));
            points.add(point);
        }


        for (Point2D.Double point : points) {
            System.out.println(point);
        }

        System.out.println(points.size());
    }

    @Test
    public void pointNum(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        int i = 1;

        for (Integer a : list) {
            i = a + i;
            System.out.println(i);
        }

        System.out.println(i);

    }



}
