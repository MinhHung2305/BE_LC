package fis.com.vn.util;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Objects;

public class SecurityUtils {
    public static String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Jwt principal = (authentication == null || authentication.getPrincipal() == null || "anonymousUser".equals(authentication.getPrincipal())) ?
                null : (Jwt) authentication.getPrincipal();
        if (Objects.isNull(principal)) {
            return null;
        }
        return principal.getClaim("preferred_username");

    }
}
