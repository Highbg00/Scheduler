package org.zerock.scheduler.member;

import lombok.Data;

@Data
public class UserDTO {
    private String id, email, username, nickname, gender, social_type;
}
