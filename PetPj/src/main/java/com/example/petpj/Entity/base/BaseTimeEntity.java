package com.example.petpj.Entity.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {

    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date createDate;

    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private Date updateDate;


}
