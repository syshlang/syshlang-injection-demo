package com.syshlang.injection.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="sys_user")
@Data
public class User implements Serializable {
    @Id
    private Long id;
    private String account;
    private String password;
    private String lastLoginIp;
    private Date lastLoginTime;
    private Long loginCount;
    private Date createTime;
    private Integer isEnable;
}
