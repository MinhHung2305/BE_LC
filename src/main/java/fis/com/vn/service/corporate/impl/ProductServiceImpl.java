package fis.com.vn.service.corporate.impl;

import fis.com.vn.exception.LCPlatformException;
import fis.com.vn.model.entity.Contract;
import fis.com.vn.model.entity.Product;
import fis.com.vn.repository.ApplicationOpeningLcRepository;
import fis.com.vn.repository.ContractRepository;
import fis.com.vn.repository.ProductRepository;
import fis.com.vn.service.corporate.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ContractRepository contractRepository;
    @Autowired
    ApplicationOpeningLcRepository applicationOpeningLcRepository;

    @Override
    public List<Product> saveAllProduct(List<Product> listProduct, Integer contractId) throws LCPlatformException {
        List<Product> listProductBD = productRepository.getAllProductByContract(contractId);
        List<Long> idProductRequest = listProduct.stream().map(product -> product.getId()).collect(Collectors.toList());
        List<Product> productNotInDb = listProductBD
                .stream()
                .filter(product -> !idProductRequest.contains(product.getId()))
                .collect(Collectors.toList());
        for(Product product : productNotInDb){
            productRepository.delete(product);
        }
        for(Product productIU : listProduct)
        {
            productIU.setContract(contractRepository.getById(contractId));
            productRepository.save(productIU);
        }
        return listProduct;
    }

    @Override
    public List<Product> insertOrUpdateProduct(List<Product> listProduct, Long applicationOpeningLcId, Contract contract){
        List<Product> listProductBD = productRepository.findAllByApplicationOpeningLcId(applicationOpeningLcId);
        List<Long> idProductRequest = listProduct.stream().map(product -> product.getId()).collect(Collectors.toList());
        List<Product> productNotInDb = listProductBD
                .stream()
                .filter(product -> !idProductRequest.contains(product.getId()))
                .collect(Collectors.toList());
        for(Product product : productNotInDb){
            productRepository.delete(product);
        }
        for(Product productIU : listProduct)
        {
            productIU.setApplicationOpeningLc(applicationOpeningLcRepository.findById(applicationOpeningLcId).get());
            productIU.setContract(contract);
            productRepository.save(productIU);
        }
        return listProduct;
    }

    @Override
    public void deleteProduct(List<Product> listProduct)
    {
        for(Product product : listProduct){
            productRepository.delete(product);
        }
    }
}
