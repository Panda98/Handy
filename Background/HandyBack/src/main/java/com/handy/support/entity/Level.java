package com.handy.support.entity;

public class Level {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column level.level_id
     *
     * @mbggenerated
     */
    private Byte levelId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column level.level_name
     *
     * @mbggenerated
     */
    private String levelName;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table level
     *
     * @mbggenerated
     */
    public Level(Byte levelId, String levelName) {
        this.levelId = levelId;
        this.levelName = levelName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table level
     *
     * @mbggenerated
     */
    public Level() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column level.level_id
     *
     * @return the value of level.level_id
     *
     * @mbggenerated
     */
    public Byte getLevelId() {
        return levelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column level.level_id
     *
     * @param levelId the value for level.level_id
     *
     * @mbggenerated
     */
    public void setLevelId(Byte levelId) {
        this.levelId = levelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column level.level_name
     *
     * @return the value of level.level_name
     *
     * @mbggenerated
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column level.level_name
     *
     * @param levelName the value for level.level_name
     *
     * @mbggenerated
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }
}