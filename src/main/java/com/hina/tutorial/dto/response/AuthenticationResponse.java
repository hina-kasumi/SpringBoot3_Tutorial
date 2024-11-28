package com.hina.tutorial.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)// gán giá trị các field trong class là private
public class AuthenticationResponse {
    String token;
    boolean authenticated;
}
