package com.handy.support.entity;

import java.util.Date;

public class User {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.user_id
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.nick_name
     *
     * @mbggenerated
     */
    private String nickName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.login_password
     *
     * @mbggenerated
     */
    private String loginPassword;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.email
     *
     * @mbggenerated
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.sex
     *
     * @mbggenerated
     */
    private Byte sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.user_pic
     *
     * @mbggenerated
     */
    private String userPic;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.birthday
     *
     * @mbggenerated
     */
    private Date birthday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.level_id
     *
     * @mbggenerated
     */
    private Byte levelId;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    public User(Integer userId, String nickName, String loginPassword, String email, Byte sex, String userPic, Date birthday, Byte levelId) {
        this.userId = userId;
        this.nickName = nickName;
        this.loginPassword = loginPassword;
        this.email = email;
        this.sex = sex;
        this.userPic = userPic;
        this.birthday = birthday;
        this.levelId = levelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbggenerated
     */
    public User() {
        super();
    }
    public User(String email,String loginPassword){
        this.email = email;
        this.loginPassword = loginPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.user_id
     *
     * @return the value of user.user_id
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.user_id
     *
     * @param userId the value for user.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.nick_name
     *
     * @return the value of user.nick_name
     *
     * @mbggenerated
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.nick_name
     *
     * @param nickName the value for user.nick_name
     *
     * @mbggenerated
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.login_password
     *
     * @return the value of user.login_password
     *
     * @mbggenerated
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.login_password
     *
     * @param loginPassword the value for user.login_password
     *
     * @mbggenerated
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.email
     *
     * @return the value of user.email
     *
     * @mbggenerated
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.email
     *
     * @param email the value for user.email
     *
     * @mbggenerated
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.sex
     *
     * @return the value of user.sex
     *
     * @mbggenerated
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.sex
     *
     * @param sex the value for user.sex
     *
     * @mbggenerated
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.user_pic
     *
     * @return the value of user.user_pic
     *
     * @mbggenerated
     */
    public String getUserPic() {
        return userPic;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.user_pic
     *
     * @param userPic the value for user.user_pic
     *
     * @mbggenerated
     */
    public void setUserPic(String userPic) {
        this.userPic = userPic == null ? null : userPic.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.birthday
     *
     * @return the value of user.birthday
     *
     * @mbggenerated
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.birthday
     *
     * @param birthday the value for user.birthday
     *
     * @mbggenerated
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.level_id
     *
     * @return the value of user.level_id
     *
     * @mbggenerated
     */
    public Byte getLevelId() {
        return levelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.level_id
     *
     * @param levelId the value for user.level_id
     *
     * @mbggenerated
     */
    public void setLevelId(Byte levelId) {
        this.levelId = levelId;
    }
}