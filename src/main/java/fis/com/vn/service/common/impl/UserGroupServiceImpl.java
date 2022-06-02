package fis.com.vn.service.common.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.UserGroupEntity;
import fis.com.vn.model.enumerate.ResponseCode;
import fis.com.vn.model.enumerate.RolesInfo;
import fis.com.vn.repository.BankInfoRepository;
import fis.com.vn.repository.UserGroupRepository;
import fis.com.vn.rest.mapper.UserGroupRequestMapper;
import fis.com.vn.rest.mapper.UserGroupResponseMapper;
import fis.com.vn.rest.request.UserGroupRequest;
import fis.com.vn.rest.response.RoleResponse;
import fis.com.vn.rest.response.UserGroupResponse;
import fis.com.vn.service.common.BankInfoService;
import fis.com.vn.service.common.UserGroupService;
import fis.com.vn.service.common.UserInfoService;
import fis.com.vn.util.PageSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    UserGroupRepository userGroupRepository;

    @Autowired
    UserGroupRequestMapper userGroupRequestMapper;

    @Autowired
    UserGroupResponseMapper userGroupResponseMapper;

    @Autowired
    KeycloakServiceImpl keycloakService;

    @Autowired
    RolesServiceImpl rolesService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    BankInfoRepository bankInfoRepository;

    @Autowired
    BankInfoService bankInfoService;

    @Override
    public UserGroupEntity findByUserGroupCode(String userGroupCode) {
        List<UserGroupEntity> userGroupList = userGroupRepository.findByUserGroupCode(userGroupCode);
        UserGroupEntity userGroupEntity = new UserGroupEntity();
        if (userGroupList.size() > 0) {
            userGroupEntity = userGroupList.get(0);
            return userGroupEntity;
        }
        return null;
    }

    @Override
    public UserGroupEntity findByUserGroupId(Long userGroupId) {
        try {
            UserGroupEntity userGroupEntity = userGroupRepository.getById(userGroupId);
            userGroupResponseMapper.toDomain(userGroupEntity);
            return userGroupEntity;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserGroupResponse findUserGroupById(Long userGroupId) {
        try {
            UserGroupEntity userGroupEntity = userGroupRepository.findById(userGroupId).get();
            UserGroupResponse userGroupResponse = userGroupResponseMapper.toDomain(userGroupEntity);
            return userGroupResponse;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public PageSupport<UserGroupEntity> findAll(Pageable pageable) {
        List<UserGroupEntity> userGroupList = userGroupRepository.findAll(Sort.by(Sort.Direction.DESC, "lastModifiedDate"));
        return new PageSupport<>(
                userGroupList.stream().skip((long) pageable.getPageNumber() * pageable.getPageSize())
                        .limit(pageable.getPageSize()).collect(Collectors.toList()),
                pageable.getPageNumber(), pageable.getPageSize(), userGroupList.size());
    }

    @Override
    public List<UserGroupEntity> findAll() {
        List<UserGroupEntity> userGroupList = userGroupRepository.findAll(Sort.by(Sort.Direction.DESC, "lastModifiedDate"));
        return userGroupList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserGroupResponse createUserGroup(String userId, UserGroupRequest userGroup) throws LCPlatformException {
        UserGroupResponse userGroupResponse = null;
        String groupId = null;
        try {
            groupId = keycloakService.createGroup(userGroup.getUserGroupCode());
            if (groupId != null) {
                UserGroupEntity userGroupEntity = userGroupRequestMapper.toEntity(userGroup);
                userGroupEntity.setGroupId(groupId);
                userGroupEntity.setBankInfo(bankInfoRepository.findBankInfoByBankCode(userInfoService.getUserLogin(userId).getBankCode()));

                if (userGroup.getPermissionList() != null) {
                    keycloakService.assignRoleToGroup(groupId, userGroup.getPermissionList());
                    userGroupEntity.setPermission(String.join(",", userGroup.getPermissionList()));
                }
                UserGroupEntity userGroupAdd = userGroupRepository.save(userGroupEntity);
                userGroupResponse = userGroupResponseMapper.toDomain(userGroupAdd);
                return userGroupResponse;
            } else {
//                keycloakService.getGroups(groupId).remove();
                throw new LCPlatformException(ResponseCode.CONFLICT, "UserGroup already exist");
            }
        } catch (Exception e) {
            log.info("Error create UserGroup: " + e.toString());
            if (groupId != null) {
                keycloakService.getGroups(groupId).remove();
            }
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new LCPlatformException(ResponseCode.CONFLICT, "UserGroup already exist");
        }
    }

    @Override
    public UserGroupEntity updateUserGroup(UserGroupEntity userGroup) {
        try {
            UserGroupEntity userG = userGroupRepository.findById(userGroup.getId()).get();
            userG.setBranchLevel(userGroup.getBranchLevel());
            userG.setUserGroupName(userGroup.getUserGroupName());
            userG.setChannels(userGroup.getChannels());
            userG.setUserType(userGroup.getUserType());
            userG.setGroupType(userGroup.getGroupType());
            userG.setBranchLevel(userGroup.getBranchLevel());
            userG.setRole(userGroup.getRole());
            userG.setStatus(userGroup.getStatus());
            userG.setDescription(userGroup.getDescription());
            userG.setPermission(userGroup.getPermission());
            UserGroupEntity userGroupUp = userGroupRepository.save(userG);
            return userGroupUp;
        } catch (Exception e) {
            log.info("Error Update UserGroup::: " + e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserGroupResponse updateUserGroup(UserGroupRequest userGroupRequest) {
        UserGroupEntity groupOld = userGroupRepository.findById(userGroupRequest.getId()).get();
        try {
            UserGroupEntity userGroupEntity = userGroupRequestMapper.toEntity(userGroupRequest);
            userGroupEntity.setGroupId(groupOld.getGroupId());
            userGroupEntity.setCreatedDate(groupOld.getCreatedDate());
            userGroupEntity.setCreatedBy(groupOld.getCreatedBy());
            userGroupEntity.setBankInfo(bankInfoService.getById(userGroupRequest.getBankId()));

            if (userGroupRequest.getPermissionList() != null) {
                userGroupEntity.setPermission(String.join(",", userGroupRequest.getPermissionList()));
                keycloakService.assignRoleToGroup(groupOld.getGroupId(), userGroupRequest.getPermissionList());
            }
            UserGroupEntity userGroupUpdate = userGroupRepository.save(userGroupEntity);
            return userGroupResponseMapper.toDomain(userGroupUpdate);
        } catch (Exception e) {
            log.info("Error create UserGroup: " + e.toString());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new LCPlatformException(ResponseCode.BAD_REQUEST, "Update UserGroup fail");
        }
    }

    @Override
    public UserGroupEntity deleteByUserGroupCode(Long id) {
        try {
            UserGroupEntity userGroupDel = userGroupRepository.getById(id);

            userGroupRepository.delete(userGroupDel);
            keycloakService.getGroups(userGroupDel.getGroupId()).remove();

            return userGroupDel;
        } catch (Exception e) {
            log.error("Error Del UserGroup::: " + e.getMessage());
            throw new LCPlatformException(ResponseCode.GROUP_IN_USED);
        }
    }

    @Override
    public List<UserGroupEntity> searchUserGroup(UserGroupEntity userGroup) {
        List<UserGroupEntity> userGroupList = userGroupRepository.searchUserGroup(userGroup.getUserGroupCode(),
                userGroup.getUserGroupName(), userGroup.getChannels(), userGroup.getUserType(),
                userGroup.getGroupType(), userGroup.getRole());
        return userGroupList;
    }

    @Override
    public UserGroupResponse getPermissionUserGroup(String userGroupCode) {
        try {
            UserGroupResponse userGroupResponse = null;
            List<UserGroupEntity> userGroupList = userGroupRepository.findByUserGroupCode(userGroupCode);

            if (userGroupList != null && !userGroupList.isEmpty()) {
                UserGroupEntity userGroupEntity = userGroupList.get(0);
                userGroupResponse = userGroupResponseMapper.toDomain(userGroupEntity);
                RoleResponse roleResponse = rolesService.getRolesByRoleType(userGroupEntity.getUserType());
                userGroupResponse.setRoleResponse(roleResponse);
            }

            return userGroupResponse;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Error getPermission UserGroup::: " + e.getMessage());
            return null;
        }
    }

    @Override
    public UserGroupResponse getPermission(String userType) {
        try {
            UserGroupResponse userGroupResponse = new UserGroupResponse();

            if (userType != null && !userType.isEmpty()) {
                userGroupResponse.setRole(RolesInfo.APPROVER.getName());
                RoleResponse roleResponse = rolesService.getRolesByRoleType(userType);
                userGroupResponse.setRoleResponse(roleResponse);
            }

            return userGroupResponse;
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Error getPermission UserGroup::: " + e.getMessage());
            return null;
        }
    }

    @Override
    public UserGroupEntity updatePermissionUserGroup(String userGroupCode, List<String> permission) {
        try {
            List<UserGroupEntity> userGroupList = userGroupRepository.findByUserGroupCode(userGroupCode);
            String groupId = null;
            UserGroupEntity userGroup = new UserGroupEntity();
            if (userGroupList.size() > 0) {
                userGroup = userGroupList.get(0);
                groupId = userGroup.getGroupId();
            }
            userGroup.setPermission(String.join(",", permission));
            keycloakService.assignRoleToGroup(groupId, permission);
            return userGroupRepository.save(userGroup);
        } catch (Exception e) {
            log.info("Error updatePermission UserGroup::: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<UserGroupEntity> searchUserGroupInUser(String branchLevel, String groupType) {
        try {
            List<UserGroupEntity> userGroupList = userGroupRepository.searchUserGroupInUser(branchLevel, groupType);
            return userGroupList;
        } catch (Exception e) {
            log.info("Error searchUserGroupInUser1: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<UserGroupEntity> searchUserGroupInUser(String branchLevel, String groupType, String roles) {
        try {
            List<UserGroupEntity> userGroupList = userGroupRepository.searchUserGroupInUser(branchLevel, groupType,
                    roles);
            return userGroupList;
        } catch (Exception e) {
            log.info("Error searchUserGroupInUser2: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<UserGroupEntity> searchGroupInUserFPT() {
        try {
            List<UserGroupEntity> userGroupList = userGroupRepository.searchGroupInUserFPT(RolesInfo.SYSTEM.getName());
            return userGroupList;
        } catch (Exception e) {
            log.info("Error searchUserGroupInUser2: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<UserGroupEntity> getByUserType(String userType) {
        List<UserGroupEntity> userGroupList = userGroupRepository.getByUserType(userType);
        return userGroupList;
    }

    @Override
    public List<UserGroupEntity> getByGroupType(String groupType) {
        List<UserGroupEntity> userGroupList = userGroupRepository.getByGroupType(groupType);
        return userGroupList;
    }

}
