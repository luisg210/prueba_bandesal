# Prueba bandesal - Luis Gerardo Henríquez Rodríguez
App web sobre blogs y lectores con login y registro. Prueba técnica.

# Características
- Tecnologías utilizadas 
    - Java Spring boot 3
    - Thymeleaf
    - Jpa
    - Spring security
    - Lombok
    - Validation api
    - Postgres
    - Docker
    - Maven
- Funcionalidades
  - Mantenimiento de las tablas blog y readers.
  - Listado de blogs con sus users.
  - Login y registro de usuario con validaciones.
  - Endpoint rest para obtener los blogs con auth básica.

# Requisitos
- Java 17
- Maven
- Docker (si se ejecuta de esta forma)
- Postman

# Instalación y ejecución
- Ejecución mediante Docker: 
  - Construir la imagen docker: En la carpeta que se clono, encontrar el archivo Dockerfile y ejecutar el siguiente comando
   > docker build -t app:latest .
  - Ejecutar contenedor mediante la imagen docker, cambiar las variables de entorno por las que se compartieron en el correo: 
  > docker run -d -p 9090:9090 --name proyecto_luis -e DB_URL={URL} -e DB_USR={usr} -e DB_PWD={pwd} app
- Ejecución por IDE: Importar el proyecto clonado desde GitHub, crear las variables de entorno y ejecutar el proyecto.

# Uso
- Version mvc:
  - Local: Ingresar a la siguiente URL = http://127.0.0.1:9090/ y registrar un usuario.
  - Remoto: Ingresar al siguiente Link = https://prueba-bandesal.onrender.com/ y registrar un usuario.
- Endpoint rest: Ingresar desde postman y enviar una seguridad básica con el usuario creado en la version MVC, También comparto la collection de postman. 
  - Local: Ingresar a la siguiente URL = http://127.0.0.1:9090/api/v1/blogs/
  - Remoto: Ingresar al siguiente Link = https://prueba-bandesal.onrender.com/api/v1/blogs/
