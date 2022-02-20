# SpringBoot REST Image upload template

### Backend

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   |`/upload` | Permite subir un archivo |
| GET    | `/summary` | Nos devuelve la lista de imágenes. Para cada una, `id`, `nombre de archivo` y `tamaño` |
| GET    | `/images/{id}` | Descarga una imagen |

Cosas interesantes de este repositorio: 

- cómo hacer tests de integración para la subida de imágenes (java/net/jsrois/imageupload/IntegrationTest.java)
- Qué cabeceras HTTP hay que poner para poder utilizar las imágenes guardadas en el servidor en nuestra página web 