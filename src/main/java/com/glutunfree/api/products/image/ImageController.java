/**
 * 
 */
package com.glutunfree.api.products.image;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.glutunfree.api.products.product.ProductRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Habib Zerai
 *
 */
@RestController
@RequestMapping("/api/image")
@Api(tags = "categories")
public class ImageController {

	@Autowired
	private ImageService imageService;

	@PostMapping()
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 500, message = "Something went wrong server side"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 422, message = "Username is already in use") })
	public Image create(@RequestBody Image image) {
		return imageService.create(image);
	}

	@PostMapping(path = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA })
	public Image imageForm(@RequestParam MultiValueMap<String, String> paramMap) {
		Image image = new Image();
		String id = ((ArrayList<String>) paramMap.get("id")).get(0);
		String name = ((ArrayList<String>) paramMap.get("name")).get(0);
		String type = ((ArrayList<String>) paramMap.get("type")).get(0);
		String base64 = ((ArrayList<String>) paramMap.get("base64")).get(0);
		image.setUri(base64);
		image.setId(id);
		image.setName(name);
		image.setType(type);
		return imageService.create(image);
	}

	@GetMapping()
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 500, message = "Something went wrong server side"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 422, message = "Username is already in use") })
	public List<Image> find(@RequestParam(name = "page", defaultValue = "-1") int page,
			@RequestParam(name = "size", defaultValue = "-1") int size) {
		if (page > 0 && size > 0) {
			return this.imageService.findPaged(page, size);
		}
		return this.imageService.findAll();
	}

	@GetMapping("/{id}")
	public Image findOne(@PathVariable("id") String id) {
		Optional<Image> optional = imageService.findOne(id);
		if (!optional.isPresent()) {
			return null;
		}
		return optional.get();
	}

	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable("id") String id) {
		imageService.delete(id);
		return true;
	}

	@PutMapping()
	public Image update(@RequestBody Image image) {
		return imageService.update(image);
	}

}
