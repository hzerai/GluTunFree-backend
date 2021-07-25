/**
 * 
 */
package com.glutunfree.api.products.category;

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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Habib Zerai
 *
 */
@RestController
@RequestMapping("/api/category")
@Api(tags = "categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping()
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 500, message = "Something went wrong server side"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 422, message = "Username is already in use") })
	public Category create(@RequestBody Category category) {
		return categoryService.create(category);
	}

	@GetMapping()
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 500, message = "Something went wrong server side"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 422, message = "Username is already in use") })
	public List<Category> find(@RequestParam(name = "page", defaultValue = "-1") int page,
			@RequestParam(name = "size", defaultValue = "-1") int size) {
		if (page > 0 && size > 0) {
			page--;
			return this.categoryService.findPaged(page, size);
		}
		return this.categoryService.findAll();
	}

	@GetMapping("/{id}")
	public Category findOne(@PathVariable("id") Long id) {
		Optional<Category> optional = categoryService.findOne(id);
		if (!optional.isPresent()) {
			return null;
		}
		return optional.get();
	}

	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable("id") Long id) {
		categoryService.delete(id);
		return true;
	}

	@PutMapping()
	public Category update(@RequestBody Category category) {
		return categoryService.update(category);
	}

}
