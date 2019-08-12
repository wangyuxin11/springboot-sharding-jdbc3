package org.springboot.sharding.jdbc3.multiple.jpa.controller;

import org.springboot.sharding.jdbc3.multiple.jpa.entity.datasource0.City;
import org.springboot.sharding.jdbc3.multiple.jpa.entity.datasource1.House;
import org.springboot.sharding.jdbc3.multiple.jpa.repository.datasource0.CityRepository;
import org.springboot.sharding.jdbc3.multiple.jpa.repository.datasource1.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author wangyx
 *
 */
@RestController
public class TestController {


    @Autowired
    CityRepository cityRepository;

    @Autowired
    HouseRepository houseRepository;

    //http://localhost:8888/testDataSource0
    @GetMapping("/testDataSource")
    public String testDataSource(){
        City city = new City("北京","中国首都");
        cityRepository.save(city);
        return "success";
    }

    //http://localhost:8888/testDataSource1
    @GetMapping("/testDataSource1")
    public String testDataSource1(){
        House house = new House("豪宅","特别大的豪宅");
        houseRepository.save(house);
        return "success";
    }
    
}
