package fis.com.vn.rest.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CorporateUserRequest {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    private Long id;
    private String userCode;
    private String userName;
    private String phoneNumber;
    private String email;
    private Integer userStatus;
    private String userGroupCode;
    private String role;
    private String channelInit;
    private String position;
    private String identityType;
    private String identityNumber;
    private String dateOfIdentity;
    private String issuedByIdentity;
    private String imageFrontOfIdentity;
    private String imageBackOfIdentity;
    private String imagePortraitOfIdentity;

    @JsonFormat(pattern = DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDate dateOfBirth;
}
