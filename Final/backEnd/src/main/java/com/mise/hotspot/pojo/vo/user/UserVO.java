package com.mise.hotspot.pojo.vo.user;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Builder
@Data
public class UserVO {

    private Long id;
    private String username;
    private String password;
    private String phone;
    private Long balance = 10000L;

}
