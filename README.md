# CRUD_mensajeria

CRUD de mensajeria ejercicio del curso de Java SE Persistencia de datos, en este proyecto se realizaron las conexiones a 
una base de datos MySql en Docker.

El proyecto cuenta con las 4 operaciones básicas:
* Crear mensajeDTO (Insert) 
* Leer mensajes (Select)
* Actualizar mensajeDTO (Update)
* Eliminar Mensaje (Delete)

---
## Incriptado de password con:
* **BCryptPasswordEncoder**
* Anteriormente con **md5Hex**
---
## Ejemplo de requests segun su enpoint:

Crear Usuario:
Enpoint --> localhost:8080/usuario

```json
{
    "userName" : "sbernal",
    "correo" : "sbernal@gmail.com",
    "nombreCompleto" : "soraimar",
    "password" : "123"
}
```


