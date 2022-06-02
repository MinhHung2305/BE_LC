package fis.com.vn.service.corporate;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.Contract;
import fis.com.vn.model.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> saveAllProduct(List<Product> listProduct, Integer contractId) throws LCPlatformException;
    public List<Product> insertOrUpdateProduct(List<Product> listProduct, Long applicationOpeningLcId, Contract contract) throws LCPlatformException;

    void deleteProduct(List<Product> listProduct);
}
