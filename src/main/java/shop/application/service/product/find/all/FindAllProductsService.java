package shop.application.service.product.find.all;

import shop.domain.model.product.ProductRepository;
import shop.domain.model.product.Products;

public final class FindAllProductsService {
    private ProductRepository productRepository;

    public FindAllProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Products execute() {
        return this.productRepository.all();
    }
}
