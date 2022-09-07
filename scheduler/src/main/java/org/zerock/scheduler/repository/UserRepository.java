package org.zerock.scheduler.repository;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.scheduler.member.UserDTO;

@Mapper
public interface UserRepository {

    int member_social_email(UserDTO vo);

    void member_social_insert(UserDTO vo);

    void member_social_update(UserDTO vo);
}
