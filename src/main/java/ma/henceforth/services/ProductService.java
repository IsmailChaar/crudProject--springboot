package ma.henceforth.services;


import ma.henceforth.model.Product;
import ma.henceforth.repositories.ProductRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProductService {
	
	@Autowired 
    private final ProductRepository productRepository;

  
    public ProductService(ProductRepository productRespository) {
		this.productRepository = productRespository;
	}

	public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(String id) {
        return productRepository.findById(new ObjectId(id));
    }

    public Product save(Product product) {
    	System.out.println(product.toString());
        return productRepository.save(product);
    }

    public void deleteById(String id) {
        productRepository.deleteById(new ObjectId(id));
    }
}