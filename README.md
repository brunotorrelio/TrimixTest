# TrimixTest

### Tecnologías utilizadas

Base de Datos: PostgreSQL 9.4

Backend: - Java 8 
         - Spring Framework 4
         - Hibernate 5 

Frontend: - Vue.js 2

##Instalación y configuración
### Configurar base de datos

1 - Ir a TestBackend

2 - Crear base de datos en postgresql llamada "trimix_test"

3 - Ejecutar script script_db.sql para crear la tabla de personas

4 - Las credenciales de la base de datos se deben escribir en el archivo context.xml

5 - En context.xml colocar como ip del servidor de base de datos la ip de la máquina actual (no 127.0.0.1)

### Levantar backend server

1 - En TestBackend ejecutar $ mvn clean install para generar el archivo war de la aplicación

2 - Crear imagen desde el archivo Dockerfile y ejecutar un contenedor con el puerto 8088 (el frontend, por defecto, estará realizando las peticiones a ese puerto)

3 - En en caso de que no fuera posible levantar el contenedor:
  
  3.1 - En Eclipse IDE importar proyecto maven existente, seleccionar test-backend.
  
  3.2 - $ mvn clean install 
  
  3.3 - Agregar un sevidor apache tomcat, preferentemente 8.5
  
  3.4 - Copiar los datos del pool de conexión del archivo context.xml de la carpeta test-backend en el context.xml del servidor apache tomcat.
  
  3.5 - Modificar el puerto del servidor para que utilice el puerto 8088
  
  3.6 - Run Server al proyecto TestBackend
  
### Levantar frontend

1 - Ir a test-frontend desde la terminal

2 - Ejecutar npm install (se debe tener instalado nodejs previamente)

3 - Una vez realizada la instalación levantar el servidor del frontend: npm run server


