package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity {

//    @CreatedBy//創建者
//    @Column(name = "CREATER", updatable = false)
//    protected String createdBy;

    @CreatedDate//創建日期
    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonIgnore
    protected Date createdAt;

//    @LastModifiedBy//修改者
//    @Column(name = "UPDATER")
//    protected String updatedBy;

    @LastModifiedDate//修改日期
    @Column(name = "modified_at", nullable = false)
    @JsonIgnore
    protected Date modifiedAt;
}
