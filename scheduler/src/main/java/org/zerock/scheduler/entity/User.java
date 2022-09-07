package org.zerock.scheduler.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class User extends BaseEntity{

    @Id
    private String id;

    @Column(length = 100)
    private String email;

    @Column(nullable = false, length = 30, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(length = 100)
    private String gender;

    @Column(length = 50)
    private String social_type;

}
