# Matriz de pruebas y defectos — Sesión 14

**Producto:** PharmaMobil | **Versión propuesta:** 1.0.0 | **Fecha:** 10/11/2026

> SIN EJECUTAR: esta matriz es un formato de registro, no evidencia de pruebas aprobadas.

| ID | Prueba | Equipo A: modelo / Android | Equipo B: modelo / Android | Resultado esperado | Resultado real | Estado |
|---|---|---|---|---|---|---|
| P01 | Instalar APK release firmado | Por registrar | Por registrar | Instala sin IDE | Por observar | Pendiente |
| P02 | Abrir app sin depuración | Por registrar | Por registrar | Abre sin cierre inesperado | Por observar | Pendiente |
| P03 | Listar productos | Por registrar | Por registrar | Lista/estado vacío correcto | Por observar | Pendiente |
| P04 | Registrar producto válido | Por registrar | Por registrar | Elemento visible en lista | Por observar | Pendiente |
| P05 | Registrar producto inválido | Por registrar | Por registrar | Validación clara | Por observar | Pendiente |
| P06 | Ver detalle y compartir | Por registrar | Por registrar | Detalle correcto, intent/hoja compartir | Por observar | Pendiente |
| P07 | Comprobar minificación release | Por registrar | Por registrar | Sin cierre inesperado / sin pérdida de estado | Por observar | Pendiente |
| P08 | Iniciar/cerrar sesión REST | No implementado | No implementado | Autenticación con PharmaSoft | No evaluable | Limitación |
| P09 | CRUD completo y roles | No implementado | No implementado | 4 acciones y 403 según rol | No evaluable | Limitación |
| P10 | Crear sin red y sincronizar | No implementado | No implementado | Persistencia/cola y sincronización | No evaluable | Limitación |
| P11 | Permiso concedido/denegado/permanente | Por revisar | Por revisar | Gestión de 3 estados | No evaluable en esta versión | Limitación |
| P12 | Archive y firma iOS | Requiere Mac | Requiere Mac | Archive en Organizer | Sin ejecutar | Pendiente |

## Registro de defectos

| Defecto | Equipo / SO | Pasos para reproducir | Esperado | Obtenido y evidencia | Severidad | Estado |
|---|---|---|---|---|---|---|
| — | — | — | — | — | — | No registrado aún |

La ausencia de registros de defectos **no equivale a cero defectos**: las pruebas no se han realizado.

## Ficha de versión (completar tras build real)

| Campo | Valor |
|---|---|
| `versionCode` | 1 |
| `versionName` | 1.0.0 |
| `applicationId` | pe.edu.upeu.pharmamobil |
| Rama | feature/builds-14 |
| SHA de commit final | Por registrar |
| Huella SHA-256 certificado | Por obtener de apksigner |
| SHA-256 APK | Por calcular |
| SHA-256 AAB | Por calcular |
| Dispositivo A | Pendiente |
| Dispositivo B | Pendiente |
| Archivo iOS / motivo de limitación | Pendiente |
