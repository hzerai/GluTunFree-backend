/**
 * 
 */
package com.glutunfree.api.products.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Habib Zerai
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByNameLikeAndDescriptionLikeAndCategoryId(String name, String description, Long category);

	List<Product> findByNameLikeAndDescriptionLike(String name, String description);

	List<Product> findByBarCode(String barCode);

	List<Product> findByNameLike(String name);

	List<Product> findByDescriptionLike(String barCode);
}
