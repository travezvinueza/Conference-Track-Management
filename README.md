Conference Track Management
Problem Statement
You are planning a big programming conference and have received many proposals which have passed the initial screen process,
but you're having trouble fitting them into the time constraints of the day -- there are so many possibilities! So you wrote a program to do it for you.

> [!NOTE]
> Requisitos
> La conferencia tiene varios temas, cada uno de los cuales tiene una sesión por la mañana y otra por la tarde.
> Cada sesión contiene varias charlas.
> Las sesiones de la mañana comienzan a las 9 a. m. y deben finalizar a las 12 del mediodía, para el almuerzo.
> Las sesiones de la tarde comienzan a la 1 p. m. y deben finalizar a tiempo para el evento de networking.
> El evento de networking no puede comenzar antes de las 4:00 p. m. ni después de las 5:00 p. m.
> El título de ninguna charla tiene números.
> La duración de todas las charlas se expresa en minutos (no en horas) o en minutos (5 minutos).
> Los presentadores serán muy puntuales; no debe haber intervalos entre las sesiones.


[!TIP]
El diagrama de la solución se encuentra en la ruta raíz.

El contextPath de la aplicación está configurado con el valor /api.

En la raíz se encuentra el archivo postman_collection.json para ejecutarlo con Postman.

El puerto en el cual está escuchando es el 8080.

El proyecto tiene Swagger para la documentación y las pruebas funcionales. Para ingresar, redirigirse a la siguiente ruta: http://localhost:8080/api/swagger-ui/index.html#/.
