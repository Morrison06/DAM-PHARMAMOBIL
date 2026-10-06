# PharmaMobil - Sesión 09

Práctica de **Desarrollo de Aplicaciones Móviles** con Kotlin Multiplatform.

## Rama de entrega

`feature/expect-actual-morrison`

## Capacidades nativas implementadas

### Formato de moneda con expect/actual

- `commonMain/platform/Formato.kt`: declaración `expect`.
- `androidMain/platform/Formato.android.kt`: implementación Android con `NumberFormat`.
- `iosMain/platform/Formato.ios.kt`: implementación iOS con `NSNumberFormatter`.
- El formato se aplica en `ProductoUi`, dentro de presentation.

### Compartir mediante interfaz + inyección

- `domain/platform/Compartidor.kt`: contrato común.
- `CompartidorAndroid.kt`: usa `Intent.ACTION_SEND` y `FLAG_ACTIVITY_NEW_TASK`.
- `CompartidorIos.kt`: usa `UIActivityViewController`.
- Ambos `platformModule` registran su implementación con Koin.
- `DetalleProductoViewModel` depende del contrato `Compartidor`.

### Pantalla de detalle

La pantalla de detalle incluye el botón **Compartir** y mantiene las APIs nativas fuera de presentation.

## Evidencias

Consultar:

- `docs/EVIDENCIAS-SESION-09.md`
- `docs/REPORTE-PRUEBAS-SESION09.md`

La compilación de iOS requiere macOS + Xcode.
