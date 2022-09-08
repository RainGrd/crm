package com.crm.workbench.entity;

import org.apache.ibatis.type.Alias;

@Alias("activityRemark")
public class ActivityRemark {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_activity_remark.id
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_activity_remark.note_content
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    private String noteContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_activity_remark.create_time
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_activity_remark.create_by
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_activity_remark.edit_time
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    private String editTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_activity_remark.edit_by
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    private String editBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_activity_remark.edit_flag
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    private String editFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_activity_remark.activity_id
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    private String activityId;
    /**
     * 市场活动备注状态
     */
    private Integer activityRemarkStatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_activity_remark.id
     *
     * @return the value of tbl_activity_remark.id
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_activity_remark.id
     *
     * @param id the value for tbl_activity_remark.id
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_activity_remark.note_content
     *
     * @return the value of tbl_activity_remark.note_content
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    public String getNoteContent() {
        return noteContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_activity_remark.note_content
     *
     * @param noteContent the value for tbl_activity_remark.note_content
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent == null ? null : noteContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_activity_remark.create_time
     *
     * @return the value of tbl_activity_remark.create_time
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_activity_remark.create_time
     *
     * @param createTime the value for tbl_activity_remark.create_time
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_activity_remark.create_by
     *
     * @return the value of tbl_activity_remark.create_by
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_activity_remark.create_by
     *
     * @param createBy the value for tbl_activity_remark.create_by
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_activity_remark.edit_time
     *
     * @return the value of tbl_activity_remark.edit_time
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    public String getEditTime() {
        return editTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_activity_remark.edit_time
     *
     * @param editTime the value for tbl_activity_remark.edit_time
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    public void setEditTime(String editTime) {
        this.editTime = editTime == null ? null : editTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_activity_remark.edit_by
     *
     * @return the value of tbl_activity_remark.edit_by
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    public String getEditBy() {
        return editBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_activity_remark.edit_by
     *
     * @param editBy the value for tbl_activity_remark.edit_by
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    public void setEditBy(String editBy) {
        this.editBy = editBy == null ? null : editBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_activity_remark.edit_flag
     *
     * @return the value of tbl_activity_remark.edit_flag
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    public String getEditFlag() {
        return editFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_activity_remark.edit_flag
     *
     * @param editFlag the value for tbl_activity_remark.edit_flag
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    public void setEditFlag(String editFlag) {
        this.editFlag = editFlag == null ? null : editFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_activity_remark.activity_id
     *
     * @return the value of tbl_activity_remark.activity_id
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_activity_remark.activity_id
     *
     * @param activityId the value for tbl_activity_remark.activity_id
     *
     * @mbggenerated Wed Sep 07 08:52:02 CST 2022
     */
    public void setActivityId(String activityId) {
        this.activityId = activityId == null ? null : activityId.trim();
    }

    public Integer getActivityRemarkStatus() {
        return activityRemarkStatus;
    }

    public void setActivityRemarkStatus(Integer activityRemarkStatus) {
        this.activityRemarkStatus = activityRemarkStatus;
    }

    @Override
    public String toString() {
        return "ActivityRemark{" +
                "id='" + id + '\'' +
                ", noteContent='" + noteContent + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createBy='" + createBy + '\'' +
                ", editTime='" + editTime + '\'' +
                ", editBy='" + editBy + '\'' +
                ", editFlag='" + editFlag + '\'' +
                ", activityId='" + activityId + '\'' +
                ", activityStatus=" + activityRemarkStatus +
                '}';
    }
}