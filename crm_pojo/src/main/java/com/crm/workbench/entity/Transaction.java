package com.crm.workbench.entity;

import org.apache.ibatis.type.Alias;

@Alias("transaction")
public class Transaction {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_transaction.id
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_transaction.owner
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    private String owner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_transaction.money
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    private String money;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_transaction.name
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_transaction.expected_date
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    private String expectedDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_transaction.customer_id
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    private String customerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_transaction.stage
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    private String stage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_transaction.type
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_transaction.source
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    private String source;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_transaction.activity_id
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    private String activityId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_transaction.contacts_id
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    private String contactsId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_transaction.create_by
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_transaction.create_time
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    private String createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_transaction.edit_by
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    private String editBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_transaction.edit_time
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    private String editTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_transaction.description
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_transaction.contact_summary
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    private String contactSummary;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_transaction.next_contact_time
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    private String nextContactTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_transaction.id
     *
     * @return the value of tbl_transaction.id
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_transaction.id
     *
     * @param id the value for tbl_transaction.id
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_transaction.owner
     *
     * @return the value of tbl_transaction.owner
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public String getOwner() {
        return owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_transaction.owner
     *
     * @param owner the value for tbl_transaction.owner
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_transaction.money
     *
     * @return the value of tbl_transaction.money
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public String getMoney() {
        return money;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_transaction.money
     *
     * @param money the value for tbl_transaction.money
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public void setMoney(String money) {
        this.money = money == null ? null : money.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_transaction.name
     *
     * @return the value of tbl_transaction.name
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_transaction.name
     *
     * @param name the value for tbl_transaction.name
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_transaction.expected_date
     *
     * @return the value of tbl_transaction.expected_date
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public String getExpectedDate() {
        return expectedDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_transaction.expected_date
     *
     * @param expectedDate the value for tbl_transaction.expected_date
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate == null ? null : expectedDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_transaction.customer_id
     *
     * @return the value of tbl_transaction.customer_id
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_transaction.customer_id
     *
     * @param customerId the value for tbl_transaction.customer_id
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_transaction.stage
     *
     * @return the value of tbl_transaction.stage
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public String getStage() {
        return stage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_transaction.stage
     *
     * @param stage the value for tbl_transaction.stage
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public void setStage(String stage) {
        this.stage = stage == null ? null : stage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_transaction.type
     *
     * @return the value of tbl_transaction.type
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_transaction.type
     *
     * @param type the value for tbl_transaction.type
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_transaction.source
     *
     * @return the value of tbl_transaction.source
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public String getSource() {
        return source;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_transaction.source
     *
     * @param source the value for tbl_transaction.source
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_transaction.activity_id
     *
     * @return the value of tbl_transaction.activity_id
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_transaction.activity_id
     *
     * @param activityId the value for tbl_transaction.activity_id
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public void setActivityId(String activityId) {
        this.activityId = activityId == null ? null : activityId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_transaction.contacts_id
     *
     * @return the value of tbl_transaction.contacts_id
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public String getContactsId() {
        return contactsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_transaction.contacts_id
     *
     * @param contactsId the value for tbl_transaction.contacts_id
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public void setContactsId(String contactsId) {
        this.contactsId = contactsId == null ? null : contactsId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_transaction.create_by
     *
     * @return the value of tbl_transaction.create_by
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_transaction.create_by
     *
     * @param createBy the value for tbl_transaction.create_by
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_transaction.create_time
     *
     * @return the value of tbl_transaction.create_time
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_transaction.create_time
     *
     * @param createTime the value for tbl_transaction.create_time
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_transaction.edit_by
     *
     * @return the value of tbl_transaction.edit_by
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public String getEditBy() {
        return editBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_transaction.edit_by
     *
     * @param editBy the value for tbl_transaction.edit_by
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public void setEditBy(String editBy) {
        this.editBy = editBy == null ? null : editBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_transaction.edit_time
     *
     * @return the value of tbl_transaction.edit_time
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public String getEditTime() {
        return editTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_transaction.edit_time
     *
     * @param editTime the value for tbl_transaction.edit_time
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public void setEditTime(String editTime) {
        this.editTime = editTime == null ? null : editTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_transaction.description
     *
     * @return the value of tbl_transaction.description
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_transaction.description
     *
     * @param description the value for tbl_transaction.description
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_transaction.contact_summary
     *
     * @return the value of tbl_transaction.contact_summary
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public String getContactSummary() {
        return contactSummary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_transaction.contact_summary
     *
     * @param contactSummary the value for tbl_transaction.contact_summary
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public void setContactSummary(String contactSummary) {
        this.contactSummary = contactSummary == null ? null : contactSummary.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_transaction.next_contact_time
     *
     * @return the value of tbl_transaction.next_contact_time
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public String getNextContactTime() {
        return nextContactTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_transaction.next_contact_time
     *
     * @param nextContactTime the value for tbl_transaction.next_contact_time
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    public void setNextContactTime(String nextContactTime) {
        this.nextContactTime = nextContactTime == null ? null : nextContactTime.trim();
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", money='" + money + '\'' +
                ", name='" + name + '\'' +
                ", expectedDate='" + expectedDate + '\'' +
                ", customerId='" + customerId + '\'' +
                ", stage='" + stage + '\'' +
                ", type='" + type + '\'' +
                ", source='" + source + '\'' +
                ", activityId='" + activityId + '\'' +
                ", contactsId='" + contactsId + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", editBy='" + editBy + '\'' +
                ", editTime='" + editTime + '\'' +
                ", description='" + description + '\'' +
                ", contactSummary='" + contactSummary + '\'' +
                ", nextContactTime='" + nextContactTime + '\'' +
                '}';
    }
}