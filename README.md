# Gimnasio


## Contexto
Prototipo de aplicación de un gimnasio, donde los clientes, registran su rendimiento en este.

## Diseño Original
![DiseñoGimnasio](https://github.com/user-attachments/assets/4a887a55-c823-46e3-b781-d1e80284327b)


## Diseño Android Studio y ejecucion
Pantalla 1
![GimnasioBDFragmentos1](https://github.com/user-attachments/assets/0d946d1c-fea5-4a09-95d6-304a44a7125e)


Pantalla 2
![GimnasioBDFragmentos2](https://github.com/user-attachments/assets/e95f0480-d0d4-480c-8ab3-d34b68c4694d)


Pantalla 3
![GimnasioBDFragmentos3](https://github.com/user-attachments/assets/7e7d5ff9-0082-4ed8-9f21-fd2a16e31aba)

## Versiones
Api de Android:35
Version Gradle:8.10.2

## Conclusiones
Los puntos requeridos en la rúbrica se pueden probar de la siguiente manera:
-ViewBiding: Se observa en las 3 actividades
-Acceso a base de datos: Lee de la BD el valor introducido en población y Nombre en la pantalla de Login, y si no existe, los agrega a la BD
-Fragmentos: Se han utilizado para el logo, el menú y el título de la APP
-Permisos: En la actividad 3 (Se accede clickando sobre una máquina de la actividad 2) hay un boton de "activar QR" que requiere permiso de camara
-Rest:En el dashboard de la actividad 2 se muestra la temperatura y precipitaciones en Parla, obtenidas de Retrofit
-MVVM: En el footer de la actividad 2 cargamos el nombre del usuario de BD mediante el observer del ViewModel
-Clean: Se ha aplicado a la organización de los paquetes, para que, por ejemplo al hacer las operaciones de BD, la actividad llame al viewModel, este al caso de uso, este al Repository y este al Provider (DAO)


