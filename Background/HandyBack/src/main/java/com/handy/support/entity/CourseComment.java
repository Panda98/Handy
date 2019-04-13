package com.handy.support.entity;

public class CourseComment {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_comment.course_id
     *
     * @mbggenerated
     */
    private Integer courseId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course_comment.comment_id
     *
     * @mbggenerated
     */
    private Integer commentId;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_comment
     *
     * @mbggenerated
     */
    public CourseComment(Integer courseId, Integer commentId) {
        this.courseId = courseId;
        this.commentId = commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table course_comment
     *
     * @mbggenerated
     */
    public CourseComment() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_comment.course_id
     *
     * @return the value of course_comment.course_id
     *
     * @mbggenerated
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_comment.course_id
     *
     * @param courseId the value for course_comment.course_id
     *
     * @mbggenerated
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course_comment.comment_id
     *
     * @return the value of course_comment.comment_id
     *
     * @mbggenerated
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course_comment.comment_id
     *
     * @param commentId the value for course_comment.comment_id
     *
     * @mbggenerated
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }
}