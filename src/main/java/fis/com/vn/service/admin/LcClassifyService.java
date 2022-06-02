package fis.com.vn.service.admin;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.rest.response.LcClassifyResponse;

import java.util.List;

public interface LcClassifyService {
    public List<LcClassifyResponse> getAllLcClassify() throws LCPlatformException;
}
