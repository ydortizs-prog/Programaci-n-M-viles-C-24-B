# Sistema de Registro de Préstamos - Biblioteca

Programa en Kotlin para registrar préstamos de libros por consola.

## Funcionalidad

- Registra: título del libro, tipo de usuario, fecha de préstamo, fecha de entrega acordada y fecha de devolución real.
- Permite registrar varios préstamos en una misma ejecución.
- Calcula automáticamente el estado del préstamo:
    - "Devuelto a tiempo" si no hubo atraso.
    - "Devuelto con X día(s) de atraso" si se entregó después de la fecha acordada.
- Muestra el detalle de la multa día por día cuando hay atraso, con el monto acumulado y el total.

## Formato de fechas

Las fechas se ingresan en formato `dd/MM/yyyy` (ejemplo: `15/03/2026`).

## Multa

Valor de ejemplo: S/ 1.00 por cada día de atraso.

## Cómo ejecutar

El programa se ejecuta desde la función `main()` en `Biblioteca.kt`, como aplicación de consola Kotlin (no como Activity de Android).

## Conceptos de POO aplicados

- Clase (`Prestamo`)
- Atributos (título, tipo de usuario, fechas)
- Métodos (`calcularEstado()`, `diasDeAtraso()`, `mostrarDetalleMulta()`)
- Objetos (instancias de `Prestamo` creadas en cada registro)
