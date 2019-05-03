package shop.domain.model.product;

public final class ProductFinder {
    private ProductRepository productRepository;

    public ProductFinder(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(ProductId id) throws ProductNotExists {
        Product product = this.productRepository.ofId(id);

        if (product == null) {
            throw new ProductNotExists(id);
        }

        return product;
    }
}
