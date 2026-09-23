## 🤖 Fase 2: Optimización y Mejoras Asistidas por IA

En esta fase se utilizó asistencia de Inteligencia Artificial para refinar la experiencia de usuario (UX/UI) siguiendo las pautas de Material Design 3, integrar la paleta cromática de la clínica y optimizar la reactividad de la aplicación.

---

### 📌 Prompt 1: Búsqueda dinámica de médicos y filtrado reactivo
* **Prompt:**
  > *"Añade un campo de texto OutlinedTextField en HomeScreen para filtrar en tiempo real la lista de médicos por nombre y especialidad, manteniendo la compatibilidad con el filtro de especialidades mediante FilterChip."*
* **Resultado e Impacto:**  
  Implementación de un estado reactivo `searchQuery` en `HomeScreen.kt` que filtra dinámicamente el listado de médicos según las coincidencias de texto e integración fluida con la selección horizontal por especialidades.

---

### 📌 Prompt 2: Fidelidad visual del diseño según prototipo Material 3
* **Prompt:**
  > *"Ajusta la paleta cromática en Color.kt y Theme.kt utilizando el color primario púrpura (`0xFF5A2D82`) para coincidir con la maqueta del prototipo. Actualiza el TopAppBar en MainScreen con fondo morado y texto blanco, y modifica las tarjetas de médicos en HomeScreen para incluir el ícono de cruz `+` y el contenedor en tono Púrpura suave."*
* **Resultado e Impacto:**  
  Unificación estética completa de la aplicación. Se alinearon los componentes de `Scaffold`, `TopAppBar` y `DoctorCard` con la identidad visual solicitada en la guía del proyecto.

---

### 📌 Prompt 3: Personalización de cabecera y navegación en ModalNavigationDrawer
* **Prompt:**
  > *"Diseña la estructura del drawerContent para ModalNavigationDrawer en MainScreen de forma que incluya una cabecera con avatar del usuario ('JP - Juan Pérez (Paciente)') y opciones de navegación utilizando íconos circulares dinámicos (RadioButtonChecked / RadioButtonUnchecked) para resaltar la sección activa."*
* **Resultado e Impacto:**  
  Mejora de la usabilidad y navegación general de la app, permitiendo identificar al usuario actual en el menú lateral y brindando retroalimentación visual sobre la pantalla donde se encuentra ubicado.