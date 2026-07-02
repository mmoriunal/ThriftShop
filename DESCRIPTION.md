# ThriftShop Portfolio README

## Elevator Pitch
ThriftShop is a desktop marketplace prototype for second-hand clothing, built with JavaFX and Maven. It focuses on practical product discovery and shopping flows while showcasing custom data structure implementations in a real UI-driven app.

## What This Project Demonstrates
- End-to-end Java desktop development with JavaFX (FXML + Controllers).
- User authentication flow with hashed passwords (SHA-256).
- Search and product discovery UX.
- Sorting and filtering with custom algorithm/data-structure components.
- Cart experience with subtotal calculation and item management.
- Feature-oriented architecture linking UI, services, and model layers.

## Key Features
- Login and registration.
- Product search by name and color.
- Price sorting (ascending/descending).
- Price range filtering.
- Category browsing by garment type and color.
- Product detail pages.
- Cart add/remove and subtotal updates.
- Basic recommendation panel using graph-based connections by size.
- New product publishing with image upload.
- User profile with profile picture update.

## Technical Highlights
- Language: Java 19
- UI: JavaFX (FXML)
- Build Tool: Maven (with wrapper)
- Test Dependency: JUnit 5 (declared)
- Data Persistence: Flat files in resources (`prendas.txt`, `usuarios.txt`)

### Custom Data Structures Used
- `HashMapUsuario`: custom hash map for user lookup.
- `AVL` and `BST`: tree-based price filtering logic.
- `MinHeap` and `MaxHeap`: price sorting in search results.
- `Graph`: recommendation relationships between products.
- `Stack` (`Historial`): screen navigation history.

## Architecture Snapshot
- Entry point: `HelloApplication`
- UI controllers: `LoginController`, `Search`, `SearchRes`, `carritoController`, `categoriasController`, `verTodoController`, `prendaViewController`, `NewPrenda`, `PerfilDeUsuarioController`
- Domain models: `Prenda`, `Usuario`
- Services/utilities: `PrendaService`, `FiltradoPrecioService`, `Carrito`, `Historial`

## Quick Start
From the project module folder:

```powershell
cd ThriftShop
.\mvnw.cmd clean compile
.\mvnw.cmd javafx:run
```

If `JAVA_HOME` fails, point it to a valid JDK 19 installation.

## Scope And Constraints
- Built as an educational/prototype application.
- Uses file-based storage instead of a database.
- Some file paths are environment-dependent and can be further standardized.

## Why It Is Portfolio-Relevant
This project is a strong portfolio piece because it combines:
- Functional product UX.
- Algorithmic reasoning in a practical context.
- State and navigation management in desktop UI.
- Clean separation of responsibilities across models, controllers, and services.

## Next Up (If Extended)
- Migrate persistence to SQLite/PostgreSQL.
- Add unit and integration tests.
- Normalize resource-path handling for cross-platform compatibility.
- Improve validation and error handling UX.
