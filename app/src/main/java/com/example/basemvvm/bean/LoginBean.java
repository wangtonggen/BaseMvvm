package com.example.basemvvm.bean;

import java.util.List;

/**
 * author: wtg  2019/4/24 0024
 * desc: 用户登录的bean
 */
public class LoginBean {
    private String access_token;//sos token
    private String birthday;//生日
    private Integer cakeScore;//巧克力数量
    private Integer carGroupNum;//车圈数
    private String carName;//车辆名称
    private Integer carOwner;//是否有车：0—暂时无车;1—有车一族; ,
    private Integer carType;// 车辆类型
    private String cityName;//所属城市
    private Integer dataIntegeregrity;//资料完整度
    private double distance;//距离(单位m)
    private String education;//学历
    private String experience;//经验值
    private Integer gender;//性别
    private String headPhoto;//头像
    private String headVerify;//是否真人头像认证：0—未认证;1—已认证
    private String id;//id
    private Integer isBindMobile;//是否绑定手机:0-否;1-是; 用于第三方登录跳转绑定手机页面
    private Integer isRegister;//是否首次注册:0-不是;1-是; 用于判断登录后跳转首页还是完善信息页
    private List<String> likeLabel;//你喜欢的标签
    private String livePlace;//居住地
    private Integer lotteryNumber;//抽奖次数
    private Integer membership;//会员资格:0-非会员;1-会员
    private String mobile;// 手机号
    private String monthIncome;//月收入
    private String nickname;//昵称
    private String profession;//职业
    private String qqOpenId;//QQ openid
    private String realnameVerify;//是否实名认证：0—未认证;1—已认证
    private String starSeat;//星座
    private String token;//身份认证
    private String userOnline;//用户是否在线：0—不在线;1—在线
    private Integer userStatus;//是否实名认证：0—未认证;1—已认证
    private String username;//用户名
    private String wxOpenId;//微信openid

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getCakeScore() {
        return cakeScore;
    }

    public void setCakeScore(Integer cakeScore) {
        this.cakeScore = cakeScore;
    }

    public Integer getCarGroupNum() {
        return carGroupNum;
    }

    public void setCarGroupNum(Integer carGroupNum) {
        this.carGroupNum = carGroupNum;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Integer getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(Integer carOwner) {
        this.carOwner = carOwner;
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getDataIntegeregrity() {
        return dataIntegeregrity;
    }

    public void setDataIntegeregrity(Integer dataIntegeregrity) {
        this.dataIntegeregrity = dataIntegeregrity;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getHeadVerify() {
        return headVerify;
    }

    public void setHeadVerify(String headVerify) {
        this.headVerify = headVerify;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIsBindMobile() {
        return isBindMobile;
    }

    public void setIsBindMobile(Integer isBindMobile) {
        this.isBindMobile = isBindMobile;
    }

    public Integer getIsRegister() {
        return isRegister;
    }

    public void setIsRegister(Integer isRegister) {
        this.isRegister = isRegister;
    }

    public List<String> getLikeLabel() {
        return likeLabel;
    }

    public void setLikeLabel(List<String> likeLabel) {
        this.likeLabel = likeLabel;
    }

    public String getLivePlace() {
        return livePlace;
    }

    public void setLivePlace(String livePlace) {
        this.livePlace = livePlace;
    }

    public Integer getLotteryNumber() {
        return lotteryNumber;
    }

    public void setLotteryNumber(Integer lotteryNumber) {
        this.lotteryNumber = lotteryNumber;
    }

    public Integer getMembership() {
        return membership;
    }

    public void setMembership(Integer membership) {
        this.membership = membership;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(String monthIncome) {
        this.monthIncome = monthIncome;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }

    public String getRealnameVerify() {
        return realnameVerify;
    }

    public void setRealnameVerify(String realnameVerify) {
        this.realnameVerify = realnameVerify;
    }

    public String getStarSeat() {
        return starSeat;
    }

    public void setStarSeat(String starSeat) {
        this.starSeat = starSeat;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserOnline() {
        return userOnline;
    }

    public void setUserOnline(String userOnline) {
        this.userOnline = userOnline;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }
}
