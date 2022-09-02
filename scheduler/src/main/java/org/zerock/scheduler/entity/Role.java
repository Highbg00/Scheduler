package org.zerock.scheduler.entity;

import lombok.*;


@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    SOCIAL("ROLE_SOCIAL"); // OAuth
    private final String value;
}
