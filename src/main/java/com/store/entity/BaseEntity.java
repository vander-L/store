package com.store.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    private String createdUser;
    private String modifiedUser;
    private Date createdTime;
    private Date modifiedTime;
}
