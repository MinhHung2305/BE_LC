package fis.com.vn.service.common;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.util.PageSupport;
import org.keycloak.admin.client.resource.GroupResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KeycloakService {
    boolean createRole(String roleName);

    boolean assignRole(String userId, String roleName);

    void assignRoleToGroup(String groupId, List<String> listRoles) throws LCPlatformException;

    RoleRepresentation findRoleByName(String roleName);

    PageSupport<RoleRepresentation> getRoles(Pageable pageable);
    
    boolean removeAssignRole(String userId, String roleName);

    AccessTokenResponse refreshToken(String refreshToken) throws LCPlatformException;

    String createGroup(String roleName);

    GroupResource getGroups(String groupId);

    List<String> getRolesByGroupId(String groupId);

    void deleteUser(String userId);
}
