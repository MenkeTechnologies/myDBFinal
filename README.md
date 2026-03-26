```
 ██████╗ ██╗   ██╗██████╗ ███████╗██████╗ ██████╗ ██████╗
██╔════╝╚██╗ ██╔╝██╔══██╗██╔════╝██╔══██╗██╔══██╗██╔══██╗
██║      ╚████╔╝ ██████╔╝█████╗  ██████╔╝██║  ██║██████╔╝
██║       ╚██╔╝  ██╔══██╗██╔══╝  ██╔══██╗██║  ██║██╔══██╗
╚██████╗   ██║   ██████╔╝███████╗██║  ██║██████╔╝██████╔╝
 ╚═════╝   ╚═╝   ╚═════╝ ╚══════╝╚═╝  ╚═╝╚═════╝ ╚═════╝
        ▄▄▄  PERSONAL  DATABASE  MAINFRAME  ▄▄▄
        ███  ═══════ RASPBERRY PI 3 ═══════  ███
```

> _"The street finds its own uses for things."_ — William Gibson

---

### `[ SYSTEM STATUS: ONLINE ]`

A full-stack neural interface built with **Kotlin**, **Spring Boot**, **JSPs**, **jQuery**, **Bootstrap**, and **MySQL** — running on bare metal Raspberry Pi 3 hardware.

![Welcome page screenshot](/welcome.png?raw=true)
![Tips and Tricks page](/tips.png?raw=true)

---

### `>> TECH_STACK.dump()`

| Layer | Tech |
|---|---|
| `LANG` | Kotlin (JVM target 23) |
| `FRAMEWORK` | Spring Boot 3.4 |
| `VIEWS` | JSP, JSTL |
| `FRONTEND` | jQuery, Bootstrap |
| `DATASTORE` | MySQL (Connector/J 9.2) |
| `BUILD` | Maven |

---

### `>> PREREQUISITES.scan()`

```
[REQUIRED] JDK 23+ ................. INSTALL
[REQUIRED] Maven ................... INSTALL
[REQUIRED] MySQL ................... INSTALL
```

---

### `>> CONFIG.init()`

Jack into `src/main/resources/application.properties` and patch your MySQL connection:

```properties
db.host=localhost
db.password=yourpassword
db.port=3306
```

---

### `>> BOOT_SEQUENCE.execute()`

**Option A** — Direct uplink:
```sh
mvn spring-boot:run
```

**Option B** — Compile and deploy the WAR payload:
```sh
mvn package
java -jar target/myDBFinal-1.0-SNAPSHOT.war
```

```
■■■■■■■■■■■■■■■■■■■■ 100%
[SYSTEM] Interface live at http://localhost:8080/db/
```

---

### `>> FEATURES.list()`

```
[01] AUTH MODULE ──────── SHA-256 hashed + salted credentials
[02] ELECTRONICS DB ───── CRUD ops with remote device control
[03] LEARNING TRACKER ─── CRUD ops with category tagging
[04] FILE UPLINK ──────── Direct upload to Raspberry Pi storage
[05] ACCESS CONTROL ───── Session-based interceptor on /app/* routes
```

---

```
╔══════════════════════════════════════════════════════════╗
║  END OF LINE                                            ║
╚══════════════════════════════════════════════════════════╝
```
