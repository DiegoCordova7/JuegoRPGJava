# Proyecto RPG

Este proyecto fue encargado por el Dr. Miguel García y está desarrollado con una arquitectura por capas, separando la entrada del usuario, la lógica de negocio y el procesamiento de datos.

## Arquitectura

El sistema está basado en una arquitectura modular y escalable, utilizando paquetes específicos para cada área, lo que facilita su expansión y mantenimiento. La base de datos utilizada es SQLite, elegida por su simplicidad y eficiencia para este proyecto.

## Desarrollo de personajes

Para crear una nueva clase de personaje, esta deberá heredar de la clase abstracta `APersonajes`. Además, se deben agregar las características específicas del personaje, como su tipo de daño y defensa.

## Habilidades

Las habilidades deben ser registradas en la base de datos. Si una habilidad incluye efectos, estos deberán añadirse tanto a la tabla correspondiente como al ENUM `Efectos` y al estado del personaje.

## Equipamiento

Las armas/armaduras y accesorios deberan ser fragmentadas en la base de datos, almacenando solo los datos necesarios en sus tablas correspondientes.

## Futuras Mejoras

Terminar el mapeo de la base de datos, terminar la logica de equipamiento, pasar los datos a la DB, crear el sistema de combate.

## Desarrollado por

Diego Cordova.
