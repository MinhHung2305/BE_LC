package fis.com.vn.service.common;

import fis.com.vn.model.entity.PackageServiceInfo;
import fis.com.vn.rest.request.PackageServiceInfoRequest;

public interface PackageServiceInfoService {

	PackageServiceInfo create(PackageServiceInfoRequest packageServiceRequest);
}
