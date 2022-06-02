package fis.com.vn.rest.mapper;

import fis.com.vn.model.entity.Product;
import fis.com.vn.rest.request.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductRequestMapper extends LcMapper<Product, ProductRequest>{
}
