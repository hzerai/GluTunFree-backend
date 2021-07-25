/**
 * 
 */
package com.glutunfree.api.products.category;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 * @author Habib Zerai
 *
 */
@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category create(Category category) {
		return this.categoryRepository.save(category);
	}

	public List<Category> findAll() {
		return this.categoryRepository.findAll();
	}

	public List<Category> findPaged(int page, int size) {
		return this.categoryRepository.findAll(PageRequest.of(page, size, Direction.DESC, "id")).getContent();
	}

	public Optional<Category> findOne(Long id) {
		return this.categoryRepository.findById(id);
	}

	public void delete(Long id) {
		this.categoryRepository.deleteById(id);
	}

	public Category update(Category category) {
		return this.categoryRepository.save(category);
	}

}
