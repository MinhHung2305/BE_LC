package fis.com.vn.service.common;


import fis.com.vn.rest.response.RoleResponse;

public interface RolesService {
    RoleResponse getRolesByRoleType(String roleType);
}
