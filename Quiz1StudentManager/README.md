# Student Manager - Aplikasi Pengelolaan Data Mahasiswa

Aplikasi **Student Manager** adalah aplikasi Android sederhana berbasis **Jetpack Compose** yang dirancang untuk mengelola data mahasiswa secara efisien. Aplikasi ini mendukung operasi dasar CRUD (Create, Read, Update, Delete) serta fitur pencarian data secara *real-time*.

---

## 🚀 Fitur Utama

1. **Splash Screen**
   - Menampilkan logo aplikasi dan judul saat aplikasi pertama kali dibuka selama 2 detik sebelum masuk ke halaman utama.

2. **Daftar Mahasiswa (Home Screen)**
   - Menampilkan daftar seluruh mahasiswa yang terdaftar.
   - Menampilkan jumlah total mahasiswa secara dinamis.
   - Kartu item mahasiswa mencakup informasi:
     - Nama Mahasiswa
     - NIM (Nomor Induk Mahasiswa)
     - Program Studi
     - Tombol aksi Edit dan Hapus pada masing-masing item.

3. **Pencarian Mahasiswa**
   - Fitur pencarian otomatis (*real-time*) berdasarkan **Nama**, **NIM**, atau **Program Studi**.
   - Dilengkapi tombol hapus teks pencarian (*clear button*).

4. **Tambah Mahasiswa Baru**
   - Form untuk menambahkan data mahasiswa baru.
   - Field inputan:
     - **NIM** (Teks)
     - **Nama** (Teks)
     - **Program Studi** (Dropdown / Pilihan)
   - Validasi sederhana untuk memastikan semua field telah terisi sebelum disimpan.

5. **Edit Data Mahasiswa**
   - Form untuk mengubah/memperbarui data mahasiswa yang sudah ada.
   - Mengisi otomatis data mahasiswa yang dipilih ke dalam form edit.

6. **Hapus Data Mahasiswa**
   - Dialog konfirmasi (*AlertDialog*) muncul sebelum penghapusan data dilakukan untuk mencegah ketidaksengajaan.

7. **Tampilan Data Kosong (Empty State)**
   - Menampilkan pesan panduan ketika belum ada data mahasiswa yang terdaftar atau hasil pencarian tidak ditemukan.

8. **Menu Opsi (Top Bar)**
   - Memiliki menu titik tiga pada bagian kanan atas yang berisi opsi:
     - **Refresh**
     - **Tentang Aplikasi**
     - **Keluar**

---

## 🛠️ Teknologi yang Digunakan

- **Bahasa Pemrograman**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose) dengan [Material Design 3](https://m3.material.io/)
- **Arsitektur**: MVVM (Model-View-ViewModel)
- **State Management**: `StateFlow` dan `ViewModel`
- **Navigasi**: `Navigation Compose`

---

## 📂 Struktur Proyek

```text
com.example.quiz1studentmanager/
│
├── model/
│   └── Student.kt             # Data class model Mahasiswa
│
├── viewmodel/
│   └── StudentViewModel.kt    # Logic bisnis & manajemen state data
│
├── ui/
│   ├── navigation/
│   │   └── Screen.kt          # Definisi rute navigasi aplikasi
│   │
│   ├── screens/
│   │   ├── SplashScreen.kt    # Tampilan awal/loading
│   │   ├── HomeScreen.kt      # Tampilan daftar mahasiswa & pencarian
│   │   └── AddEditScreen.kt   # Form tambah & edit mahasiswa
│   │
│   └── theme/                 # Konfigurasi warna, tipografi, dan tema Material 3
│
└── MainActivity.kt            # Activity utama & alur NavHost
```

---

## 🧭 Alur Navigasi Aplikasi

```
Splash Screen ──> Home Screen (Daftar Mahasiswa)
                       │
                       ├───> Tambah Mahasiswa
                       ├───> Edit Mahasiswa
                       ├───> Hapus Mahasiswa (Dialog Konfirmasi)
                       └───> Pencarian Mahasiswa
```

---

## 💻 Cara Menjalankan Aplikasi

1. Clone atau buka folder proyek ini di **Android Studio**.
2. Pastikan perangkat atau emulator Android terhubung (Min SDK: Android 14 / API 34).
3. Lakukan **Gradle Sync** jika diminta.
4. Klik tombol **Run** (atau tekan `Shift + F10`) di Android Studio.
