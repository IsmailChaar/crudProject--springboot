package ma.henceforth.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.henceforth.model.Product;
import ma.henceforth.services.ProductService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping({"/products","/edit-product"})


public class ProductController {
	
    private final ProductService productService;

    public ProductController(ProductService productService) {
		this.productService = productService;
	}

    @GetMapping("/")
    public String list(){
        return "products";
    }
    

	@GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
    	product.setProductId(ObjectId.get());
        return ResponseEntity.ok(productService.save(product));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable String id) {
        Optional<Product> stock = productService.findById(id);
        if (!stock.isPresent()) {
        	System.err.println("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable String id, @Valid @RequestBody Product product) {
        
    	System.out.println(id + "-------" + product.toString());
    	
    	if (!productService.findById(id).isPresent()) {
            System.err.println("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable String id) {
        if (!productService.findById(id).isPresent()) {
        	System.err.println("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        productService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
