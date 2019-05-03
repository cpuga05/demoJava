package shop.domain.model.product;

import shared.domain.Collection;

import java.util.List;

public class Products extends Collection<Product> {
    public Products() {
        super();
    }

    public Products(Product[] items) {
        super(items);
    }

    public Products(List<Product> items) {
        super(items);
    }
}
