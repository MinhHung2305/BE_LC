package fis.com.vn.rest.request;

import org.keycloak.representations.idm.RoleRepresentation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleRequest {

	private String id;
	private String name;
	private String description;
	private Boolean composite;
	private RoleRepresentation composites;
	private Boolean clientRole;
}
