package service.impl;

import dao.ProductDAO;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    @Transactional
    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return productDAO.getAll();
    }

    @Override
    @Transactional
    public void edit(Product product) {
        productDAO.edit(product);
    }

    @Override
    @Transactional
    public Optional<Product> getById(int id) {
        return productDAO.getById(id);
    }

    @Override
    @Transactional
    public void deleteProduct(Integer id) {
        productDAO.deleteProduct(id);
    }
}
