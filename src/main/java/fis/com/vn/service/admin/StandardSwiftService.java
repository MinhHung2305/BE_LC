package fis.com.vn.service.admin;

import fis.com.vn.rest.response.StandardSwiftResponse;

import java.util.List;

public interface StandardSwiftService {
    List<StandardSwiftResponse> getAllByPowerType(Long powerTypeId);
}
