package fis.com.vn.service.admin.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.ContractLicense;
import fis.com.vn.model.entity.License;
import fis.com.vn.repository.ContractLicenseRepository;
import fis.com.vn.repository.LicenseRepository;
import fis.com.vn.rest.mapper.LicenseRequestMapper;
import fis.com.vn.rest.request.LicenseRequest;
import fis.com.vn.service.admin.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LicenseServiceImpl implements LicenseService {
    @Autowired
    LicenseRepository licenseRepository;
    @Autowired
    private LicenseRequestMapper licenseRequestMapper;

    @Autowired
    private ContractLicenseRepository contractLicenseRepository;

    @Override
    public License createLicense(License license) throws LCPlatformException {
        return licenseRepository.save(license);
    }

    @Override
    public License create(LicenseRequest licenseRequest) {
        License license = licenseRequestMapper.toEntity(licenseRequest);
        licenseRepository.save(license);
        return license;
    }


    public List<Long> insertLicenNotExist(List<License> licenseListRequest) {
        // lay chung tu co id = null - tạo moi
        licenseListRequest = licenseListRequest.stream().filter(license -> license.getLicenseId() == null).collect(Collectors.toList());

        List<Long> idLicensesAdd = new ArrayList<>();
        List<License> licenses = licenseRepository.findAll();

        // lay ten chung tu va check trung ten
        List<String> nameLicenseRequestFilter = new ArrayList<String>();
        for (License element : licenseListRequest) {
            if (!nameLicenseRequestFilter.contains(element.getLicenseName())) {
                nameLicenseRequestFilter.add(element.getLicenseName());
            }
        }

        List<String> allNameLicenseInDB = licenses.stream().map(license -> license.getLicenseName()).collect(Collectors.toList());
        //license chua co trong db
        List<String> licensesNotInDb = nameLicenseRequestFilter
                .stream()
                .filter(license -> !allNameLicenseInDB.contains(license))
                .collect(Collectors.toList());
        for (String licenseName : licensesNotInDb) {
            License license = new License();
            license.setLicenseName(licenseName);
            License licenseAdd = licenseRepository.save(license);
            idLicensesAdd.add(licenseAdd.getLicenseId());
        }
        //license da co trong db nhung van add licenseName = Khac
        List<String> nameLicenseExistInDb = nameLicenseRequestFilter
                .stream()
                .filter(license -> allNameLicenseInDB.contains(license))
                .collect(Collectors.toList());
        List<License> licenseInDb = licenses
                .stream()
                .filter(license -> nameLicenseExistInDb.contains(license.getLicenseName()))
                .collect(Collectors.toList());
        for (License licenseExist : licenseInDb) {
            idLicensesAdd.add(licenseExist.getLicenseId());
        }
        return idLicensesAdd;
    }

    @Override
    public List<License> insertOrUpdateLicenseContract(List<License> licenseList, Integer contractId) {
        List<Long> licensesAdd = insertLicenNotExist(licenseList);
        List<ContractLicense> contractLicensesDb = contractLicenseRepository.findAllByContractId(contractId);

        // lay chung tu co id = null - tạo moi // loc cac idLicenseContract trung id
        licenseList = licenseList.stream().filter(license -> license.getLicenseId() != null).collect(Collectors.toList());
        List<Long> idLicenseRequestFilter = new ArrayList<Long>();
        for (License element : licenseList) {
            if (!idLicenseRequestFilter.contains(element.getLicenseId())) {
                idLicenseRequestFilter.add(element.getLicenseId());
            }
        }
        idLicenseRequestFilter.addAll(licensesAdd);

        List<Long> idContractLicenseInDB = contractLicensesDb.stream().map(license -> license.getLicenseId()).collect(Collectors.toList());
        List<ContractLicense> contractLicensesInDbNotInRequest = contractLicensesDb
                .stream()
                .filter(license -> !idLicenseRequestFilter.contains(license.getLicenseId()))
                .collect(Collectors.toList());

        List<Long> idContractLicensesToCreate = idLicenseRequestFilter
                .stream()
                .filter(license -> !idContractLicenseInDB.contains(license))
                .collect(Collectors.toList());
        for (ContractLicense contractLicense : contractLicensesInDbNotInRequest) {
            contractLicenseRepository.delete(contractLicense);
        }

        for (Long licenseId : idContractLicensesToCreate) {
            ContractLicense contractLicense = new ContractLicense();
            contractLicense.setLicenseId(licenseId);
            contractLicense.setContractId(contractId);
            contractLicenseRepository.save(contractLicense);
        }
        return licenseList;
    }

    @Override
    public List<License> insertOrUpdateLicense(List<License> licenseList, Long applicationOpeningLcId) {
        List<ContractLicense> contractLicensesDb = contractLicenseRepository.findAllByApplicationOpeingLcId(applicationOpeningLcId);
        List<Long> idLicenseRequest = licenseList.stream().map(license -> license.getLicenseId()).collect(Collectors.toList());
        List<Long> idLicenseDB = contractLicensesDb.stream().map(license -> license.getLicenseId()).collect(Collectors.toList());
        List<ContractLicense> contractLicensesInDbNotInRequest = contractLicensesDb
                .stream()
                .filter(license -> !idLicenseRequest.contains(license.getLicenseId()))
                .collect(Collectors.toList());
        List<Long> icContractLicensesToCreate = idLicenseRequest
                .stream()
                .filter(license -> !idLicenseDB.contains(license))
                .collect(Collectors.toList());
        for (ContractLicense contractLicense : contractLicensesInDbNotInRequest) {
            contractLicenseRepository.delete(contractLicense);
        }

        for (Long licenseId : icContractLicensesToCreate) {
            ContractLicense contractLicense = new ContractLicense();
            contractLicense.setLicenseId(licenseId);
            contractLicense.setApplicationOpeingLcId(applicationOpeningLcId);
            contractLicenseRepository.save(contractLicense);
        }
        return licenseList;
    }

    @Override
    public License getLicenseByLicenseName(String licenseName) {
        License license = new License();
        List<License> licenses = licenseRepository.getLicenseByLicenseName(licenseName);
        if(!licenses.isEmpty() && licenses != null){
            license = licenses.get(0);
        }
        return license;
    }
}
