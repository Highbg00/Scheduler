package org.zerock.scheduler.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired UserDAO dao;

    @Override
    public boolean member_social_email(UserDTO vo) {
        return dao.member_social_email(vo);
    }

    @Override
    public boolean member_social_insert(UserDTO vo) {
        return dao.member_social_insert(vo);
    }

    @Override
    public boolean member_social_update(UserDTO vo) {
        return dao.member_social_update(vo);
    }
}
