# Demo JAVA
Esta demo es para enseñar la arquitectura de un caso de uso de un carrito de compra.

Se utiliza DDD con arquitectura hexagonal y CQRS para la ejecución de casos de usos.

## Ejecución
- Clonar repositorio
- Descargar las dependencias por maven
- Ir al paquete src/main/java/shop/infrastructure/ui/console
    - Archivo Kernel.java
        - Elegir tipo de repositorio para productos:
            - InMemoryProductRepository
            - MySQLProductRepository (configurar MySQL)
    - Archivo Init.java
        - Descomentar las líneas en la función init() para crear los productos (recomendable para repositorio en memorio o primera ejecución en MySQL)

## Resumen
### DDD
Es tan amplio... que no me da para resumirlo. Se han añadido, Value Objects, pratrón Repository, lenguaje obicuo...

### Arquitectura hexagonal
Se dividen en tres capas:
- Dominio: responsable de nuestra lógica de negocio
- Aplicación: los casos de usos se ejecutarán y llamarán a la lógica
- Infrastructura: todo aquello que pertenezca a un tercero, implementaciones concretas.

### CQRS
Hay dos tipos, **Commands** y **Queries**.

- Los commands sirven para ejecutar algo que va a tener un _side effect_ y no retornan nada. (Crear un artículo, añadir un producto al carrito...)
- Las queries es para hacer consultas y retornan aquello pedido. (Ver el carrito, consultar algún producto...)

Ambos se tiran a un bus, que simplemente hace que relacionar esa comando con su manejador.

### A tener en cuenta
Hay que vigilar que que petición (command o query) solo se modificará un único agregado (entidad). Para poder editar varios agregados, se lanzan eventos de dominios.

Estos serán lanzados a un "publicador" que repartirá a los escuchantes que se ha producido tal efecto y actuarán acorde de ellos. 