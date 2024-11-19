# Project Overview

This project is a **Kotlin-based Android application** developed using modern Android development tools and libraries, ensuring offline capabilities and seamless user experience.

---

## 🚀 **Technologies Used**
- **Kotlin** for robust and concise development.
- **Jetpack Compose** for building declarative and modern UIs.
- **Hilt** for Dependency Injection.
- **Room Database** for local data storage and offline access.
- **Retrofit** for API communication.
- **NavController** for smooth navigation between screens.
- **Custom Drawer** for a unique right-to-left sliding drawer experience.

---

## ✨ **Features**

### **User Authentication**
- On first launch, the user is prompted to enter their **email address**.
- The app retrieves an **authentication token** from the server using the entered email.
- The retrieved token is securely stored in **Room Database** for future logins.
- **Automatic Login**: If the token already exists in **SharedPreferences**, the user is automatically redirected to the **HomeActivity** without needing to enter their email again.

---

### **Copilot Management**
- **Copilot List Retrieval**:
  - Fetches the list of copilots from the server via an **API call**.
  - Stores the retrieved data in **Room Database** for offline access.
  - Implements **Flow** to observe the database continuously, ensuring the UI reflects real-time changes in the data.
  - Uses the **single source of truth** principle by always accessing data from the local database.
- **Offline Access**:
  - The app ensures users can view their copilots even without an internet connection.
- **Filtering**:
  - Users can filter copilots based on **Folder** and **Schedule Types**.
  - Utilizes **StateFlow** and **Combine Operator** for seamless and reactive filtering.

---

### **Internet Connectivity Monitoring**
- Integrates a **Connectivity Observer** to track the internet state:
  - Displays a **dialog** and an **error message** when the internet is disconnected.
  - Automatically removes the error message and dialog when the connection is restored.
  - **API calls** are made only when the app detects an active internet connection.

---

### **Navigation and Drawer**
- **NavController**:
  - Simplifies navigation between screens, enhancing the user experience with clear and intuitive transitions.
- **Custom Drawer**:
  - A custom drawer slides in from the **right to left**, offering a unique and polished UI interaction.

---

### **Navigation and Detail View**
- Users can click on any **Copilot** in the list to navigate to the **DetailScreen**.
- **Scheduled Copilots Only**:
  - Navigation to the **DetailScreen** is allowed only if the selected copilot is **scheduled**.

---

## 🛠️ **Architecture and Design**
- **MVVM Architecture**: Ensures separation of concerns and a clean, testable codebase.
- **Single Source of Truth**: Always retrieves data from **Room Database** to maintain consistency between local and remote data.
- **Reactive Programming**: Combines **StateFlow** and **Flow** for efficient state management and real-time updates.

---

## 💡 **Summary**
This project demonstrates the use of modern Android development practices to build an app with:
- Offline-first capabilities.
- Efficient data management.
- A seamless user experience with real-time updates, custom navigation, and connectivity-aware features.
