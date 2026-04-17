package com.disan.ecom_project.Controller;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.disan.ecom_project.Model.Product;
import com.disan.ecom_project.Service.ProductService;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
	@Autowired
	private ProductService service;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		return new ResponseEntity<>(service.getAllProducts(),HttpStatus.OK);
	}
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable int id) {

	    Optional<Product> product = Optional.of(service.findById(id));

	    if (product.isPresent()) {
	        return new ResponseEntity<>(product.get(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	@PostMapping("/product")
	public ResponseEntity<?> addProduct(@RequestPart Product product,@RequestPart MultipartFile imageFile){
		try {
		Product product1=service.addProduct(product,imageFile);
		return new ResponseEntity<>(product1,HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/product/{productId}/image")
	public ResponseEntity<?> getImageByProductId(@PathVariable int productId){
		Product product=service.findById(productId);
		byte[] imageFile=product.getImageDate();
		
		return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageFile);
	}
	@PutMapping("/product/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable int id,@RequestPart Product product,@RequestPart MultipartFile imageFile){
		Product product1=null;
		try {
			product1=service.updateProduct(id,product,imageFile);
		}
		catch(IOException e) {
			return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
		}
		if(product1!=null) {
			return new ResponseEntity<>("updated",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("failed to update",HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id){
		Product product=service.findById(id);
		if(product!=null) {
			service.deleteProduct(id);
			return new ResponseEntity<>("Deleted",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("product not found",HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/products/search")
	public ResponseEntity<?> searchProducts(@RequestParam String keyword){
		System.out.println("search with"+keyword);
		List<Product> product=service.searchProducts(keyword);
		return  new ResponseEntity<>(product,HttpStatus.OK);
	}
}
