# SpringBoot REST Image upload template

### Backend

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   |`/upload` | Permite subir un archivo |
| GET    | `/summary` | Nos devuelve la lista de imágenes. Para cada una, `id`, `nombre de archivo` y `tamaño` |
| GET    | `/images/{id}` | Permite acceder a una imagen anteriormente subida. No la descarga como `attachment`, sino que permite visualizarla en el navegador, de modo que podemos utilizarla, por ejemplo, en nuestro html |

Cosas interesantes de este repositorio: 

- cómo hacer tests de integración para la subida de imágenes (java/net/jsrois/imageupload/IntegrationTest.java)
- Qué cabeceras HTTP hay que poner para poder utilizar las imágenes guardadas en el servidor en nuestra página web 
