/**
 * 
 */
package com.glutunfree.api.products.image;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.glutunfree.api.products.category.Category;
import com.glutunfree.api.products.category.CategoryRepository;
import com.glutunfree.api.products.product.Product;
import com.glutunfree.api.products.product.ProductRepository;

/**
 * @author Habib Zerai
 *
 */
@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private ProductRepository ProductRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public Image create(Image image) {
		image = this.imageRepository.save(image);
		if (image.getId().startsWith("PRODUCT")) {
			Long productId = Long.valueOf(image.getId().substring(image.getId().indexOf("-") + 1));
			Product p = ProductRepository.findById(productId).get();
			p.setImage(image);
			ProductRepository.save(p);
		} else if (image.getId().startsWith("CATEGORY")) {
			Long categoryId = Long.valueOf(image.getId().substring(image.getId().indexOf("-") + 1));
			Category p = categoryRepository.findById(categoryId).get();
			p.setImage(image);
			categoryRepository.save(p);
		}
		return image;
	}

	public List<Image> findAll() {
		return this.imageRepository.findAll();
	}

	public List<Image> findPaged(int page, int size) {
		return this.imageRepository.findAll(PageRequest.of(page, size, Direction.DESC, "id")).getContent();
	}

	public Optional<Image> findOne(String id) {
		return this.imageRepository.findById(id);
	}

	public void delete(String id) {
		this.imageRepository.deleteById(id);
	}

	public Image update(Image image) {
		return this.imageRepository.save(image);
	}

}
