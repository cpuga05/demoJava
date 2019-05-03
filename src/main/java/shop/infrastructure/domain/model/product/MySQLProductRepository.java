package shop.infrastructure.domain.model.product;

import shop.domain.model.product.Product;
import shop.domain.model.product.ProductId;
import shop.domain.model.product.ProductRepository;
import shop.domain.model.product.Products;
import shop.domain.model.shared.Money;
import shop.domain.model.shared.Unit;
import shop.infrastructure.persistence.mysql.MySQL;
import sun.reflect.ReflectionFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public final class MySQLProductRepository implements ProductRepository {
    private MySQL mysql;

    public MySQLProductRepository(MySQL mysql) {
        this.mysql = mysql;
    }

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO products VALUES ('" + product.id() + "', '" + product.name() + "', '" + product.price() + "', " + product.offerUnits().amount() + ", '" + product.offerPrice() + "')";

        this.mysql.execute(sql);
    }

    @Override
    public Product ofId(ProductId id) {
        String sql = "SELECT * FROM products WHERE id = '" + id.id() + "'";

        List<Map<String, String>> products = this.mysql.query(sql);

        for (Map<String, String> product: products){
            return generateProduct(product.get("id"), product.get("name"), product.get("price"), product.get("offer_units"), product.get("offer_price"));
        }

        return null;
    }

    @Override
    public Products all() {
        String sql = "SELECT * FROM products";

        List<Map<String, String>> products = this.mysql.query(sql);
        Products result = new Products();

        for (Map<String, String> product: products){
            result.add(generateProduct(product.get("id"), product.get("name"), product.get("price"), product.get("offer_units"), product.get("offer_price")));
        }

        return result;
    }

    private Product generateProduct(String id, String name, String price, String offerUnits, String offerPrice) {
        try {
            Class<?> myClass = Product.class;

            ReflectionFactory reflection = ReflectionFactory.getReflectionFactory();
            Constructor<Object> constructor = (Constructor<Object>) reflection.newConstructorForSerialization(myClass, Object.class.getDeclaredConstructor(new Class[0]));
            Product o = (Product) constructor.newInstance(new Object[0]);

            Field field = myClass.getDeclaredField("id");
            boolean accessible = field.isAccessible();

            field.setAccessible(true);
            field.set(o, new ProductId(id));
            field.setAccessible(accessible);

            field = myClass.getDeclaredField("name");
            accessible = field.isAccessible();

            field.setAccessible(true);
            field.set(o, name);
            field.setAccessible(accessible);

            field = myClass.getDeclaredField("price");
            accessible = field.isAccessible();

            field.setAccessible(true);
            field.set(o, Money.fromString(price));
            field.setAccessible(accessible);

            field = myClass.getDeclaredField("offerUnits");
            accessible = field.isAccessible();

            field.setAccessible(true);
            field.set(o, new Unit(Integer.parseInt(offerUnits)));
            field.setAccessible(accessible);

            field = myClass.getDeclaredField("offerPrice");
            accessible = field.isAccessible();

            field.setAccessible(true);
            field.set(o, Money.fromString(offerPrice));
            field.setAccessible(accessible);

            return o;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
}
