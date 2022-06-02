package fis.com.vn.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class KeyCloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

	@Override
	public Collection<GrantedAuthority> convert(Jwt jwt) {
		
		Map<String, Object> realmAcess = (Map<String, Object>) jwt.getClaims().get("realm_access");
		if(realmAcess == null || realmAcess.isEmpty()) {
			return new ArrayList<>();
		}
		Collection<GrantedAuthority> returnValue = ((List<String>) realmAcess.get("roles"))
				.stream().map(roleName -> "ROLE_" + roleName)
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		
		return returnValue;
	}

}
