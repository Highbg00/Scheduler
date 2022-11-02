package org.zerock.scheduler.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.scheduler.repository.UserRepository;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    UserRepository dao;

    @Override
    public boolean social_email(UserDTO vo) {
        return dao.social_email(vo) == 0 ? false : true;
    }

    @Override
    public void social_insert(UserDTO vo) {
        dao.social_insert(vo);
    }

    @Override
    public void social_update(UserDTO vo) {
        dao.social_update(vo);
    }
}
