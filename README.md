# 📝 Ticky - To-Do List App

Ticky is a simple, efficient, and modern To-Do list Android application currently under development. The goal of Ticky is to help users stay organized, boost productivity, and manage their daily tasks effectively with a clean and intuitive UI.

---

## 🚧 Current Status

> **Under Development**  
Ticky is actively being built and tested. The core features are being integrated, and the app is not yet published on the Play Store. A public release will be announced once development and QA are complete.

---

## ✨ Features (Planned & In Progress)

- ✅ Add, update, and delete to-do items  
- ✅ Local data persistence using **Room Database**
- 🕒 Task scheduling and reminders  
- 📌 Priority tagging (High, Medium, Low)  
- 🎨 Material You theming  
- 📊 Task completion statistics  
- ☁️ Cloud sync (planned for future release)  

---

## 🏗️ Tech Stack

- **Language**: Kotlin  
- **Architecture**: MVVM  
- **Database**: [Room Persistence Library](https://developer.android.com/jetpack/androidx/releases/room)  
- **UI Toolkit**: Material Components  
- **Build Tools**: Gradle, ProGuard  

---

## 🧩 Room Database Integration

Ticky uses Room for managing local task data persistently. The database handles the storage of task information and supports features such as LiveData and auto-updating lists for UI responsiveness.

Room setup includes:

- `AppDatabase` with annotated `@Database`
- `TaskDao` interface for data access
- `TaskEntity` data class with annotations
- Repository pattern for abstraction and separation of concerns

---

## 🔧 Installation & Setup (for contributors)

1. Clone the repository:

    ```bash
    git clone https://github.com/your-username/ticky.git
    cd ticky
    ```

2. Open in Android Studio.

3. Ensure you have the latest version of Android Studio & SDK.

4. Build the project and run on an emulator or Android device.

---

## 📸 Screenshots (Coming Soon)

> UI mockups and screenshots will be added once the primary interface is complete.

---

## 📅 Roadmap

- [x] Core to-do list functionality  
- [x] Room database integration  
- [ ] Reminders and notifications  
- [ ] Tagging and filtering  
- [ ] Google account sync  
- [ ] Release to Play Store  

---

## 🤝 Contributing

Ticky is currently being developed as a solo project. Contributions, suggestions, or bug reports are welcome via issues and pull requests once the repository is made public.

---

## 📜 License

TBD — Will be updated upon public release.

---

## 📬 Contact

For updates or collaboration opportunities:

**Developer**: Piyush Apkaje  
**Email**: apkajepiyush@gmail.com.com  
**GitHub**: [github.com/thepiyushapkaje](https://github.com/thepiyushapkaje)

---

> “Stay productive, one tick at a time.” – Ticky
