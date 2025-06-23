# FHCuboids

**FHCuboids** is a Minecraft plugin designed for managing protected cuboid regions on a server. It provides an intuitive set of commands for players to create, manage, and interact with their own plots, including features such as homes, warps, member management, and region protection.

---

## ✨ Features

- 🧱 Create and manage personal cuboids (regions)
- ⚙️ Fully configurable via `config.yml`
- 🏠 Set and teleport to homes within your cuboid
- 👥 Invite or kick other players from your cuboid
- 📌 Create and delete warp points
- 📋 Display information about owned cuboids
- ✅ Whitelist-based control over commands usable inside regions
- 💾 Persistent data storage using MySQL, Hibernate & HikariCP

---

## 🧩 Included Commands

| Command                  | Description                                 |
|--------------------------|---------------------------------------------|
| `/cuboid add`            | Create a new cuboid                         |
| `/cuboid delete`         | Delete an existing cuboid                   |
| `/cuboid info`           | Show cuboid information                     |
| `/cuboid kick`           | Remove a member from a cuboid               |
| `/cuboid leave`          | Leave your current cuboid                   |
| `/cuboid sethome`        | Set a cuboid home location                  |
| `/cuboid home`           | Teleport to your cuboid home                |
| `/cuboid delwarp`        | Delete a warp point inside the cuboid       |
| `/cuboid crafting`       | Additional configuration or behavior option |

Each subcommand is implemented modularly in its own class (e.g., `AddSubcommand.java`, `DeleteSubcommand.java`, `InfoSubcommand.java`, etc.) for clean code separation and easy maintenance.

---

## ⚙️ Configuration

All plugin settings can be managed via the `config.yml` file. Configuration options include:

- Maximum cuboid size and limits
- Allowed/disallowed commands inside cuboids (whitelist)
- Default values for new cuboids
- MySQL database connection details

---

## 📦 Dependencies

- Spigot API 1.19.1-R0.1-SNAPSHOT
- MySQL Connector/J
- Hibernate Core & HikariCP
- Vault API (for permissions/economy support)
- RedLib (utility library)
- Kotlin Standard Library (for Kotlin interop)

---

## 🛠️ Building the Plugin

### 🔧 Requirements

- Java 17+
- Gradle
- Git (required for release versioning)

### 🏗️ Build Instructions

Clone and build the plugin using:

```bash
git clone https://github.com/yourusername/FHCuboids.git
cd FHCuboids
./gradlew shadowJar
