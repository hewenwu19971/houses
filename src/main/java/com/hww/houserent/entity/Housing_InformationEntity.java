package com.hww.houserent.entity;

/**
 * 位置
 * 小区
 * 楼栋号
 * 楼层
 * 房间号
 * 房源面积
 * 计租面积
 * 户型
 * 建筑结构
 * 建筑结构
 * 状态
 */
public class Housing_InformationEntity {
   private int id;
    private String position;//位置
    private String community;//小区
    private String building_number;//楼栋号
    private String floor;//楼层
    private String room_number;//房间号
    private String housing_area;//房源面积
    private String rental_area;//计租面积
    private String apartment;//户型
    private String building_structure;//建筑结构

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getBuilding_number() {
        return building_number;
    }

    public void setBuilding_number(String building_number) {
        this.building_number = building_number;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoom_number() {
        return room_number;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public String getHousing_area() {
        return housing_area;
    }

    public void setHousing_area(String housing_area) {
        this.housing_area = housing_area;
    }

    public String getRental_area() {
        return rental_area;
    }

    public void setRental_area(String rental_area) {
        this.rental_area = rental_area;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getBuilding_structure() {
        return building_structure;
    }

    public void setBuilding_structure(String building_structure) {
        this.building_structure = building_structure;
    }

    public String getNature_of_lease() {
        return nature_of_lease;
    }

    public void setNature_of_lease(String nature_of_lease) {
        this.nature_of_lease = nature_of_lease;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String nature_of_lease;
    private String state;

}
