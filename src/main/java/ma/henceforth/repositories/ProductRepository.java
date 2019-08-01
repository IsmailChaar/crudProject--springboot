package ma.henceforth.repositories;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ma.henceforth.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product,ObjectId> {
	
	public void deleteByproductId(String productId);
	

}
