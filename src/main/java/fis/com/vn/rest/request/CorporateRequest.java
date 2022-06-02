package fis.com.vn.rest.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CorporateRequest {

    private static final String DATE_FORMAT = "dd/MM/yyyy";

    private String corporateCode;

    @NotBlank(message = "{corporateName.invalid}")
    private String corporateName;

    @NotBlank(message = "{corporateNameEn.invalid}")
    private String corporateNameEn;

    @NotBlank(message = "{typeOfCorporate.invalid}")
    private String typeOfCorporate;

    @JsonFormat(pattern = DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDate dateRegistration;

    private String registrationTimes;

    @NotBlank(message = "{corporateAddress.invalid}")
    private String corporateAddress;

    @NotBlank(message = "{corporatePhoneNumber.invalid}")
    private String corporatePhoneNumber;

    private String corporateEmail;

    private String corporateFaxNumber;

    private String corporateWebsite;

    @NotBlank(message = "{charterCapital.invalid}")
    private String charterCapital;

    private String parValueShares;

    private String totalNumberOfShares;

    @NotBlank(message = "{legalRepresentativeFullName.invalid}")
    private String legalRepresentativeFullName;

    @NotBlank(message = "{legalRepresentativeIdentifyNumber.invalid}")
    private String legalRepresentativeIdentifyNumber;

    @JsonFormat(pattern = DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDate legalRepresentativeIdentifyCreatedDate;

    @NotBlank(message = "{legalRepresentativeIdentifyCreatedPlace.invalid}")
    private String legalRepresentativeIdentifyCreatedPlace;

    private String businessRegistrationCertificateUrl;

    private String user1DeputyName;

    @JsonFormat(pattern = DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDate user1DeputyBirthday;

    private String user1DeputyIdentifyNumber;

    private String user1DeputyIdentifyCreatedPlace;

    @JsonFormat(pattern = DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDate user1DeputyIdentifyCreatedDate;

    private String user1DeputyRoles;

    private String user1DeputyIdentifyPhone;

    private String user1DeputyIdentifyEmail;

    private String frontOfUser1DeputyIdentifyUrl;

    private String backOfUser1DeputyIdentifyUrl;

    private String portraitOfUser1DeputyUrl;

    private String user2DeputyName;

    @JsonFormat(pattern = DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDate user2DeputyBirthday;

    private String user2DeputyIdentifyNumber;

    private String user2DeputyIdentifyCreatedPlace;

    @JsonFormat(pattern = DATE_FORMAT, shape = JsonFormat.Shape.STRING)
    private LocalDate user2DeputyIdentifyCreatedDate;

    private String user2DeputyRoles;

    private String user2DeputyIdentifyPhone;

    private String user2DeputyIdentifyEmail;

    private String frontOfUser2DeputyIdentifyUrl;

    private String backOfUser2DeputyIdentifyUrl;

    private String portraitOfUser2DeputyUrl;

    private String channelInit;

    private Integer corporateStatus;

    private List<CorporateAccountRequest> corporateAccountList;

    private List<CorporateUserRequest> corporateUserList;

    private String packageServiceId;

    private String signatureSerial;

    private String signature;

    private String signatureSubscriberName;

    private String signatureTime;

    private String signatureCertificate;

    private String signatureExpirationDate;

    private String signatureValueDate;

    private String signatureValue;

    private String signatureReleaseBy;

    private String signatureTaxCode;

}
