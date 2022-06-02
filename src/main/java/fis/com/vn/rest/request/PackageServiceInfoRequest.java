package fis.com.vn.rest.request;

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
public class PackageServiceInfoRequest {

	private String packageServiceId;

	private String corporateId;
	
	private String packageServiceInfoDescription;

//	    private Integer packageServiceInfoStatus;
}
