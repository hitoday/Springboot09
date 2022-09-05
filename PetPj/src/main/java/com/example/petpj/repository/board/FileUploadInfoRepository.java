package com.example.petpj.repository.board;

import com.example.petpj.Entity.data.FileUploadEntity;
import org.springframework.data.jpa.repository.JpaRepository;


//0901
//FileUploadEntity를 저장하는 인터페이스 (JPA CrudRepository 활용)
public interface FileUploadInfoRepository extends JpaRepository<FileUploadEntity, Long> {

    //findBy : 튜플 검색
    //BoardSeq : BoardSeq 데이터를 찾겠다
    FileUploadEntity findByBoardNum(Long boardNum);


}
