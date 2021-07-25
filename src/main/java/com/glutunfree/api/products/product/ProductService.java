/**
 * 
 */
package com.glutunfree.api.products.product;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 * @author Habib Zerai
 *
 */
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public static Function<String, String> like = (input) -> {
		return "%" + input + "%";
	};

	public Product create(Product product) {
		return this.productRepository.save(product);
	}

	public List<Product> findAll() {
		return this.productRepository.findAll();
	}

	public List<Product> findPaged(int page, int size) {
		return this.productRepository.findAll(PageRequest.of(page, size, Direction.DESC, "id")).getContent();
	}

	public List<Product> findfiltered(int page, int size, String name, String description, String barCode,
			int CategoryId) {
		if (barCode.length() > 0) {
			return this.productRepository.findByBarCode(barCode);
		}
		if (CategoryId > 0) {
			return this.productRepository.findByNameLikeAndDescriptionLikeAndCategoryId(like.apply(name),
					like.apply(description), Long.valueOf(CategoryId));
		}
		return this.productRepository.findByNameLikeAndDescriptionLike(like.apply(name), like.apply(description));
	}

	public Optional<Product> findOne(Long id) {
		return this.productRepository.findById(id);
	}

	public void delete(Long id) {
		this.productRepository.deleteById(id);
	}

	public Product update(Product product) {
		return this.productRepository.save(product);
	}

}
