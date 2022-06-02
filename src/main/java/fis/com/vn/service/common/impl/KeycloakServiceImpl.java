package fis.com.vn.service.common.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.enumerate.ResponseCode;
import fis.com.vn.service.common.KeycloakService;
import fis.com.vn.util.PageSupport;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.*;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.authorization.client.util.Http;
import org.keycloak.authorization.client.util.HttpResponseException;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.util.*;
import java.util.stream.Collectors;

import static org.keycloak.OAuth2Constants.*;

@Service
@Slf4j
public class KeycloakServiceImpl implements KeycloakService {

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.auth-server-url}")
    public String keyCloakAuthServerUrl;

    @Value("${keycloak.realm}")
    public String keycloakRealm;

    @Value("${keycloak.resource}")
    public String resourceClientId;

    @Value("${keycloak.credentials.secret}")
    public String resourceClientSecret;

    @Autowired
    private Keycloak keycloak;

    @Override
    public boolean createRole(String roleName) {
        RoleRepresentation role = new RoleRepresentation();
        role.setName(roleName);
        keycloak.realm(realm).roles().create(role);
        return true;
    }

    @Override
    public boolean assignRole(String userId, String roleName) {
        RoleRepresentation role = findRoleByName(roleName);
        keycloak.realm(realm).users().get(userId).roles().realmLevel().add(Collections.singletonList(role));
        return false;
    }

    @Override
    public void assignRoleToGroup(String groupId, List<String> listRoles) throws LCPlatformException{
        List<RoleRepresentation> roleRepresentationList = keycloak.realm(realm).roles().list();
        List<RoleRepresentation> listUpdateRole = roleRepresentationList.stream()
                .filter(roleRepresentation -> listRoles.stream()
                        .anyMatch(role -> roleRepresentation.getName().equals(role)))
                .collect(Collectors.toList());
        keycloak.realm(realm).groups().group(groupId).roles().realmLevel().add(listUpdateRole);
    }

    @Override
    public RoleRepresentation findRoleByName(String roleName) {
        return keycloak.realm(realm).roles().get(roleName).toRepresentation();
    }

    @Override
    public PageSupport<RoleRepresentation> getRoles(Pageable pageable) {
        List<RoleRepresentation> roleRepresentations = keycloak.realm(realm).roles().list();
        return new PageSupport<>(roleRepresentations.stream()
                .skip((long) pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize()).collect(Collectors.toList()),
                pageable.getPageNumber(),
                pageable.getPageSize(),
                roleRepresentations.size());
    }
    
    @Override
	public boolean removeAssignRole(String userId, String roleName) {
		RoleRepresentation role = findRoleByName(roleName);
        keycloak.realm(realm).users().get(userId).roles().realmLevel().remove(Collections.singletonList(role));
        return true;
	}

    @Override
    @Transactional
    public AccessTokenResponse refreshToken(String refreshToken) throws LCPlatformException {
        AccessTokenResponse accessTokenResponse;
        Map<String, Object> clientCredentials = new HashMap<>();
        clientCredentials.put("secret", resourceClientSecret);
        clientCredentials.put("grant_type", "password");
        Configuration configuration = new Configuration(
                keyCloakAuthServerUrl,
                realm,
                resourceClientId,
                clientCredentials,
                null);
        String url = keyCloakAuthServerUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        Http http = new Http(configuration, (params, headers) -> {
        });
        try {
            accessTokenResponse = http.<AccessTokenResponse>post(url).authentication().client().form()
                    .param(GRANT_TYPE, REFRESH_TOKEN)
                    .param(REFRESH_TOKEN, refreshToken)
                    .param(CLIENT_ID, resourceClientId)
                    .param(CLIENT_SECRET, resourceClientSecret)
                    .response()
                    .json(AccessTokenResponse.class)
                    .execute();
        } catch (HttpResponseException e) {
            throw new LCPlatformException(ResponseCode.INVALID_TOKEN);
        }
        return accessTokenResponse;
    }

    @Override
    public String createGroup(String groupCode) {
        GroupRepresentation groupRepresentation = new GroupRepresentation();
        groupRepresentation.setName(groupCode);
        String groupId = null;
        Response response = keycloak.realm(realm).groups().add(groupRepresentation);
        if (response.getStatus() == 201) {
            groupId = CreatedResponseUtil.getCreatedId(response);
        }

        return groupId;
    }

    @Override
    public GroupResource getGroups(String groupId) {
        return keycloak.realm(realm).groups().group(groupId);
    }

    @Override
    public List<String> getRolesByGroupId(String groupId) {
        List<String> listRole = getGroups(groupId).toRepresentation().getRealmRoles();

        return  listRole;
    }

    @Override
    public void deleteUser(String userId) {
        keycloak.realm(keycloakRealm).users().get(userId).remove();
    }
}
