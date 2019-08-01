package ma.henceforth.model;
import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;


public class Product implements Serializable {
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private ObjectId _id;
	private String label;
	private String category;
	private float price;
	private Date expirationDate;
	
	
	public Product() {

	}

	
	
	
	public Product(ObjectId _id, String label, String category, float price, Date expirationDate) {
		super();
		this._id = _id;
		this.label = label;
		this.category = category;
		this.price = price;
		this.expirationDate = expirationDate;
	}




	public String getProductId() {
		return _id.toString();
	}

	public void setProductId(ObjectId _id) {
		this._id = _id;
	}



	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
	@Override
	public String toString() {
		return "Product [_id=" + _id + ", label=" + label + ", category=" + category + ", price=" + price
				+ ", expirationDate=" + expirationDate + "]";
	}
}
