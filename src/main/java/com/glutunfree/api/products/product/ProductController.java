/**
 * 
 */
package com.glutunfree.api.products.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

/**
 * @author Habib Zerai
 *
 */
@RestController
@RequestMapping("/api")
@Api(tags = "products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/product")
	public Product create(@RequestBody Product product) {
		return productService.create(product);
	}

	@GetMapping("/product")
	public List<Product> find(@RequestParam(name = "page", defaultValue = "-1") int page,
			@RequestParam(name = "size", defaultValue = "-1") int size) {
		if (page > 0 && size > 0) {
			page--;
			return this.productService.findPaged(page, size);
		}
		return this.productService.findAll();
	}

	@GetMapping("/productsfiltered")
	public List<Product> findFiltered(@RequestParam(name = "page", defaultValue = "-1") int page,
			@RequestParam(name = "size", defaultValue = "-1") int size,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "description", defaultValue = "") String description,
			@RequestParam(name = "barCode", defaultValue = "") String barCode,
			@RequestParam(name = "category", defaultValue = "-1") int categoryId) {
		if (name.length() > 0 || description.length() > 0 || barCode.length() > 0 || categoryId > 0) {
			return this.productService.findfiltered(page, size, name, description, barCode, categoryId);
		}
		if (page > 0 && size > 0) {
			page--;
			return this.productService.findPaged(page, size);
		}
		return this.productService.findAll();
	}

	@GetMapping("/product/{id}")
	public Product findOne(@PathVariable("id") Long id) {
		Optional<Product> optional = productService.findOne(id);
		if (!optional.isPresent()) {
			return null;
		}
		return optional.get();
	}

	@DeleteMapping("/product/{id}")
	public boolean delete(@PathVariable("id") Long id) {
		productService.delete(id);
		return true;
	}

	@PutMapping()
	public Product update(@RequestBody Product product) {
		return productService.update(product);
	}

}
