package shop.application.service.product.create;

import com.sun.javaws.exceptions.InvalidArgumentException;
import shop.domain.model.product.Product;
import shop.domain.model.product.ProductAlreadyExists;
import shop.domain.model.product.ProductId;
import shop.domain.model.product.ProductRepository;
import shop.domain.model.shared.Money;
import shop.domain.model.shared.Unit;

public final class CreateProductService {
    private ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void execute(ProductId id, String name, Money price, Unit offerUnits, Money offerPrice) throws ProductAlreadyExists, InvalidArgumentException {
        if (this.productRepository.ofId(id) != null) {
            throw new ProductAlreadyExists(id);
        }

        Product product = new Product(id, name, price, offerUnits, offerPrice);

        this.productRepository.save(product);
    }
}
