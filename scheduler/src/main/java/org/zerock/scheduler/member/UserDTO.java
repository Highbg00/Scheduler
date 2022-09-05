package org.zerock.scheduler.member;

import lombok.Data;

@Data
public class UserDTO {
    private String id, username, nickname, pw, gender, email;
}
