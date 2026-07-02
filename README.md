# api3-mobile-testing-ecommerce

# Actividad Práctica Integradora - Mobile Testing

* **Estudiante:** Florencia Buonincontro
* **Materia:** Mobile Testing
* **Año:** 2026

---

## Descripción del proyecto
Este repositorio contiene el desarrollo de una aplicación móvil nativa para Android construida con **Kotlin Multiplataform**. El objetivo principal es dar solución a la feature requerida por el equipo de e-commerce: conectarse de forma eficiente a una API externa, recuperar el catálogo de productos en formato JSON y renderizar la información limpia y sencilla para el usuario final.

La aplicación implementa una arquitectura moderna dividida en dos grandes capas técnicas:
1. **Consumo de API:** Utiliza la librería **Retrofit 2** combinada con el conversor **Gson** para procesar de forma asríncronica la respuesta del servidor en segundo plano (`enqueue`).
2. **Interfaz de Usuario (Frontend):** Diseñado de forma declarativa con **Jetpack Compose (Material Design 3)**, empleando contenedores optimizados de memoria (`LazyColumn`) para el renderizado eficiente de listas scroleables.

---

## Especificaciones Técnicas

* **Lenguaje principal:** Kotlin 
* **Framework de IU:** Jetpack Compose (Material 3)
* **Cliente de Red:** Retrofit 2 + Gson Converter
* **Entorno de pruebas:** Emulador Pixel 8 (Android API 35)
