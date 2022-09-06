package org.zerock.scheduler.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.scheduler.repository.UserRepository;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    UserRepository dao;

    @Override
    public boolean member_social_email(UserDTO vo) {
        return dao.member_social_email(vo) == 0 ? true : false;
    }

    @Override
    public void member_social_insert(UserDTO vo) {
        dao.member_social_insert(vo);
    }

    @Override
    public void member_social_update(UserDTO vo) {
        dao.member_social_update(vo);
    }
}
