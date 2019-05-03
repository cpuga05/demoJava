package shop.infrastructure.ui.console;

import shared.application.event.subscribe.SubscribeDomainEventService;
import shared.infrastructure.domain.event.LoggerDomainEventSubscriber;
import shop.application.service.cart.removeproduct.RemoveProductCartCommand;
import shop.application.service.cart.removeproduct.RemoveProductCartCommandHandler;
import shop.application.service.cart.removeproduct.RemoveProductCartService;
import shop.application.service.cart.take.TakeCartCommand;
import shop.application.service.cart.take.TakeCartCommandHandler;
import shop.application.service.cart.take.TakeCartService;
import shop.application.service.cart.takeproduct.TakeProductCartCommand;
import shop.application.service.cart.takeproduct.TakeProductCartCommandHandler;
import shop.application.service.cart.takeproduct.TakeProductCartService;
import shop.application.service.cart.view.ViewCartQuery;
import shop.application.service.cart.view.ViewCartQueryHandler;
import shop.application.service.cart.view.ViewCartService;
import shop.application.service.product.create.CreateProductCommand;
import shop.application.service.product.create.CreateProductCommandHandler;
import shop.application.service.product.create.CreateProductService;
import shop.application.service.product.find.all.FindAllProductsQuery;
import shop.application.service.product.find.all.FindAllProductsQueryHandler;
import shop.application.service.product.find.all.FindAllProductsService;
import shop.application.service.product.find.one.FindOneProductQuery;
import shop.application.service.product.find.one.FindOneProductQueryHandler;
import shop.application.service.product.find.one.FindOneProductService;
import shop.domain.bus.command.Command;
import shop.domain.bus.command.CommandBus;
import shop.domain.bus.query.Query;
import shop.domain.bus.query.QueryBus;
import shop.domain.model.cart.CartRepository;
import shop.domain.model.product.ProductRepository;
import shop.infrastructure.domain.bus.command.SyncCommandBus;
import shop.infrastructure.domain.bus.query.SyncQueryBus;
import shop.infrastructure.domain.model.cart.InMemoryCartRepository;
import shop.infrastructure.domain.model.product.InMemoryProductRepository;
import shop.infrastructure.domain.model.product.MySQLProductRepository;
import shop.infrastructure.persistence.mysql.MySQL;

public class Kernel {
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    private CommandBus commandBus;
    private QueryBus queryBus;

    public Kernel() {
        MySQL mysql = new MySQL("localhost:3306", "root", "rootroot", "demojava");

        this.cartRepository = new InMemoryCartRepository();
        //this.productRepository = new InMemoryProductRepository();
        this.productRepository = new MySQLProductRepository(mysql);

        this.commandBus = new SyncCommandBus();
        this.queryBus = new SyncQueryBus();

        this.bindCommands();
        this.bindQueries();
        this.bindEvents();
    }

    private void bindCommands() {
        this.commandBus.bind(
                CreateProductCommand.class.getName(),
                new CreateProductCommandHandler(new CreateProductService(this.productRepository))
        );

        this.commandBus.bind(
                TakeCartCommand.class.getName(),
                new TakeCartCommandHandler(new TakeCartService(this.cartRepository))
        );

        this.commandBus.bind(
                TakeProductCartCommand.class.getName(),
                new TakeProductCartCommandHandler(new TakeProductCartService(this.cartRepository, this.productRepository))
        );

        this.commandBus.bind(
                RemoveProductCartCommand.class.getName(),
                new RemoveProductCartCommandHandler(new RemoveProductCartService(this.cartRepository, this.productRepository))
        );
    }

    private void bindQueries() {
        this.queryBus.bind(
                FindOneProductQuery.class.getName(),
                new FindOneProductQueryHandler(new FindOneProductService(this.productRepository))
        );

        this.queryBus.bind(
                FindAllProductsQuery.class.getName(),
                new FindAllProductsQueryHandler(new FindAllProductsService(this.productRepository))
        );

        this.queryBus.bind(
                ViewCartQuery.class.getName(),
                new ViewCartQueryHandler(new ViewCartService(this.cartRepository))
        );
    }

    private void bindEvents() {
        (new SubscribeDomainEventService()).execute(new LoggerDomainEventSubscriber());
    }

    public <C extends Command> void dispatch(C command) throws Exception {
        this.commandBus.dispatch(command);
    }

    public <R, Q extends Query> R ask(Q query) throws Exception {
        return this.queryBus.ask(query);
    }

    public static Kernel init() {
        return new Kernel();
    }
}
