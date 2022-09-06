package org.zerock.scheduler.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.scheduler.entity.User;
import org.zerock.scheduler.member.UserDTO;

import java.util.Optional;

@Mapper
public interface UserRepository {

    int member_social_email(UserDTO vo);

    void member_social_insert(UserDTO vo);

    void member_social_update(UserDTO vo);
}
