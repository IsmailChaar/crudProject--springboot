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
@RequestMapping("/products")


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

/* @RestController

public class ProductController {


    @Autowired
    private ProductRepository repository;


    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getAllProducts() {
    	System.out.println(repository.findAll().toString());
        return repository.findAll();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Product> getProductById(@PathVariable("id") String id) {
        return repository.findById(new ObjectId(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateProductById(@PathVariable("id") ObjectId id, @Valid @RequestBody Product product) {
        //product.setProductId(new ObjectId());
        repository.save(product);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product addNewProduct(@Valid @RequestBody Product product) {
    	product.setProductId(ObjectId.get());
    	System.out.println(product.toString());
        repository.save(product);
        return product;
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)

    public void deleteProductByID(@PathVariable("id") String id) {
    	 System.out.println("delete " + id);
    	 repository.deleteById(new ObjectId(id));
    }
    
    
    
    @GetMapping("/add")
    public Product addProduct() {
    	Product product = new Product("blabla", "ategory", 1234, new Date());
    	
    	repository.save(product);
    	return product;
    }
    
    @GetMapping("/delete")
    public void delete() {
    	String id = "5d3f0b0ef8b8e410908c2ef3";
    	
    	repository.deleteById(new ObjectId(id));
    }
    
    

}*/
