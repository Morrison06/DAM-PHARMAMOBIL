# Reglas de PharmaMobil para build release.
# Evitar mantener clases indiscriminadamente: comprobar fallos reales en R8.
# Útil para trazas y librerías que consultan anotaciones.
-keepattributes Signature,InnerClasses,EnclosingMethod,*Annotation*
-keepattributes SourceFile,LineNumberTable

# Si en sesiones posteriores se agregan DTO serializables o Koin por reflexión,
# documentar las reglas concretas en este archivo tras reproducir el problema.
