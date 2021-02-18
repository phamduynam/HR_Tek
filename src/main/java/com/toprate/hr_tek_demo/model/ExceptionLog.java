package com.toprate.hr_tek_demo.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by IntelliJ IDEA.
 * User: namnv
 * Date: 17:32 17/02/2021
 */
@Entity
@Table(name = "exception_log", schema = "hr_tek")
public class ExceptionLog {
    private int id;
    private String excMessage;
    private String excName;
    private String excRequestParam;
    private Date createTime;
    private String ip;
    private String method;
    private String uri;
    private String userId;
    private String userName;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "exc_message", columnDefinition="TEXT")
    public String getExcMessage() {
        return excMessage;
    }

    public void setExcMessage(String excMessage) {
        this.excMessage = excMessage;
    }

    @Basic
    @Column(name = "exc_name")
    public String getExcName() {
        return excName;
    }

    public void setExcName(String excName) {
        this.excName = excName;
    }

    @Basic
    @Column(name = "exc_request_param")
    public String getExcRequestParam() {
        return excRequestParam;
    }

    public void setExcRequestParam(String excRequestParam) {
        this.excRequestParam = excRequestParam;
    }

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic
    @Column(name = "method")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Basic
    @Column(name = "uri")
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Basic
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
