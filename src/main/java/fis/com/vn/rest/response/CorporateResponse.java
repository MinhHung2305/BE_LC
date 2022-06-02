package fis.com.vn.rest.response;

import fis.com.vn.model.entity.CorporateAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CorporateResponse {

	private Long corporateId;
	private String corporateCode;
	private String corporateName;
	private String corporateNameEn;
	private String typeOfCorporate;
	private LocalDate dateRegistration;
	private String registrationTimes;
	private String corporateAddress;
	private String corporatePhoneNumber;
	private String corporateEmail;
	private String corporateFaxNumber;
	private String corporateWebsite;
	private String charterCapital;
	private String parValueShares;
	private String totalNumberOfShares;
	private String legalRepresentativeFullName;
	private String legalRepresentativeIdentifyNumber;
	private LocalDate legalRepresentativeIdentifyCreatedDate;
	private String legalRepresentativeIdentifyCreatedPlace;
	private String businessRegistrationCertificateUrl;
	private String businessRegistrationCertificateUrlMinio;
	private String user1DeputyName;
	private LocalDate user1DeputyBirthday;
	private String user1DeputyIdentifyNumber;
	private String user1DeputyIdentifyCreatedPlace;
	private LocalDate user1DeputyIdentifyCreatedDate;
	private String user1DeputyRoles;
	private String user1DeputyIdentifyPhone;
	private String user1DeputyIdentifyEmail;
	private String frontOfUser1DeputyIdentifyUrl;
	private String backOfUser1DeputyIdentifyUrl;
	private String portraitOfUser1DeputyUrl;
	private String user2DeputyName;
	private LocalDate user2DeputyBirthday;
	private String user2DeputyIdentifyNumber;
	private String user2DeputyIdentifyCreatedPlace;
	private LocalDate user2DeputyIdentifyCreatedDate;
	private String user2DeputyRoles;
	private String user2DeputyIdentifyPhone;
	private String user2DeputyIdentifyEmail;
	private String frontOfUser2DeputyIdentifyUrl;
	private String backOfUser2DeputyIdentifyUrl;
	private String portraitOfUser2DeputyUrl;
	private String channelInit;
	private Integer corporateStatus;
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

	private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
    
	private List<CorporateAccount> corporateAccountList;
	private List<UserCorporateResponse> userInfoList;
	private String packageServiceId;

}
