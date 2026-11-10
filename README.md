# PharmaMobil — KMP | Sesión 14

Proyecto de **Desarrollo de Aplicaciones Móviles**, UPEU. Estudiante: **Jim Guillermo Cueva Roman**.

Esta rama conserva el desarrollo previo de `feature/expect-actual-morrison` (Kotlin Multiplatform, vista de productos y detalle, Koin, funcionalidades nativas Android/iOS).

## Entrega de la sesión 14

- Firma release Android con `keystore.properties` privado (plantilla `keystore.properties.example`).
- `versionCode=1`, `versionName=1.0.0` y R8 activo.
- Gradle Wrapper reproducible 9.1.0.
- Script `scripts/generar-builds-android.sh` para obtener **AAB + APK** firmados y verificar el APK.
- Documentación y matriz de pruebas **sin inventar resultados**.

**Guías y registros:** [Instalación y firma](docs/GUIA-INSTALACION-SESION14.md) · [Ficha de integración](docs/FICHA-INTEGRACION-SESION14.md) · [Pruebas](docs/MATRIZ-PRUEBAS-SESION14.md) · [Cotejo](docs/CHECKLIST-SESION14.md).

### Inicio rápido en Android Studio

```bash
./gradlew :androidApp:assembleDebug
cp keystore.properties.example keystore.properties
# Edita keystore.properties con la clave y credenciales LOCALES
EQUIPO=Equipo1 bash scripts/generar-builds-android.sh
```

La firma requiere crear antes el almacén con `keytool`; sigue la guía. Los archivos reales de entrega se excluyen de Git deliberadamente. **Jamás subas .jks, contraseñas o keystore.properties.**

### Limitaciones actuales

Este repositorio corresponde al avance de sesión 09: el almacenamiento de productos es **en memoria**. Aún no acredita autenticación ni REST de PharmaSoft, CRUD completo, base local persistente, sincronización offline-first o pruebas en dispositivos físicos. iOS requiere macOS, Xcode y firma Apple. Ver estado real de cada criterio en la ficha y checklist.

### Antecedentes

- [Documentación de la sesión 09](docs/REPORTE-PRUEBAS-SESION09.md).
- Basado en el proyecto KMP de clase; los cambios de sesión 14 se hacen en una rama separada, sin sustituir el trabajo previo.
