package shop.application.service.product.find.all;

import shop.application.service.product.find.ProductsResponse;
import shop.domain.bus.query.QueryHandler;

public final class FindAllProductsQueryHandler implements QueryHandler<ProductsResponse, FindAllProductsQuery> {
    private FindAllProductsService findAllProductsService;

    public FindAllProductsQueryHandler(FindAllProductsService findAllProductsService) {
        this.findAllProductsService = findAllProductsService;
    }

    @Override
    public ProductsResponse handle(FindAllProductsQuery query) throws Exception {
        return ProductsResponse.fromProducts(this.findAllProductsService.execute());
    }
}
