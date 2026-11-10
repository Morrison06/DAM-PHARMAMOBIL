# Sesión 14 — ficha de integración y congelamiento de alcance

**Proyecto:** PharmaMobil (Kotlin Multiplatform) | **Backend previsto:** PharmaSoft
**Rama base revisada:** `feature/expect-actual-morrison`
**Rama de trabajo:** `feature/builds-14`
**Fecha de preparación:** 10/11/2026

> Esta ficha refleja únicamente lo verificable en el código publicado. No equivale a resultados de ejecución en dispositivos.

| Componente | Estado de integración | Evidencia / decisión |
|---|---|---|
| UI Android/iOS compartida | Implementación presente; pruebas pendientes | `shared/src/commonMain`, entradas Android/iOS |
| Registro y lista de productos | Presente en repositorio **en memoria** | `ProductoRepositorioEnMemoria` |
| Compartir desde detalle | Implementado a nivel de fuente; pendiente probar | `CompartidorAndroid`, `CompartidorIos` |
| Autenticación/roles REST de PharmaSoft | **Limitación declarada** | No se identificó un cliente REST ni flujo de login en esta rama |
| CRUD completo (crear, leer, actualizar, borrar) | **Limitación declarada** | La rama expone registrar/listar; actualizar/eliminar no están integrados |
| Offline-first persistente + sincronización | **Limitación declarada** | El repositorio en memoria no persiste en disco ni sincroniza |
| Permisos Android/iOS de tres estados | **Limitación declarada** | No hay flujo específico de permiso en esta versión |
| Firma y empaquetado Android | **Se prepara en sesión 14** | Configuración para firma con `keystore.properties` local |
| Archivo iOS y firma Apple | **Pendiente de macOS + Xcode** | Documentar Organizer/Archive o limitación real |
| Pruebas en 2 dispositivos Android | **Pendiente** | Requiere APK firmado y teléfonos físicos |
| commonTest | **Se incorpora prueba inicial** | Ejecutar en entorno con Gradle/SDK |

## Alcance congelado para esta sesión

Esta rama se dedica a la **preparación de entrega**: firma, reducción/ProGuard, automatización de compilación y documentación. No se agregarán funciones de negocio para simular que se completó la sesión 13.

## Limitaciones que pasan al informe

La rama recibida no demuestra autenticación REST, roles, CRUD completo, caché duradera ni sincronización offline-first. Se registran como **pendientes reales** y no se marcan como aprobados.

## Trabajo futuro

Integrar el cliente REST de PharmaSoft, almacenamiento local persistente, cola de operaciones pendientes, recuperación de red y autorización basada en roles, seguido de pruebas E2E.
