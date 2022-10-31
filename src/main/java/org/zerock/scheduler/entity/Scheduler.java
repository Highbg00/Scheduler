package org.zerock.scheduler.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Scheduler extends BaseEntity{
    
    //데이터 테이블에 들어갈 데이터 칼럼들을 생성하는 곳
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 11)
    private int schedule_idx;

    @Column(length = 11)
    private int schedule_num;

    @Column(length = 45)
    private String schedule_subject;

    @Column(length = 45)
    private String schedule_desc;

    @Column
    private Date schedule_date;

    @Column(length = 10)
    private String schedule_mycolor;

    @Column(length = 100)
    private String id;
}
