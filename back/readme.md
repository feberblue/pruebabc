Leer Instrucciones

- Proyecto JAVA
Se debe Ejcutar en un Eclipse o STS (Spring Tool Suite)
-importar proyecto como proyecto Maven

Ejecución desde STS
1. click derecho sobre el proyecto --> Run As --> Spring boot App
ó

desde una consola windows o linux, previamente configurado e instalado maven
1. ejecutar comando  ->     mvn package ó mvn install
2. una vez generado el jar ejecutar dicho jar con el comando
3. java -jar targer/archivojargenerado.jar
4. el jar ejecutado levantará un tomcat embebido

la aplicación ejecutara en el puerto 8080, si qiuere cambiarlo deberá cambiar
la configuracion en el archivo application.properties
añadiendo la siguiente linea 
server.port=NUMERO_PUERTO

recuerde que al cambiar el puerto la aplicacion se ejecutara sobre este en todos **sus endpoints**

en caso que se presente error al generar el jar, deshabilite todos los test java y vuelva a
intentar generar dicho jar.

para la prueba se puede usar los siguientes clientes RestFull
Insomnia o Postman


ENDPOINTS
Asesores
obtener todos los asesores (GET): http://localhost:8080/api/asesores/asesorbancario/
obtener asesor por ID (GET): http://localhost:8080/api/asesores/asesorbancario/ID_ASESOR
crear asesor (POST): http://localhost:8080/api/asesores/asesorbancario
actualizar asesor (PUT): http://localhost:8080/api/asesores/asesorbancario
eliminar asesor (DELETE): http://localhost:8080/api/asesores/asesorbancario/ID_ASESOR

Clientes
crear cliente (POST): http://localhost:8080/api/clientes/cliente
actualizar cliente (PUT): http://localhost:8080/api/clientes/cliente
borrar cliente (DELETE): http://localhost:8080/api/clientes/cliente/ID_CLIENTE
obtener un cliente (GET): http://localhost:8080/api/clientes/cliente/ID_CLIENTE
obtener todos los clientes (GET): http://localhost:8080/api/clientes/cliente

Tarjetas
Crear Tarjeta (POST): http://localhost:8080/api/tarjetas/tarjeta
actualizar Tarjeta (PUT): http://localhost:8080/api/tarjetas/tarjeta/ID_TARJETA
borrar tarjeta (DELETE): http://localhost:8080/api/tarjetas/tarjeta/ID_TARJETA
obtener todas las tarjetas (GET): http://localhost:8080/api/tarjetas/tarjeta/
obtener tarjeta x ID (GET): http://localhost:8080/api/tarjetas/tarjeta/ID_TARJETA
obtener tarjeta x Cliente: http://localhost:8080/api/tarjetas/tarjeta/cliente/ID_CLIENTE

Hostorico Tarjeta
crear historico (POST): http://localhost:8080/api/tarjetas/historico
actualizar historico (PUT): http://localhost:8080/api/tarjetas/historico/ID_HISTORICO
borrar historico (DELETE):http://localhost:8080/api/tarjetas/historico/ID_HISTORICO

la consulta de historico x tarjeta no se implementa, dado que cuando se consulta una tarjeta
ya trae dicho historico.


A continuacion se relacionan los puntos de la prueba realizados
Puntos a ser evaluados
1. (NO)Capa de presentación (Front End): Tecnología a ser utilizada: HTML5, Bootstrap, CSS3
2. (NO)Controlador (Front End): Tecnología a ser utilizada: AngularJS version 5 o superior
3. (SI)Servicio Rest (Back – End): Tecnología a ser utilizada: Spring Framework, JAX-RS, JEE.JPA,
u otro framework Java.
4. (SI)Pruebas Unitarias y Documentación: Junit, Mokito, PowerMock
5. (SI)Automatización de construcción de código: Usar Gradle o Maven
6. (SI)Pre carga de información: Cuando la aplicación inicie, se debe pre cargar información para
navegar y validar la funcionalidad
7. (SI)Base de datos: Usar una base de datos in-memory, o cualquier otra que permita estar como
dependencia en el proyecto
8. (SI)Despliegue: Docker (sugerido), WebSphere Liberty Profile, WebSphere Application Server, o
cualquier servidor Java


puntos 1 y 2, por razones ajenas al tiempo y prueba no se pudo concretar se inicio construccion en angular 7, pero no alcanzada a realizar la completitud de la prueba.

3. Se usa tecnologia SpringFramework
4. Se pueden tener acceso a ellas en la carpeta Test del proyecto
5. Se uso Maven para este caso
6. Se cuenta con la clase llamada DataLoadInit.java ubicada dentro del paquete com.bancolombia.components en dicha clase se hace la carga inicial de forma dinamica, cada vez que se inicia la aplicacion genera de forma aleatoria datos de todas las tablas en H2
7. Se usa la base de datos H2
8. Como se usó Spring Boot, al generar el jar se genera el tomcat embebido el cual despliega la aplicacion sin necesidad de tener un contenedor o servidor java.
