# Gimnasio

## Diseño Original
![DiseñoGimnasio](https://github.com/user-attachments/assets/4a887a55-c823-46e3-b781-d1e80284327b)


## Diseño Android Studio y ejecucion
Pantalla 1
![Pantalla1](https://github.com/user-attachments/assets/13417758-bcaa-4602-88f6-5cc8b337d301)


Pantalla 2
![Pantalla2](https://github.com/user-attachments/assets/779ac647-15c4-4a25-9805-01c5d1261773)


Pantalla 3
![Pantalla3](https://github.com/user-attachments/assets/51c0470a-0416-4be7-9a76-23ad2edde97c)

SharedPrefs
![SharedPref](https://github.com/user-attachments/assets/bcc67e0f-ca24-449f-998b-d3c0a8b9cd87)


## XML

Actividad 1
```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:foregroundTint="@color/white"
    android:theme="@style/ThemeOverlay.Material3.TextInputEditText"
    tools:context=".MainActivity">

    <EditText
        android:id="@+id/password_val"
        android:layout_width="209dp"
        android:layout_height="48dp"
        android:layout_marginTop="60dp"
        android:layout_marginEnd="44dp"
        android:background="@android:drawable/editbox_background_normal"
        android:backgroundTint="@color/material_dynamic_neutral80"
        android:fadingEdge="horizontal|vertical"
        android:inputType="textPassword"
        android:visibility="visible"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/email" />

    <TextView
        android:id="@+id/password"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="44dp"
        android:layout_marginBottom="292dp"
        android:text="@string/password"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent" />

    <TextView
        android:id="@+id/app_name"
        android:layout_width="317dp"
        android:layout_height="110dp"
        android:outlineProvider="paddedBounds"
        android:text="@string/app_name"
        android:textAlignment="center"
        android:textDirection="inherit"
        android:textSize="34sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.228" />

    <TextView
        android:id="@+id/email"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="44dp"
        android:layout_marginBottom="384dp"
        android:text="@string/email"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent" />

    <ImageView
        android:id="@+id/logo"
        android:layout_width="115dp"
        android:layout_height="48dp"
        android:layout_marginStart="16dp"
        android:layout_marginTop="16dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@drawable/icono" />

    <Button
        android:id="@+id/btn_login"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginEnd="172dp"
        android:layout_marginBottom="180dp"
        android:text="@string/logIn"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <EditText
        android:id="@+id/email_val"
        android:layout_width="209dp"
        android:layout_height="48dp"
        android:layout_marginEnd="44dp"
        android:layout_marginBottom="48dp"
        android:background="@android:drawable/editbox_background"
        android:backgroundTint="@color/material_dynamic_neutral80"
        android:drawableTint="@color/material_dynamic_neutral10"
        android:fadingEdge="horizontal|vertical"
        android:foregroundTint="@color/material_dynamic_neutral10"
        android:requiresFadingEdge="horizontal|vertical"
        android:visibility="visible"
        app:layout_constraintBottom_toTopOf="@+id/password_val"
        app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
Actividad 2
```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".SecondActivity">

    <TextView
        android:id="@+id/maquina3"
        android:layout_width="258dp"
        android:layout_height="61dp"
        android:layout_marginTop="152dp"
        android:text="@string/maquina3"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.888"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/dashboard" />

    <TextView
        android:id="@+id/maquina2"
        android:layout_width="257dp"
        android:layout_height="60dp"
        android:layout_marginTop="76dp"
        android:text="@string/maquina2"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.896"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/dashboard" />

    <ImageView
        android:id="@+id/avatar"
        android:layout_width="136dp"
        android:layout_height="126dp"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        tools:srcCompat="@tools:sample/avatars" />

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/menu"
        android:layout_width="136dp"
        android:layout_height="373dp"
        android:background="@android:drawable/edit_text"
        android:backgroundTint="@color/material_dynamic_neutral80"
        android:clipChildren="false"
        android:clipToPadding="false"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/avatar" />

    <TextView
        android:id="@+id/app_name_little"
        android:layout_width="188dp"
        android:layout_height="85dp"
        android:layout_marginStart="20dp"
        android:layout_marginTop="16dp"
        android:text="@string/app_name"
        android:textSize="34sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.477"
        app:layout_constraintStart_toEndOf="@+id/avatar"
        app:layout_constraintTop_toTopOf="parent" />

    <View
        android:id="@+id/dashboard"
        android:layout_width="271dp"
        android:layout_height="259dp"
        android:layout_marginStart="8dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.971"
        app:layout_constraintStart_toEndOf="@+id/menu"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.237" />

    <TextView
        android:id="@+id/maquina1"
        android:layout_width="257dp"
        android:layout_height="56dp"
        android:text="@string/maquina1"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.889"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/dashboard" />

    <View
        android:id="@+id/scroll"
        android:layout_width="15dp"
        android:layout_height="224dp"
        android:layout_marginTop="368dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/user_info"
        android:layout_width="365dp"
        android:layout_height="63dp"
        android:layout_marginEnd="32dp"
        android:layout_marginBottom="16dp"
        android:background="@android:drawable/edit_text"
        android:backgroundTint="@color/material_dynamic_neutral80"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
Actividad 3
```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ThirdActivity">

    <TextView
        android:id="@+id/app_name_little"
        android:layout_width="188dp"
        android:layout_height="85dp"
        android:layout_marginStart="20dp"
        android:layout_marginTop="12dp"
        android:layout_marginEnd="52dp"
        android:text="@string/app_name"
        android:textSize="34sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="1.0"
        app:layout_constraintStart_toEndOf="@+id/avatar"
        app:layout_constraintTop_toTopOf="parent" />

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/menu"
        android:layout_width="128dp"
        android:layout_height="373dp"
        android:layout_marginTop="128dp"
        android:background="@android:drawable/edit_text"
        android:backgroundTint="@color/material_dynamic_neutral80"
        android:clipChildren="false"
        android:clipToPadding="false"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="@+id/avatar" />

    <ImageView
        android:id="@+id/avatar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        tools:srcCompat="@tools:sample/avatars" />

    <TextView
        android:id="@+id/nombreMaquina"
        android:layout_width="265dp"
        android:layout_height="27dp"
        android:layout_marginStart="3dp"
        android:layout_marginTop="16dp"
        app:layout_constraintStart_toEndOf="@+id/avatar"
        app:layout_constraintTop_toBottomOf="@+id/app_name_little" />

    <Button
        android:id="@+id/btn_active_qr"
        android:layout_width="102dp"
        android:layout_height="105dp"
        android:layout_marginStart="3dp"
        android:layout_marginTop="4dp"
        android:text="@string/activar_QR"
        app:layout_constraintStart_toEndOf="@+id/menu"
        app:layout_constraintTop_toBottomOf="@+id/nombreMaquina" />

    <Button
        android:id="@+id/btn_report_incidence"
        android:layout_width="152dp"
        android:layout_height="103dp"
        android:layout_marginStart="20dp"
        android:layout_marginTop="4dp"
        android:text="@string/comunicar_incidencia"
        app:layout_constraintStart_toEndOf="@+id/btn_active_qr"
        app:layout_constraintTop_toBottomOf="@+id/nombreMaquina" />

    <TextView
        android:id="@+id/ranking"
        android:layout_width="285dp"
        android:layout_height="190dp"
        android:layout_marginTop="108dp"
        android:text="@string/ranking"
        app:layout_constraintStart_toEndOf="@+id/menu"
        app:layout_constraintTop_toBottomOf="@+id/nombreMaquina" />

    <TextView
        android:id="@+id/historico"
        android:layout_width="282dp"
        android:layout_height="288dp"
        android:layout_marginTop="296dp"
        android:text="@string/histórico"
        app:layout_constraintStart_toStartOf="@+id/ranking"
        app:layout_constraintTop_toBottomOf="@+id/nombreMaquina" />

    <Button
        android:id="@+id/btn_log_out"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginBottom="16dp"
        android:text="@string/logout"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toStartOf="@+id/historico" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
SharedPrefs
```
<?xml version='1.0' encoding='utf-8' standalone='yes' ?>
<map>
    <string name="email">ad@gmail.com</string>
</map>
```
## Comentarios
He utilizado el componente view ya que hay componentes más complejos que dan problemas. Se cambiarían a distintos tipos de vistas a futuro. Al igual que apartados como el menú se irán definiendo con mas detalle a futuro. Los botones de logOut y el user_info no los he incluido en el diseño, ya que irian dentro del menú para proximas prácticas
