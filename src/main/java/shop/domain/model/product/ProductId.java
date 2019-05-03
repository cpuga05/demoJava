package shop.domain.model.product;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.UUID;

public final class ProductId {
    private String id;

    public ProductId(String id) throws InvalidArgumentException {
        this.ensureValidId(id);

        this.id = id;
    }

    private void ensureValidId(String id) throws InvalidArgumentException {
        if (id.length() == 0) {
            throw new InvalidArgumentException(new String[]{"The cartId " + id + " not is valid."});
        }
    }

    public static ProductId random() throws InvalidArgumentException {
        return new ProductId(UUID.randomUUID().toString());
    }

    public boolean equals(ProductId productId) {
        return this.id().equals(productId.id());
    }

    public String id() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.id();
    }
}
