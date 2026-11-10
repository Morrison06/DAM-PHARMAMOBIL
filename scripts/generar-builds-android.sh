#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT"

if [[ ! -f keystore.properties ]]; then
  echo "ERROR: falta keystore.properties; copia keystore.properties.example y completa localmente." >&2
  exit 1
fi

for key in storeFile storePassword keyAlias keyPassword; do
  if ! grep -Eq "^$key=[^[:space:]]+" keystore.properties; then
    echo "ERROR: falta propiedad de firma: $key" >&2
    exit 1
  fi
done

if ! command -v java >/dev/null; then
  echo "ERROR: instala JDK 21." >&2; exit 1
fi
if [[ -z "${ANDROID_HOME:-${ANDROID_SDK_ROOT:-}}" ]]; then
  echo "ERROR: configura ANDROID_HOME o ANDROID_SDK_ROOT." >&2; exit 1
fi

./gradlew :androidApp:bundleRelease :androidApp:assembleRelease

APK="androidApp/build/outputs/apk/release/androidApp-release.apk"
AAB="androidApp/build/outputs/bundle/release/androidApp-release.aab"
for f in "$APK" "$AAB"; do
  if [[ ! -s "$f" ]]; then
    echo "ERROR: no apareció $f" >&2; exit 1
  fi
done

SDK_ROOT="${ANDROID_HOME:-$ANDROID_SDK_ROOT}"
APKSIGNER="$(find "$SDK_ROOT/build-tools" -maxdepth 2 -type f -name apksigner | sort -V | tail -n 1)"
if [[ -z "$APKSIGNER" ]]; then
  echo "ERROR: no se encontró apksigner en Android SDK Build-Tools." >&2
  exit 1
fi
"$APKSIGNER" verify --verbose --print-certs "$APK"

EQUIPO="${EQUIPO:-EquipoN}"
if [[ ! "$EQUIPO" =~ ^[a-zA-Z0-9_-]+$ ]]; then
  echo "ERROR: EQUIPO solo admite letras, números, guiones y guion bajo." >&2; exit 1
fi

mkdir -p entrega
cp "$APK" "entrega/${EQUIPO}_ProyectoFinal_Android.apk"
cp "$AAB" "entrega/${EQUIPO}_ProyectoFinal_Android.aab"
echo "Builds generados y APK verificado. Huellas de archivos:"
sha256sum "entrega/${EQUIPO}_ProyectoFinal_Android.apk" "entrega/${EQUIPO}_ProyectoFinal_Android.aab"
echo "ATENCIÓN: falta instalar y recorrer el APK en dos teléfonos reales."
