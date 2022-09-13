package org.zerock.scheduler.repository;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.scheduler.member.UserDTO;

@Mapper
public interface UserRepository {

    int social_email(UserDTO vo);

    void social_insert(UserDTO vo);

    void social_update(UserDTO vo);
}
