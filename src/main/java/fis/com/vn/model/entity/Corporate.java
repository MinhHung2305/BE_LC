package fis.com.vn.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "corporate")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "FieldHandler"})
public class Corporate extends Audit<String> {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "corporate_id")
    private Long corporateId;

    @Column(name = "corporate_code")
    private String corporateCode;

    @Column(name = "corporate_name")
    private String corporateName;

    @Column(name = "corporate_name_en")
    private String corporateNameEn;

    @Column(name = "type_of_corporate")
    private String typeOfCorporate;

    @Column(name = "date_registration")
    private LocalDate dateRegistration;

    @Column(name = "registration_times")
    private String registrationTimes;

    @Column(name = "corporate_address")
    private String corporateAddress;

    @Column(name = "corporate_phone_number")
    private String corporatePhoneNumber;

    @Column(name = "corporate_email")
    private String corporateEmail;

    @Column(name = "corporate_fax_number")
    private String corporateFaxNumber;

    @Column(name = "corporate_website")
    private String corporateWebsite;

    @Column(name = "charter_capital")
    private String charterCapital;

    @Column(name = "par_value_shares")
    private String parValueShares;

    @Column(name = "total_number_of_shares")
    private String totalNumberOfShares;

    @Column(name = "legal_representative_full_name")
    private String legalRepresentativeFullName;

    @Column(name = "legal_representative_identify_number")
    private String legalRepresentativeIdentifyNumber;

    @Column(name = "legal_representative_identify_created_date")
    private LocalDate legalRepresentativeIdentifyCreatedDate;

    @Column(name = "legal_representative_identify_created_place")
    private String legalRepresentativeIdentifyCreatedPlace;

    @Column(name = "business_registration_certificate_url")
    private String businessRegistrationCertificateUrl;

    @Column(name = "user1_deputy_name")
    private String user1DeputyName;

    @Column(name = "user1_deputy_birthday")
    private LocalDate user1DeputyBirthday;

    @Column(name = "user1_deputy_identify_number")
    private String user1DeputyIdentifyNumber;

    @Column(name = "user1_deputy_identify_created_place")
    private String user1DeputyIdentifyCreatedPlace;

    @Column(name = "user1_deputy_identify_created_date")
    private LocalDate user1DeputyIdentifyCreatedDate;

    @Column(name = "user1_deputy_roles")
    private String user1DeputyRoles;

    @Column(name = "user1_deputy_identify_phone")
    private String user1DeputyIdentifyPhone;

    @Column(name = "user1_deputy_identify_email")
    private String user1DeputyIdentifyEmail;

    @Column(name = "front_of_user1_deputy_identify_url")
    private String frontOfUser1DeputyIdentifyUrl;

    @Column(name = "back_of_user1_deputy_identify_url")
    private String backOfUser1DeputyIdentifyUrl;

    @Column(name = "portrait_of_user1_deputy_url")
    private String portraitOfUser1DeputyUrl;

    @Column(name = "user2_deputy_name")
    private String user2DeputyName;

    @Column(name = "user2_deputy_birthday")
    private LocalDate user2DeputyBirthday;

    @Column(name = "user2_deputy_identify_number")
    private String user2DeputyIdentifyNumber;

    @Column(name = "user2_deputy_identify_created_place")
    private String user2DeputyIdentifyCreatedPlace;

    @Column(name = "user2_deputy_identify_created_date")
    private LocalDate user2DeputyIdentifyCreatedDate;

    @Column(name = "user2_deputy_roles")
    private String user2DeputyRoles;

    @Column(name = "user2_deputy_identify_phone")
    private String user2DeputyIdentifyPhone;

    @Column(name = "user2_deputy_identify_email")
    private String user2DeputyIdentifyEmail;

    @Column(name = "front_of_user2_deputy_identify_url")
    private String frontOfUser2DeputyIdentifyUrl;

    @Column(name = "back_of_user2_deputy_identify_url")
    private String backOfUser2DeputyIdentifyUrl;

    @Column(name = "portrait_of_user2_deputy_url")
    private String portraitOfUser2DeputyUrl;

    @Column(name = "channel_init")
    private String channelInit;

    @Column(name = "corporate_status")
    private Integer corporateStatus;

    @Column(name = "signature_serial")
    private String signatureSerial;

    @Column(name = "signature")
    private String signature;

    @Column(name = "signature_subscriber_name")
    private String signatureSubscriberName;

    @Column(name = "signature_time")
    private String signatureTime;

    @Column(name = "signature_certificate")
    private String signatureCertificate;

    @Column(name = "signature_expiration_date")
    private String signatureExpirationDate;

    @Column(name = "signature_value_date")
    private String signatureValueDate;

    @Column(name = "signature_value")
    private String signatureValue;

    @Column(name = "signatureRelease_by")
    private String signatureReleaseBy;

    @Column(name = "signature_tax_code")
    private String signatureTaxCode;

    @OneToMany(mappedBy = "corporate",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<CorporateAccount> corporateAccountList;

    @OneToMany( mappedBy = "corporate",  fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<UserInfoEntity> userInfoList;

}
