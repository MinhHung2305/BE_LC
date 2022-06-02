package fis.com.vn.rest.response;

import lombok.*;

import java.io.File;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInfoForSignDigital {
    File filePathFrontIdentity;
    File filePahBackIdentity;
    String fullName;
    String identityNumber;
    String address;
    String city;
    String country;
    String signPosition;
    String phoneNumber;
    File contractFile;
}
