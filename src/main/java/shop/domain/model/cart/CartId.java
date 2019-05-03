package shop.domain.model.cart;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.UUID;

public final class CartId {
    private String id;

    public CartId(String id) throws InvalidArgumentException {
        this.ensureValidId(id);

        this.id = id;
    }

    private void ensureValidId(String id) throws InvalidArgumentException {
        if (id.length() == 0) {
            throw new InvalidArgumentException(new String[]{"The cartId " + id + " not is valid."});
        }
    }

    public static CartId random() throws InvalidArgumentException {
        return new CartId(UUID.randomUUID().toString());
    }

    public boolean equals(CartId cartId) {
        return this.id().equals(cartId.id());
    }

    public String id() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.id();
    }
}
