package com.venturedive.rotikhilao.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTokenResponseDto implements Serializable {

    private static final long serialVersionUID = -1007099974760213488L;

    private boolean isAuthorized;

    private String jwtToken;

    private Long userId;

    private String name;
}
