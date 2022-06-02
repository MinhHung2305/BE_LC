package fis.com.vn.service.bank.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.UserGroupEntity;
import fis.com.vn.model.entity.UserInfoEntity;
import fis.com.vn.model.enumerate.ResponseCode;
import fis.com.vn.repository.UserInfoRepository;
import fis.com.vn.rest.mapper.BankUserRequestMapper;
import fis.com.vn.rest.mapper.BankUserResponseMapper;
import fis.com.vn.rest.request.BankUserRequest;
import fis.com.vn.rest.response.BankUserResponse;
import fis.com.vn.service.bank.BankManageAuthenticationService;
import fis.com.vn.service.common.KeycloakService;
import fis.com.vn.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BankManageAuthenticationServiceImpl implements BankManageAuthenticationService  {
    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    BankUserResponseMapper bankUserResponseMapper;

    @Autowired
    BankUserRequestMapper bankUserRequestMapper;

    @Autowired
    KeycloakService keycloakService;

    /**
     * Get List Data Manage Authentication for Bank.
     *
     * @return : List<BankUserResponse>
     */
    @Override
    public List<BankUserResponse> getManageAuthenticationAll() {
        List<BankUserResponse> bankUserResponseList = new ArrayList<>();
        // TODO Hiện tại trong bảng chưa đầy đủ nên đang chưa thực hiện câu SQL chính xác cho phần này
        // TODO Đang lấy tất cả user có role là BANK.
        List<UserInfoEntity> userList = userInfoRepository.getByUserType(Constant.USER_TYPE_BANK);
        if (userList.isEmpty()) {
            return bankUserResponseList;
        }
        // TODO Hiện tại trong thiết kế Table đang chưa rõ ràng về Chi nhánh ( Branch - nên đang bỏ qua ).
        for (UserInfoEntity userInfoEntity : userList) {
            List<String> listGroupCode = new ArrayList<>();
            List<UserGroupEntity> userGroupList = userInfoEntity.getUserGroupEntitys();
            BankUserResponse bankUserResponse = bankUserResponseMapper.toDomain(userInfoEntity);
            BankUserResponse bankUserRes = null;
            bankUserRes = bankUserResponseMapper.toDomain(userInfoEntity);
            String branchLevels = "";
            for (UserGroupEntity group : userGroupList) {
                listGroupCode.add(group.getUserGroupCode());
                branchLevels = group.getBranchLevel();
            }
            bankUserRes.setBranchLevels(branchLevels);
            bankUserRes.setUserGroupCodes(listGroupCode);
            bankUserResponseList.add(bankUserRes);
        }
        return bankUserResponseList;
    }

    /**
     * Get Record Detail Manage Authentication for Bank.
     *
     * @param id : UserId.
     * @return : BankUserResponse
     */
    @Override
    public BankUserResponse getBankAuthenticationUser(String id) {
        BankUserResponse bankUserRes = null;
        // TODO Hiện tại trong bảng chưa đầy đủ nên đang chưa thực hiện câu SQL chính xác cho phần này
        // TODO Đang lấy user có Id trùng.

        UserInfoEntity userInfo = userInfoRepository.getById(Long.parseLong(id));
        List<String> listGroupCode = new ArrayList<>();
        bankUserRes = bankUserResponseMapper.toDomain(userInfo);
        String branchLevels = "";
        for (UserGroupEntity group : userInfo.getUserGroupEntitys()) {
            listGroupCode.add(group.getUserGroupCode());
            branchLevels = group.getBranchLevel();
        }
        bankUserRes.setBranchLevels(branchLevels);
        bankUserRes.setUserGroupCodes(listGroupCode);
        // TODO Hiện tại trong thiết kế Table đang chưa rõ ràng về Chi nhánh ( Branch - nên đang bỏ qua ).
        return bankUserRes;
    }

    /**
     * Update Status For User.
     *
     * @param bankUserRequest
     * @throws LCPlatformException
     */
    @Override
    @Transactional
    public void updateUser(BankUserRequest bankUserRequest) throws LCPlatformException {
        if (bankUserRequest == null) {
            log.error("bankUserRequest is null when update");
            return;
        }
        UserInfoEntity userBankInfo = null;
        userBankInfo = bankUserRequestMapper.toEntity(bankUserRequest);
        userBankInfo.setUserStatus(bankUserRequest.getUserStatus());
        try {
            userInfoRepository.save(userBankInfo);
        } catch (DataAccessException e) {
            throw new LCPlatformException(ResponseCode.CONFLICT);
        }

    }

    /**
     * Delete User in Keycloak and Database.
     *
     * @param id : userId.
     * @throws LCPlatformException : When Delete Keycloak or DB false.
     */
    @Override
    @Transactional
    public void deleteUser(String id) throws LCPlatformException {
        UserInfoEntity userInfo = userInfoRepository.getById(Long.parseLong(id));
        try {
            keycloakService.deleteUser(userInfo.getUserId());
            log.debug("Delete BankUser in keycloak success: " + userInfo.toString());
        } catch (Exception e) {
            throw new LCPlatformException(ResponseCode.CONFLICT);
        }

        try {
            userInfoRepository.delete(userInfo);
            log.debug("Delete BankUser Database success: " + userInfo.toString());
        } catch (DataAccessException e) {
            throw new LCPlatformException(ResponseCode.CONFLICT);
        }
    }
}
