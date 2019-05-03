package shop.application.service.product.find.one;

import shop.domain.model.product.*;

public final class FindOneProductService {
    private ProductFinder productFinder;

    public FindOneProductService(ProductRepository productRepository) {
        this.productFinder = new ProductFinder(productRepository);
    }

    public Product execute(ProductId id) throws ProductNotExists {
        return this.productFinder.execute(id);
    }
}
