package org.zerock.scheduler.entity;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Schduler extends BaseEntity{
    
    //데이터 테이블에 들어갈 데이터 칼럼들을 생성하는 곳
    @Id
    private String id;

    @Column(length = 100)
    private String pw;

}
