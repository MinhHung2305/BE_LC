package fis.com.vn.service.corporate.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.ContractLicense;
import fis.com.vn.model.entity.License;
import fis.com.vn.repository.ContractLicenseRepository;
import fis.com.vn.repository.LicenseRepository;
import fis.com.vn.rest.mapper.ContractLicenseRequestMapper;
import fis.com.vn.rest.mapper.ContractLicenseResponseMapper;
import fis.com.vn.rest.mapper.LicenseRequestMapper;
import fis.com.vn.rest.mapper.LicenseResponseMapper;
import fis.com.vn.rest.request.ContractLicenseRequest;
import fis.com.vn.rest.request.LicenseRequest;
import fis.com.vn.service.admin.LicenseService;
import fis.com.vn.service.corporate.ContractLicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractLicenseServiceImpl implements ContractLicenseService {
    @Autowired
    ContractLicenseRepository contractLicenseRepository;
    @Autowired
    private ContractLicenseRequestMapper contractLicenseRequestMapper;
    @Autowired
    private ContractLicenseResponseMapper contractLicenseResponseMapper;
    @Autowired
    private LicenseResponseMapper licenseResponseMapper;
    @Autowired
    private LicenseRequestMapper licenseRequestMapper;
    @Autowired
    private LicenseService licenseService;
    @Autowired
    LicenseRepository licenseRepository;

    @Override
    public ContractLicense createContractLicense(ContractLicense contractLicense) throws LCPlatformException {
        return contractLicenseRepository.save(contractLicense);
    }

    @Override
    public ContractLicense create(ContractLicenseRequest contractLicenseRequest) throws LCPlatformException {

        ContractLicense contractLicense = contractLicenseRequestMapper.toEntity(contractLicenseRequest);
        return contractLicenseRepository.save(contractLicense);
    }

    @Override
    public List<ContractLicense> insertOrUpdateContractLicense(List<ContractLicenseRequest> contractLicenseList, Integer contractId) {
        List<ContractLicense> contractLicenses = new ArrayList<>();
        List<ContractLicenseRequest> contractLicensesAdd = insertLicenNotExist(contractLicenseList);

//        List<ContractLicenseRequest> contractLicenseList
        List<ContractLicense> contractLicensesDb = contractLicenseRepository.findAllByContractId(contractId);

        //lấy ra tất cả id contractLicense trong req
        List<Long> idContractLicenseRequestFilter = new ArrayList<Long>();
        for (ContractLicenseRequest contractLicenseRequest : contractLicensesAdd) {
            idContractLicenseRequestFilter.add(contractLicenseRequest.getContractLicenseId());
        }

        //lấy ra list ContractLicense có trong db nhưng không có trong req -- se xoa trong db
        List<ContractLicense> contractLicensesInDbNotInRequest = contractLicensesDb
                .stream()
                .filter(contractLicense -> !idContractLicenseRequestFilter.contains(contractLicense.getId()))
                .collect(Collectors.toList());

        for (ContractLicense contractLicense : contractLicensesInDbNotInRequest) {
            contractLicenseRepository.delete(contractLicense);
        }

        // lọc ra list ContractLicenseRequest có contractLicenseId == null để tạo mới
        for (ContractLicenseRequest contractLicenseReq : contractLicensesAdd) {
            ContractLicense contractLicense = new ContractLicense();
            contractLicense.setId(contractLicenseReq.getContractLicenseId());
            contractLicense.setContractId(contractId);
            contractLicense.setLicenseId(contractLicenseReq.getLicenseRequest().getLicenseId());
            contractLicense.setLicenseDescription(contractLicenseReq.getLicenseDescription());
            contractLicenseRepository.save(contractLicense);
            contractLicenses.add(contractLicense);
        }

        return contractLicenses;
    }

    public List<ContractLicenseRequest> insertLicenNotExist(List<ContractLicenseRequest> contractLicenseList) {

        List<ContractLicenseRequest> contractLicenseAdd = new ArrayList<>();

        // lay chung tu co id != null
        contractLicenseAdd = contractLicenseList.stream()
                .filter(license -> license.getLicenseRequest().getLicenseId() != null)
                .collect(Collectors.toList());

        // lay chung tu co id = null - tạo moi
        contractLicenseList = contractLicenseList.stream()
                .filter(license -> license.getLicenseRequest().getLicenseId() == null)
                .collect(Collectors.toList());

        // lay ten chung tu va check trung ten
        List<String> nameLicenseRequestFilter = new ArrayList<String>();
        List<ContractLicenseRequest> licenseRequestFilter = new ArrayList<ContractLicenseRequest>();
        for (ContractLicenseRequest element : contractLicenseList) {
            if (!nameLicenseRequestFilter.contains(element.getLicenseRequest().getLicenseName())) {
                nameLicenseRequestFilter.add(element.getLicenseRequest().getLicenseName());
                licenseRequestFilter.add(element);
            }
        }
        // lấy tất cả license và lấy tên
        List<License> licenses = licenseRepository.findAll();
        List<String> allNameLicenseInDB = licenses.stream().map(license -> license.getLicenseName()).collect(Collectors.toList());

        //license chua co trong db
        List<ContractLicenseRequest> licenseRequestNotInDB = new ArrayList<ContractLicenseRequest>();

        boolean checkExist;
        for (ContractLicenseRequest contractLicense : licenseRequestFilter) {
            checkExist = true;
            ContractLicenseRequest contractLicenseRequest = new ContractLicenseRequest();
            for (String licenseName : allNameLicenseInDB) {
                if (contractLicense.getLicenseRequest().getLicenseName().equals(licenseName)) {
                    checkExist = true;
                    break;
                } else {
                    checkExist = false;
                }
            }
            // existed , get license by licenseName
            if (checkExist) {
                LicenseRequest licenseRequestGet = licenseRequestMapper.toDomain(
                        licenseService.getLicenseByLicenseName(contractLicense.getLicenseRequest().getLicenseName()));

                contractLicenseRequest.setLicenseRequest(licenseRequestGet);
                if(contractLicenseRequest.getContractId() != null ){
                    contractLicense.setContractId(contractLicenseRequest.getContractId());
                }
                contractLicenseRequest.setLicenseDescription(contractLicense.getLicenseDescription());
                contractLicenseAdd.add(contractLicenseRequest);
            } else {
                // not exist, insert to table license
                License license = new License();
                license.setLicenseName(contractLicense.getLicenseRequest().getLicenseName());
                LicenseRequest licenseRequestAdd = licenseRequestMapper.toDomain(licenseRepository.save(license));
                if(contractLicenseRequest.getContractId() != null ){
                    contractLicense.setContractId(contractLicenseRequest.getContractId());
                }
                contractLicenseRequest.setLicenseRequest(licenseRequestAdd);
                contractLicenseRequest.setLicenseDescription(contractLicense.getLicenseDescription());

                contractLicenseAdd.add(contractLicenseRequest);

            }

        }
        return contractLicenseAdd;
    }

    @Override
    public List<ContractLicense> insertOrUpdateContractLicenseForApplicationOpeningLc(List<ContractLicenseRequest> contractLicenseList, Long applicationOpeningLcId) {
        List<ContractLicense> contractLicenses = new ArrayList<>();
        List<ContractLicenseRequest> contractLicensesAdd = insertLicenNotExist(contractLicenseList);

//        List<ContractLicenseRequest> contractLicenseList
        List<ContractLicense> contractLicensesDb = contractLicenseRepository.findAllByApplicationOpeingLcId(applicationOpeningLcId);

        //lấy ra tất cả id contractLicense trong req
        List<Long> idContractLicenseRequestFilter = new ArrayList<Long>();
        for (ContractLicenseRequest contractLicenseRequest : contractLicensesAdd) {
            idContractLicenseRequestFilter.add(contractLicenseRequest.getContractLicenseId());
        }

        //lấy ra list ContractLicense có trong db nhưng không có trong req -- se xoa trong db
        List<ContractLicense> contractLicensesInDbNotInRequest = contractLicensesDb
                .stream()
                .filter(contractLicense -> !idContractLicenseRequestFilter.contains(contractLicense.getId()))
                .collect(Collectors.toList());

        for (ContractLicense contractLicense : contractLicensesInDbNotInRequest) {
            contractLicenseRepository.delete(contractLicense);
        }

        // lọc ra list ContractLicenseRequest có contractLicenseId == null để tạo mới
        for (ContractLicenseRequest contractLicenseReq : contractLicensesAdd) {
            ContractLicense contractLicense = new ContractLicense();
            contractLicense.setId(contractLicenseReq.getContractLicenseId());
            if(contractLicenseReq.getContractId() != null){
                contractLicense.setContractId(contractLicenseReq.getContractId());
            }
            contractLicense.setApplicationOpeingLcId(applicationOpeningLcId);
            contractLicense.setLicenseId(contractLicenseReq.getLicenseRequest().getLicenseId());
            contractLicense.setLicenseDescription(contractLicenseReq.getLicenseDescription());
            contractLicenseRepository.save(contractLicense);
            contractLicenses.add(contractLicense);
        }

        return contractLicenses;
    }

//    @Override
    public List<ContractLicense> insertContractLicenseForApplicationOpeningLc(List<ContractLicenseRequest> contractLicenseList, Long applicationOpeningLcId) {
        List<ContractLicense> contractLicenses = new ArrayList<>();
        List<ContractLicenseRequest> contractLicensesAdd = insertLicenNotExist(contractLicenseList);

        // lọc ra list ContractLicenseRequest có contractLicenseId == null để tạo mới
        for (ContractLicenseRequest contractLicenseReq : contractLicensesAdd) {
            contractLicenseReq.setContractLicenseId(null);
            ContractLicense contractLicense = new ContractLicense();
            contractLicense.setContractId(contractLicenseReq.getContractId());
            contractLicense.setApplicationOpeingLcId(applicationOpeningLcId);
            contractLicense.setLicenseId(contractLicenseReq.getLicenseRequest().getLicenseId());
            contractLicense.setLicenseDescription(contractLicenseReq.getLicenseDescription());
            contractLicenseRepository.save(contractLicense);
            contractLicenses.add(contractLicense);
        }

        return contractLicenses;
    }

}
