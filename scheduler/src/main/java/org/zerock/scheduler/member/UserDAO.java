package org.zerock.scheduler.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.zerock.scheduler.member.MemberService;
import org.zerock.scheduler.member.UserDTO;

public class UserDAO implements MemberService {

    @Autowired private SqlSession sql;

    @Override
    public boolean member_social_email(UserDTO vo) {
        return (Integer) sql.selectOne("member.mapper.social_email", vo) == 0 ? false : true ;
    }

    @Override
    public boolean member_social_insert(UserDTO vo) {
        return sql.insert("member.mapper.social_insert", vo) == 0 ? false : true;
    }

    @Override
    public boolean member_social_update(UserDTO vo) {
        return sql.update("member.mapper.social_update", vo) == 0 ? false : true;
    }
}
