# TrimixTest

## Levantar backend server

1 - Ir a test-backend
2 - Crear base de datos en postgresql llamada "trimix_test"
3 - Ejecutar script script_db.sql para crear la tabla de personas
4 - Las credenciales de la base de datos se deben escribir en el archivo Context.xml
5 - En Context.xml colocar como ip del servidor de base de datos la ip de la máquina actual
6 - Crear imagen desde el archivo Dockerfile y ejecutar un contenedor con el puerto 8088 (el frontend, por defecto, estará realizando las peticiones a ese puerto)
