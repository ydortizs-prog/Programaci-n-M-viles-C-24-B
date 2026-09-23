"Actúa como un desarrollador Senior en Android con Jetpack Compose y Kotlin. Necesito refactorizar la pantalla principal (HomeScreen.kt) para la aplicación de fitness 'TECSUP Fit' perteneciente a la rama 'mejora-ia'.

Requisitos técnicos y de arquitectura:
1. Estado y Búsqueda en Tiempo Real:
    - Implementa un estado reactivo (`searchQuery`) usando `remember { mutableStateOf("") }`.
    - Utiliza un `OutlinedTextField` como campo de búsqueda interactivo. Debe incluir un ícono de búsqueda al inicio (`leadingIcon`), un botón de limpieza (`trailingIcon` con icono `Clear`) que aparezca solo cuando hay texto, y esquinas redondeadas (12.dp).
    - Implementa la lógica de filtrado predictivo en tiempo real que evalúe concurrentemente el nombre de la clase (`name`), la ubicación/sala (`room`) y las palabras clave de la descripción (`description`) de forma insensible a mayúsculas y minúsculas (`ignoreCase = true`).

2. Filtrado Dinámico de Fechas:
    - Integra una fila horizontal de `FilterChip` (`LazyRow`) para alternar entre las opciones 'Hoy' y 'Esta semana', combinando este filtro con la búsqueda por texto.

3. Tarjetas de Clase (`ClassCard`) e Indicadores de Estado:
    - Para cada elemento renderizado mediante `LazyColumn`, muestra la información estructural clave: nombre de la clase, horario, sala y número de cupos disponibles.
    - Aplica estilos visuales acordes a la identidad de marca: verde primario (`GreenPrimary`) para destacados/cupos, contenedor verde (`GreenContainer`) para los íconos, y fondo gris claro (`Color(0xFFF2F2F2)`) para los contenedores de tarjeta.

4. Garantía de Legibilidad y Compatibilidad:
    - Asigna de manera explícita `color = Color.Black` a todos los componentes `Text` de títulos, encabezados y nombres de clase dentro de las tarjetas. Esto evitará que la tipografía se vuelva invisible o pase a blanco por herencia de temas en dispositivos con modo oscuro activado.
    - En el `OutlinedTextField`, fuerza `focusedTextColor = Color.Black` y `unfocusedTextColor = Color.Black`.
    - Maneja el estado de lista vacía mostrando un mensaje claro centralizado cuando no existan coincidencias con el término buscado."