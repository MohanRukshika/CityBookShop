# 📚 CityBookShop

A Java desktop application for managing a bookshop, built with **Java Swing**. It supports two roles — **Manager** and **Cashier** — with a clean, modern GUI and file-based data persistence.

---

## 📖 Table of Contents

- [About the Project](#about-the-project)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Running the App](#running-the-app)
- [How It Works](#how-it-works)
- [Data Storage](#data-storage)
- [Future Improvements](#future-improvements)
- [Author](#author)

---

## 🏙️ About the Project

**CityBookShop** is a role-based bookshop management system built as a Java desktop (GUI) application. It allows staff to log in, view the book inventory, and search for books. Managers have additional privileges to add new books and create new user accounts.

---

## ✨ Features

### 🔐 Authentication
- Login with username and password
- Register a new account with a selected role (Manager or Cashier)
- Role-based access: different dashboards for each role

### 👤 Cashier Dashboard
- View all books in the inventory
- Search books by name (case-insensitive)
- Logout and return to the login screen

### 🛠️ Manager Dashboard (extends Cashier)
- All Cashier features, plus:
- Add new books (title, category, price)
- Create new user accounts (Cashier or Manager)

---

## 🛠️ Tech Stack

| Layer        | Technology              |
|--------------|-------------------------|
| Language     | Java (JDK 8+)           |
| GUI          | Java Swing              |
| Data Storage | Flat files (`.txt`)     |
| IDE          | IntelliJ IDEA           |
| Build        | IntelliJ build system   |

---

## 🗂️ Project Structure

```
CityBookShop/
├── src/
│   ├── main/
│   │   └── Main.java            # Entry point — launches LoginFrame
│   ├── gui/
│   │   ├── LoginFrame.java      # Login & registration window
│   │   ├── CashierFrame.java    # Cashier dashboard
│   │   └── ManagerFrame.java    # Manager dashboard (extends CashierFrame)
│   ├── model/
│   │   ├── User.java            # Abstract base class for users
│   │   ├── Cashier.java         # Cashier role
│   │   ├── Manager.java         # Manager role
│   │   └── Book.java            # Book entity
│   └── Services/
│       ├── BookService.java     # Book CRUD operations
│       ├── UserService.java     # User login & registration
│       └── FileHandler.java     # File read/write utility
├── books.txt                    # Book data store
├── users.txt                    # User data store
└── CityBookShop.iml             # IntelliJ module file
```

---

## 🚀 Getting Started

### Prerequisites

- Java JDK 8 or above installed
- IntelliJ IDEA (recommended) or any Java IDE

### Running the App

1. **Clone the repository**

```bash
git clone https://github.com/MohanRukshika/CityBookShop.git
cd CityBookShop
```

2. **Open in IntelliJ IDEA**
   - Go to `File → Open` and select the `CityBookShop` folder
   - IntelliJ will auto-detect the project structure

3. **Run the application**
   - Open `src/main/Main.java`
   - Click the green **Run** button, or press `Shift + F10`

> ⚠️ Make sure `books.txt` and `users.txt` are in the **project root directory** (where you run the app from), not inside `src/`.

---

## ⚙️ How It Works

```
App Launch
    └── LoginFrame
            ├── Login  → verifies credentials from users.txt
            │       ├── Role = Manager  → ManagerFrame
            │       └── Role = Cashier  → CashierFrame
            └── Register → saves new user to users.txt
```

- **Cashier** can view all books and search by name.
- **Manager** inherits all cashier features and can additionally add books and create accounts.
- All data is read from and written to plain `.txt` files using `FileHandler`.

---

## 💾 Data Storage

Data is stored in plain text files using comma-separated values (CSV format):

**`books.txt`** — one book per line:
```
Java Basics,Programming,2500.0
Atomic Habits,Self Help,2100.0
```

**`users.txt`** — one user per line:
```
admin,1234,Manager
cashier1,1111,Cashier
```

> ⚠️ Passwords are currently stored as plain text. This is suitable for a learning project but should use hashing (e.g., BCrypt) in a production system.

---

## 👤 Author

**Rukshi Mohan**

- GitHub: [@MohanRukshika](https://github.com/MohanRukshika)
- University: Eastern University of Sri Lanka, Trincomalee Campus
- Degree: BSc in Computer Science (Year 2)

---

> ⭐ If you found this project useful, feel free to give it a star on GitHub!
