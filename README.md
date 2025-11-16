

# 📱 **Tổng Hợp Bài Tập Lập Trình Di Động (Android)**

Repository này lưu trữ toàn bộ các **bài tập – mini project – lab thực hành** của môn *Lập Trình Di Động* (Android).
Mỗi bài tập là một dự án Android độc lập và có thể mở trực tiếp bằng Android Studio.

---

## 📑 **Mục Lục**

* [Giới thiệu](#giới-thiệu)
* [Ảnh minh họa ứng dụng](#ảnh-minh-họa-ứng-dụng)
* [Cấu trúc Repository](#cấu-trúc-repository)
* [Công nghệ sử dụng](#công-nghệ-sử-dụng)
* [Cách mở từng project](#cách-mở-từng-project)
* [Cấu trúc một project Android](#cấu-trúc-một-project-android)
* [File .gitignore](#file-gitignore)
* [Mục tiêu môn học](#mục-tiêu-môn-học)
* [Liên hệ](#liên-hệ)

---

## 🖼️ **Ảnh minh họa ứng dụng**

Bạn có thể upload ảnh chụp màn hình (screenshots) trong từng bài tập vào thư mục:

```
/screenshots/
```

Sau đó chèn vào README theo mẫu dưới đây 👇

---

### 📌 **Bài tập 1 – Layout cơ bản**

#### Giao diện demo:

<p align="center">
  <img src="screenshots/baitap1.png" width="300" />
</p>

---

### 📌 **Bài tập 2 – Danh sách liên hệ (RecyclerView)**

#### Giao diện demo:

<p align="center">
  <img src="screenshots/baitap2.png" width="300" />
</p>

---

### 📌 **Bài tập 3 – SQLite / Room**

#### Giao diện demo:

<p align="center">
  <img src="screenshots/baitap3.png" width="300" />
</p>

---

*(Bạn chỉ cần upload ảnh vào `screenshots/` và đổi đúng tên ảnh.)*

---

## 📁 **Cấu trúc Repository**

```
mobile-programming/
│
├── BaiTap1_Layout/
├── BaiTap2_RecyclerView/
├── BaiTap3_SQLite/
├── screenshots/        ← chứa ảnh minh họa
└── README.md
```

---

## 🛠 **Công nghệ sử dụng**

* Android Studio (Iguana / Ladybug / phiên bản mới hơn)
* Java hoặc Kotlin
* XML Layout
* RecyclerView + Adapter
* Material Components
* SQLite / Room (tùy bài)
* ViewBinding / DataBinding
* Gradle Scripts (.kts)

---

## ▶️ **Cách mở từng project**

1. Clone repo:

```sh
git clone https://github.com/<username>/mobile-programming.git
```

2. Mở Android Studio → **Open**

3. Chọn thư mục bài tập cần mở:

```
mobile-programming/BaiTap2_RecyclerView/
```

4. Chờ Gradle sync → **Run ▶**

---

## 🧱 **Cấu trúc một project Android**

```
app/
 ├── manifests/
 ├── java/
 ├── res/
 └── build.gradle.kts
gradle/
build.gradle.kts
settings.gradle.kts
local.properties
```

---

## 🔒 **File .gitignore**

```gitignore
.gradle/
**/build/
local.properties
.idea/
*.iml
.DS_Store
Thumbs.db
.cxx/
externalNativeBuild/
captures/
```

---

## 🎯 **Mục tiêu môn học**

* Nắm vững vòng đời Activity
* Hiểu Intent, Bundle, Fragment
* Thiết kế giao diện bằng XML
* Làm việc với RecyclerView
* Lưu trữ dữ liệu bằng SQLite/Room
* Tạo mini ứng dụng hoàn chỉnh
* Quản lý dự án với Gradle

---

## 📬 **Liên hệ**

📧 *<your_email_here>*
📌 Hoặc tạo **Issue** trực tiếp trên GitHub để hỏi đáp.

---

Nếu bạn muốn, mình có thể:

✔ Tạo README riêng cho từng bài tập
✔ Tự tạo mẫu screenshot frame đẹp
✔ Tự sinh danh sách bài tập dựa trên folder repo của bạn

Chỉ cần gửi:
👉 **“Tạo README cho từng bài tập từ danh sách folder”**
