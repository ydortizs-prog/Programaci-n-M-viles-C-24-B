\# Presentación - Chat de Android Studio (Gemini)



\## Prompt utilizado



Actúa como tech lead de Android. Necesito que plantees un desafío técnico sobre manejo de estados en Jetpack Compose. Esto está dirigido a un candidato para una posición de desarrollador Android junior. Quiero que respondas en formato de prueba técnica, incluyendo contexto del negocio, requerimientos, criterios de evaluación y posibles extensiones. Ten en cuenta estas condiciones: no incluyas la solución, el problema debe evaluar buenas prácticas y uso correcto de estados.



\## Respuesta obtenida



Desafío Técnico: "EcoMart - Gestor de Carrito de Compras"



1\. Contexto del Negocio



"EcoMart" es una startup que busca incentivar el consumo responsable. Necesitamos implementar la pantalla principal de gestión de pedidos. El objetivo es que el usuario pueda visualizar los productos seleccionados, ajustar cantidades y ver el impacto económico de su compra en tiempo real. La experiencia debe ser fluida y el estado del carrito debe ser consistente en todo momento.



2\. El Problema



El candidato debe construir una aplicación de una sola pantalla que gestione una lista de productos en un carrito de compras. La complejidad reside en cómo se propaga el estado desde la lógica de negocio (ViewModel) hacia la interfaz de usuario y cómo se gestionan los eventos de vuelta.



3\. Requerimientos Funcionales



\- Visualización: Mostrar una lista de productos (mínimo 3) que incluya: nombre, precio unitario y cantidad actual.

\- Modificación de Cantidades: Cada ítem debe tener botones para incrementar (+) y decrementar (-) la cantidad. Si la cantidad llega a 0, el producto debe eliminarse de la lista automáticamente o preguntar al usuario.

\- Cálculo en Tiempo Real: Mostrar un pie de página (Bottom Bar o similar) con el total acumulado de la compra que se actualice instantáneamente al cambiar cualquier cantidad.

\- Estado Vacío: Si no hay productos en el carrito, debe mostrarse una ilustración o mensaje indicativo y un botón para "Reiniciar Carrito" (que vuelva a cargar los datos iniciales).



4\. Requerimientos Técnicos (Evaluación de Estado)



\- Arquitectura: Uso obligatorio de ViewModel para la gestión de la lógica de estado.

\- Unidirectional Data Flow (UDF): El estado debe fluir hacia abajo (State) y los eventos hacia arriba (Events).

\- State Containers: Uso de StateFlow o LiveData para exponer el estado desde el ViewModel.

\- Compose Best Practices: Implementar State Hoisting (elevación de estado) para que los componentes de la lista sean "stateless" y fáciles de testear. Uso correcto de remember y mutableStateOf donde sea estrictamente necesario para estados locales de UI. Garantizar que la aplicación maneje correctamente los cambios de configuración (ej. rotación de pantalla) sin perder el estado del carrito.



5\. Criterios de Evaluación



1\. Manejo de Estados: ¿El candidato entiende la diferencia entre estado persistente en ViewModel y estado efímero de UI?

2\. Eficiencia de Recomposición: ¿Se están recomponiendo solo los elementos necesarios o toda la lista ante un cambio de cantidad?

3\. Clean Code: Organización de los Composables, nombrado de variables y separación de lógica vs. vista.

4\. Robustez: Manejo de casos borde (ej. el total nunca debe ser negativo, manejo de decimales en el precio).



6\. Posibles Extensiones (Puntos Extra)



\- Validación de Stock: Añadir un límite máximo de stock por producto; el botón + debe deshabilitarse si se alcanza dicho límite.

\- Animaciones: Uso de AnimatedVisibility o animateContentSize al eliminar productos o actualizar el total.

\- Preview: Inclusión de @Preview parametrizados para diferentes estados (Cargando, Lista, Vacío).



Nota para el Reclutador: Este ejercicio permite observar si el candidato junior simplemente "hace que funcione" (usando estados globales o variables mutables en la vista) o si realmente comprende el ciclo de vida de Compose y el patrón UDF, que es fundamental para escalar cualquier proyecto en Android moderno.

