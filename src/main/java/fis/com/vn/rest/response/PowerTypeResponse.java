package fis.com.vn.rest.response;

import fis.com.vn.model.entity.BankInfo;
import fis.com.vn.model.entity.HistoryPowerType;
import fis.com.vn.model.entity.StandardSwiftPowerType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PowerTypeResponse {
    private Long id;
    private String powerType;
    private String powerName;
    private String purposeUse;
    private String version;
    private String powerCodeFpt;
    private String senderReferenceNumber;
    private String recipientReferenceNumber;
    private BankInfo sendingBank;
    private BankInfo receivingBank;
    private String information;
    private List<HistoryPowerType> historyPowerTypes;
    private List<StandardSwiftPowerType> standardSwiftPowerType;

    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
}