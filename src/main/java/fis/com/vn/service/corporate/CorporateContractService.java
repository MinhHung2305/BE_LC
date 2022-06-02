package fis.com.vn.service.corporate;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.exception.SendEmailException;
import fis.com.vn.rest.request.ContractRequest;
import fis.com.vn.rest.request.GenFileRequest;
import fis.com.vn.rest.request.SignDigitalRequest;
import fis.com.vn.rest.response.ContractResponse;
import fis.com.vn.rest.response.GenFileResponse;
import fis.com.vn.rest.response.TransactionCodeResponse;

import java.io.IOException;
import java.util.List;

public interface CorporateContractService {
    List<ContractResponse> getAllContract() throws LCPlatformException;

    List<ContractResponse> getAllContractByCorporateBuyer(Long corporateId) throws LCPlatformException;

    List<ContractResponse> getAllContractByCorporateSeller(Long corporateId) throws LCPlatformException;

    ContractResponse getContractById(Integer id) throws LCPlatformException;

    ContractResponse createContract(ContractRequest contractRequest) throws LCPlatformException;

    ContractResponse updateContract(ContractRequest contractRequest) throws LCPlatformException;

    void deleteContractById(Integer contractId) throws LCPlatformException;

    void changeStateContract(ContractRequest contractRequest) throws LCPlatformException, SendEmailException;

    String generatePdfFile(String pdfFileName, ContractRequest contractRequest) throws LCPlatformException;

    TransactionCodeResponse signInSignature(String userId, Integer contractId, String signPosition) throws LCPlatformException;

    String signDigital(SignDigitalRequest signDigitalRequest, String nameFile) throws LCPlatformException, IOException;

    GenFileResponse genPdfFile(String pdfFileName, ContractRequest contractRequest) throws LCPlatformException;

    GenFileResponse updateUrlFile (GenFileRequest genFileRequest)throws LCPlatformException;
}