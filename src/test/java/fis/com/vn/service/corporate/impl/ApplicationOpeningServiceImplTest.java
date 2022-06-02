package fis.com.vn.service.corporate.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.ApplicationOpeningLc;
import fis.com.vn.repository.ApplicationOpeningLcRepository;
import fis.com.vn.repository.BankInfoRepository;
import fis.com.vn.rest.mapper.ApplicationOpeningLcRequestMapper;
import fis.com.vn.rest.mapper.ApplicationOpeningLcResponseMapper;
import fis.com.vn.rest.response.ApplicationOpeningLcResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ApplicationOpeningServiceImplTest {
    @Mock
    private ApplicationOpeningLcRepository mockApplicationOpeningLcRepository;

    @Mock
    private ApplicationOpeningLcRequestMapper mockApplicationOpeningLcRequestMapper;

    @Mock
    private ApplicationOpeningLcResponseMapper mockApplicationOpeningLcResponseMapper;

    @Mock
    private BankInfoRepository mockBankInfoRepository;

    private ApplicationOpeningServiceImpl applicationOpeningServiceImplTest;

    @BeforeEach
    void setUp() throws Exception {
//        applicationOpeningServiceImplTest = new ApplicationOpeningServiceImpl(mockApplicationOpeningLcRepository, mockApplicationOpeningLcRequestMapper, mockApplicationOpeningLcResponseMapper);
    }

    @Test
    void search() {
        ApplicationOpeningLc applicationOpeningLc = new ApplicationOpeningLc();
        applicationOpeningLc.setProposalCodeRelease("DNPHCode");
        applicationOpeningLc.setBankInfo(mockBankInfoRepository.findById(Long.valueOf(1)).get());
        applicationOpeningLc.setStatus(1);
        applicationOpeningLc.setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0));
        applicationOpeningLc.setCreatedBy("createBy");
        final List<ApplicationOpeningLc> applicationOpeningLcResponses = new ArrayList<>();
        when(mockApplicationOpeningLcRepository.search("DNPHCode", 1, "2020-01-01", "2020-01-01", 1L, 1L, 1L, null, null)).thenReturn(applicationOpeningLcResponses);

    }

    @Test
    void getApplicationOpeningLc() throws LCPlatformException {
        // Setup
        final ApplicationOpeningLcResponse applicationOpeningLcResponse = new ApplicationOpeningLcResponse();
        applicationOpeningLcResponse.setProposalCodeRelease("DNPHCode");
        applicationOpeningLcResponse.setBankInfo(mockBankInfoRepository.findById(Long.valueOf(1)).get());
        applicationOpeningLcResponse.setId(0L);
        final Optional<ApplicationOpeningLcResponse> expectedResult = Optional.of(applicationOpeningLcResponse);

        // Configure ApplicationOpeningLc.findById(...).
        final ApplicationOpeningLc applicationOpeningLc = new ApplicationOpeningLc();
        applicationOpeningLc.setId(0L);
        applicationOpeningLc.setBankInfo(mockBankInfoRepository.findById(Long.valueOf(1)).get());
        applicationOpeningLc.setStatus(1);
        applicationOpeningLc.setCreatedDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0));
        applicationOpeningLc.setCreatedBy("createBy");
        final Optional<ApplicationOpeningLc> applicationOpeningLcOptional = Optional.of(applicationOpeningLc);
        when(mockApplicationOpeningLcRepository.findById(0L)).thenReturn(applicationOpeningLcOptional);

        when(mockApplicationOpeningLcResponseMapper.toDomain(any(ApplicationOpeningLc.class))).thenReturn(applicationOpeningLcResponse);

        // Run the test
        final ApplicationOpeningLcResponse result = applicationOpeningServiceImplTest.getApplicationOpeningLc(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() throws LCPlatformException{
        // Run the test
        applicationOpeningServiceImplTest.delete(0L);

        // Verify the results
        verify(mockApplicationOpeningLcRepository).deleteById(0L);
    }

}