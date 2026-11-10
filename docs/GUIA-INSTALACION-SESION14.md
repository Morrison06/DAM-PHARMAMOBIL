# Guía de instalación y generación de builds — PharmaMobil (sesión 14)

**Asignatura:** Desarrollo de Aplicaciones Móviles (UPEU)  
**Estudiante:** Jim Guillermo Cueva Roman  
**Proyecto:** PharmaMobil / Kotlin Multiplatform  
**Versión:** 1.0.0 (Android `versionCode=1`, iOS `MARKETING_VERSION=1.0.0`)  
**Identificador Android:** `pe.edu.upeu.pharmamobil`

## 1. Preparación

- Descarga la rama `feature/builds-14` y ábrela en Android Studio.
- Instala JDK 21, Android SDK 36 y Android Build-Tools, acepta las licencias.
- El repositorio incluye Gradle Wrapper 9.1.0. Gradle necesita descargar dependencias.
- Ejecuta `./gradlew :androidApp:assembleDebug` para verificar la configuración inicial.
- No confundas el build de desarrollo con uno firmado para entrega.

## 2. Clave de firma Android (una sola vez)

Crea una carpeta **fuera** del repositorio y ejecuta de forma interactiva:

```bash
mkdir -p "$HOME/.pharmamobil-firma"
keytool -genkeypair -v -keystore "$HOME/.pharmamobil-firma/pharmamobil-release.jks" \
  -storetype PKCS12 -keyalg RSA -keysize 3072 -validity 10000 -alias pharmamobil
```

Guarda una copia segura del almacén. Las contraseñas **nunca** se escriben en GitHub ni en una captura. No compartas la clave con el docente.

```bash
cp keystore.properties.example keystore.properties
```

Edita `keystore.properties` con la ruta real y las credenciales introducidas durante la creación. Git ignora este archivo, `*.jks`, `*.keystore` y los paquetes generados. Confirma con `git check-ignore -v keystore.properties`.

## 3. Android firmado

```bash
./gradlew :androidApp:bundleRelease :androidApp:assembleRelease
```

Se generan, normalmente:
- `androidApp/build/outputs/bundle/release/androidApp-release.aab`
- `androidApp/build/outputs/apk/release/androidApp-release.apk`

También puedes ejecutar `EQUIPO=Equipo1 bash scripts/generar-builds-android.sh` para compilar, copiar con nomenclatura final y verificar la firma. Sustituye *Equipo1* por el número real del equipo.

El **AAB** es para publicación y **no se instala directamente**; el **APK** es el instalable para las pruebas.

Verifica con la utilidad de Android SDK (ajusta la versión del directorio a la instalada):

```bash
"$ANDROID_HOME/build-tools/36.0.0/apksigner" verify --verbose --print-certs \
  androidApp/build/outputs/apk/release/androidApp-release.apk
```

Guarda captura de la salida con huellas del certificado y resultado exitoso. Anota SHA-256 del APK/AAB para integridad. Si el APK no está firmado, **no entregarlo** como release.

### Diagnóstico de R8

La minificación está activada. Instala y recorre el APK release: registrar, listar y ver detalle de un producto, probar validaciones y compartir. Si una versión release falla donde debug funcionaba, revisa Logcat y aplica la regla ProGuard mínima, **sin ocultar el defecto**.

## 4. Instalación en Android real

1. Transfiere el APK firmado a un teléfono Android y permite instalación local si el sistema la solicita.
2. Instálalo sin Android Studio conectado o mediante `adb install -r ruta-del-apk`.
3. Verifica nombre, apertura, productos, campos inválidos, detalle y opción Compartir.
4. Repite en otro modelo/versión de Android y documenta los resultados.
5. El backend en `10.0.2.2` solo funciona desde el emulador; en una app con REST, un dispositivo físico necesita IP LAN o un servidor accesible.

**Importante:** esta rama usa repositorios *en memoria*, por lo que no se puede declarar aprobada la integración PharmaSoft, login, CRUD completo ni sincronización offline-first. No atribuyas fallos de conexión a un backend que aún no está integrado.

## 5. iOS

Para iOS se requiere **macOS + Xcode**:
1. Abre `iosApp/iosApp.xcodeproj` cuando el proyecto Xcode esté completo en tu clon.
2. En Signing & Capabilities, elige tu Apple Development Team. No se publican certificados ni perfiles en Git.
3. Selecciona Any iOS Device (no simulador) y Product > Archive.
4. En Organizer, guarda captura del archivo y distribuye/exporta solo si tu cuenta lo permite.
5. Con cuenta gratuita, la exportación ad hoc de IPA puede no estar disponible. Documenta la limitación y aporta captura de Archive si se consigue.

En este entorno no se pudo compilar iOS ni exportar un IPA: se requiere un Mac con Xcode y firma Apple del titular.

## 6. Paquete y versión final

Completa `docs/MATRIZ-PRUEBAS-SESION14.md` y el checklist. Solo cuando se hayan compilado, verificado y probado los artefactos definitivos, fusiona `feature/builds-14` en `develop`, y crea la etiqueta `v1.0-final` sobre el commit exacto de los builds finales. **No se etiqueta una entrega que aún está pendiente de pruebas**.

## 7. Evidencias mínimas (capturas)

- Archivo `keystore.properties.example` y reglas .gitignore, **sin credenciales**.
- APK y AAB generados, con nombres de archivo.
- Salida de `apksigner verify --print-certs`.
- App instalada y abierta en dos dispositivos físicos distintos.
- Registro/listado, validación y prueba de release tras R8.
- En iOS, Organizer con Archive o documentación de la imposibilidad real.
- Matriz de defectos y checklist final.

**Estado al publicar esta guía:** código preparado para compilar, sin ejecutar builds reales ni pruebas físicas en este entorno.
