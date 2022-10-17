package org.zerock.scheduler.member;


import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    // 소셜 회원 정부 존재 여부 (R)
    boolean social_email(UserDTO vo);

    // 소셜 회원 정보 신규 저장 (C)
    void social_insert(UserDTO vo);

    // 소셜 회원 정보 변경 저장 (U)
    void social_update(UserDTO  vo);

}
