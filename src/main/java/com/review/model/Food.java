/**
 *
 */
package com.review.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author ddung
 *
 */
@Entity
@Table(name = "food")
@Document(indexName = "food", type = "Food")
public class Food {
    @Id
    @org.springframework.data.annotation.Id
    private Long foodId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "foodTypeId", nullable = false)
    private Long foodTypeId;
    @Column(name = "foodTypeName", nullable = false)
    private String foodTypeName;
    @Column(name = "stateId", nullable = false)
    private Long stateId;
    @Column(name = "stateName", nullable = false)
    private String stateName;
    @Column(name = "cityId", nullable = false)
    private Long cityId;
    @Column(name = "cityName", nullable = false)
    private String cityName;
    @Column(name = "districtId", nullable = false)
    private Long districtId;
    @Column(name = "districtName", nullable = false)
    private String districtName;
    @Column(name = "villageId", nullable = false)
    private Long villageId;
    @Column(name = "villageName", nullable = false)
    private String villageName;
    @Column(name = "linkGoogleMap", columnDefinition = "LONGTEXT", nullable = false)
    private String linkGoogleMap;
    @Column(name = "location", columnDefinition = "LONGTEXT", nullable = false)
    private String location;
    @Column(name = "description", columnDefinition = "LONGTEXT", nullable = true)
    private String description;
    @Column(name = "isActive", nullable = false)
    private Integer isActive;
    @Column(name = "ownerFoodId", nullable = false)
    private Long ownerFoodId;
    @Column(name = "createDate", nullable = false)
    private Date createDate;
    @Column(name = "modifiedDate", nullable = false)
    private Date modifiedDate;
    @Column(name = "userId", nullable = false)
    private Long userId;
    @Column(name = "commentPoint", nullable = false, columnDefinition = "int default 0")
    private int commentPoint;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getFoodId() {
        return foodId;
    }

    public void setFoodId(Long foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(Long foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public String getFoodTypeName() {
        return foodTypeName;
    }

    public void setFoodTypeName(String foodTypeName) {
        this.foodTypeName = foodTypeName;
    }

    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Long getVillageId() {
        return villageId;
    }

    public void setVillageId(Long villageId) {
        this.villageId = villageId;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getLinkGoogleMap() {
        return linkGoogleMap;
    }

    public void setLinkGoogleMap(String linkGoogleMap) {
        this.linkGoogleMap = linkGoogleMap;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Long getOwnerFoodId() {
        return ownerFoodId;
    }

    public void setOwnerFoodId(Long ownerFoodId) {
        this.ownerFoodId = ownerFoodId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getCommentPoint() {
        return commentPoint;
    }

    public void setCommentPoint(int commentPoint) {
        this.commentPoint = commentPoint;
    }
}
