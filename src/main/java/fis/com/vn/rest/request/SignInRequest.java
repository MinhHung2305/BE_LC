package fis.com.vn.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {
	
	private String password;
	private String username;
}
