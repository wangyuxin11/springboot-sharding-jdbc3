package org.springboot.sharding.jdbc3.multiple.jpa.entity.datasource1;

import javax.persistence.*;

/**
 * 
 * @author wangyx
 *
 */
@Entity
@Table(name="house")
public class House {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int houseId;
    private String houseName;
    private String houseIntroduce;

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseIntroduce() {
        return houseIntroduce;
    }

    public void setHouseIntroduce(String houseIntroduce) {
        this.houseIntroduce = houseIntroduce;
    }

    public House(String houseName, String houseIntroduce) {
        this.houseName = houseName;
        this.houseIntroduce = houseIntroduce;
    }
}