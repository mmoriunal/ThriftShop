# ThriftShop

Aplicacion de escritorio construida con **JavaFX + Maven** para gestionar una tienda de ropa de segunda mano.

Incluye autenticacion de usuarios, busqueda de prendas, filtros por categoria y precio, carrito de compras, recomendaciones y publicacion de nuevas prendas.

## Tabla De Contenido

- [Descripcion General](#descripcion-general)
- [Funcionalidades Principales](#funcionalidades-principales)
- [Tecnologias Y Estructuras De Datos](#tecnologias-y-estructuras-de-datos)
- [Estructura Del Proyecto](#estructura-del-proyecto)
- [Requisitos Previos](#requisitos-previos)
- [Instalacion Y Ejecucion](#instalacion-y-ejecucion)
- [Flujo De Uso](#flujo-de-uso)
- [Formato De Datos](#formato-de-datos)
- [Limitaciones Actuales](#limitaciones-actuales)
- [Troubleshooting](#troubleshooting)
- [Mejoras Recomendadas](#mejoras-recomendadas)

## Descripcion General

ThriftShop modela un marketplace local de prendas de segunda mano.

La interfaz esta basada en vistas FXML y controladores Java, con carga de datos desde archivos de texto en recursos.

El proyecto combina conceptos de interfaz grafica con estructuras de datos implementadas manualmente para operaciones de busqueda, filtrado y ordenamiento.

## Funcionalidades Principales

- Registro e inicio de sesion de usuarios.
- Almacenamiento de contrasenas con hash SHA-256.
- Busqueda por texto (nombre y color).
- Visualizacion de resultados de busqueda.
- Ordenamiento de resultados por precio ascendente y descendente.
- Filtrado de resultados por rango de precio.
- Navegacion por categorias (tipo de prenda y color).
- Visualizacion de catalogo completo.
- Vista de detalle de prenda.
- Carrito de compras (agregar/eliminar) con subtotal.
- Recomendaciones basadas en conexiones por talla (grafo).
- Publicacion de nuevas prendas con carga de imagen.
- Perfil de usuario con actualizacion de foto.
- Historial de navegacion para retroceso entre vistas.

## Tecnologias Y Estructuras De Datos

### Stack tecnico

- Java 19
- JavaFX (`javafx-controls`, `javafx-fxml`, `javafx-graphics`)
- Maven
- JUnit 5 (dependencias declaradas)

### Estructuras implementadas en el proyecto

- `HashMapUsuario`: tabla hash personalizada para usuarios.
- `AVL` y `BST`: arboles para filtrado por precio.
- `MinHeap` y `MaxHeap`: ordenamiento de resultados por precio.
- `Graph`: recomendaciones de prendas conectadas por talla.
- `Stack` (en `Historial`): navegacion hacia atras.

## Estructura Del Proyecto

```text
ThriftShop/
	README.md
	ThriftShop/
		pom.xml
		mvnw
		mvnw.cmd
		src/
			main/
				java/com/example/demo1/
					HelloApplication.java
					LoginController.java
					Search.java
					SearchRes.java
					Carrito.java
					carritoController.java
					categoriasController.java
					verTodoController.java
					prendaViewController.java
					NewPrenda.java
					PerfilDeUsuarioController.java
					Prenda.java
					Usuario.java
					PrendaService.java
					AVL.java
					BST.java
					MinHeap.java
					MaxHeap.java
					Graph.java
					HashMapUsuario.java
					Historial.java
					HistorialEntry.java
				resources/
					prendas.txt
					usuarios.txt
					com/example/demo1/
						*.fxml
						Style.css
						images/
						imagesUser/
						imagesin/
```

## Requisitos Previos

- JDK 19 instalado.
- Variable `JAVA_HOME` apuntando al directorio del JDK 19.
- Maven Wrapper incluido en el proyecto (`mvnw` / `mvnw.cmd`).
- Sistema operativo compatible con JavaFX (Windows, Linux o macOS).

> Nota: el `pom.xml` esta configurado para compilar con `source/target` en 19.

## Instalacion Y Ejecucion

### 1) Clonar repositorio

```bash
git clone <url-del-repositorio>
cd ThriftShop/ThriftShop
```

### 2) Compilar

En Windows (PowerShell o CMD):

```powershell
.\mvnw.cmd clean compile
```

En Linux/macOS:

```bash
./mvnw clean compile
```

### 3) Ejecutar la aplicacion JavaFX

En Windows:

```powershell
.\mvnw.cmd javafx:run
```

En Linux/macOS:

```bash
./mvnw javafx:run
```

La vista inicial es `Login.fxml`, cargada desde `HelloApplication`.

## Flujo De Uso

1. Iniciar sesion con un usuario existente o crear uno nuevo.
2. Entrar a la pantalla principal de busqueda.
3. Buscar prendas por nombre (o nombre + color).
4. Ordenar y filtrar resultados por precio.
5. Abrir detalle de prenda y agregar al carrito.
6. Revisar carrito y subtotal.
7. Explorar categorias o ver todo el catalogo.
8. Publicar una prenda nueva (incluyendo imagen).
9. Editar foto de perfil desde la vista de usuario.

## Formato De Datos

### Archivo de prendas

Ruta: `src/main/resources/prendas.txt`

Formato por linea:

```text
id,nombre,color,talla,id_vendedor,precio,ruta_imagen
```

Ejemplo:

```text
1,Pantalon,Rojo,L,92913,87500,images/1.jpg
```

### Archivo de usuarios

Ruta: `src/main/resources/usuarios.txt`

Formato por linea:

```text
id_usuario,nombre,hashed_password[,ruta_imagen_perfil]
```

La contrasena se guarda con hash SHA-256.

## Limitaciones Actuales

- Persistencia basada en archivos de texto (sin base de datos).
- No hay suite de pruebas automatizadas implementada aun.
- Varias rutas de archivos estan hardcodeadas para entorno local Windows.
- No hay sistema de roles, sesiones persistentes ni control avanzado de errores.
- Parte del codigo contiene mezcla de idioma en nombres y estilo.

## Troubleshooting

### Error de `JAVA_HOME` invalido

Si aparece un error similar a:

```text
JAVA_HOME is set to an invalid directory
```

ajusta `JAVA_HOME` a la ruta real de tu JDK 19.

Ejemplo en PowerShell:

```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-19"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
```

### La aplicacion no encuentra imagenes o archivos txt

- Verifica que `prendas.txt`, `usuarios.txt` y carpetas de imagenes existan en `src/main/resources`.
- Revisa rutas relativas usadas al crear nuevas prendas o subir foto de perfil.

### Error al cargar vistas FXML

- Confirma que los nombres de archivo coincidan exactamente (mayusculas/minusculas).
- Revisa que el controlador declarado en cada FXML exista y compile.

## Mejoras Recomendadas

- Migrar persistencia a una base de datos (H2, SQLite o PostgreSQL).
- Agregar pruebas unitarias para estructuras de datos y servicios.
- Centralizar rutas de recursos para evitar hardcoding.
- Mejorar validaciones de formularios y manejo de excepciones.
- Internacionalizacion (i18n) de textos de interfaz.
- Refactorizar nomenclatura y estilo para consistencia del codigo.

---

Si deseas, puedo crear una segunda version del README orientada a presentacion academica (con seccion de complejidad algoritmica y justificacion de estructuras) o una version para despliegue tecnico del proyecto.

