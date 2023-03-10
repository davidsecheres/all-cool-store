package com.allcoolstore.service;
import com.allcoolstore.model.Product;
import com.allcoolstore.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private double tva = 1.19;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        priceWithTva();
        return productRepository.findAll();
    }

    public List<Product> getAllProductsForAdmin() {
        return productRepository.findAll();
    }

    public void priceWithTva() {
        List<Product> productList = productRepository.findAll();
        for (Product p : productList) {
            p.setPrice(p.getPrice() * tva);
        }
    }
    public Product findById (Long id){
        Optional <Product> productOptional = productRepository.findById(id);
        Product product = productOptional.get();
        return product;
    }

    public void deleteProduct(Long id) {
        boolean productExists = productRepository.existsById(id);
        if (!productExists) {
            throw new IllegalStateException(String.format("Product with id %s does not exist.", id));
        }
        productRepository.deleteById(id);
    }

    public void updateProduct(MultipartFile file, Long id, Product product) {
        Product productToUpdate = productRepository.findById(id).orElseThrow(() ->
                new IllegalStateException(String.format("Product with id %s does not exist.", id)));
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("Not a a valid file");
        }
        try {
            product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        productRepository.save(product);
    }

    public void saveProductToDB(MultipartFile file, String name, String producer, String type, double price, int qty,
                                double bottleSize, String description) {
        Product product = new Product();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("Not a a valid file");
        }
        try {
            product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setName(name);
        product.setProducer(producer);
        product.setType(type);
        product.setPrice(price);
        product.setQty(qty);
        product.setBottleSize(bottleSize);
        product.setDescription(description);
        productRepository.save(product);
    }

    public void createProduct(MultipartFile file, String name, String producer, String type, double price, int qty
            ,double bottleSize, String description) {
        Product product = new Product();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("Not a a valid file");
        }
        try {
            product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setName(name);
        product.setType(type);
        product.setPrice(price);
        product.setQty(qty);
        product.setProducer(producer);
        product.setBottleSize(bottleSize);
        product.setDescription(description);
        productRepository.save(product);
    }

    public Product getByProductId(Long id) {
        return productRepository.findById(id).orElse(new Product());
    }
}
