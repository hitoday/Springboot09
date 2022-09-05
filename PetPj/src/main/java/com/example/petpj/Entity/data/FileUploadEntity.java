package com.example.petpj.Entity.data;

import com.example.petpj.Entity.base.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//0831
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FileUploadEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String uuid;
    private String contentType;
    private String name;
    private String originalFilename;
    @Column
    private Long boardNum;
}
