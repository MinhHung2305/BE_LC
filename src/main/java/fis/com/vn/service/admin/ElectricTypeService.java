package fis.com.vn.service.admin;

import fis.com.vn.rest.response.ElectricTypeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ElectricTypeService {
    List<ElectricTypeResponse> findAll();
    ElectricTypeResponse getElectricType(Long id);
}
