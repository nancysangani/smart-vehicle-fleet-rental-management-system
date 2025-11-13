# Smart Vehicle Fleet & Rental Management System

A comprehensive enterprise-grade vehicle fleet and rental management system built with Java 17, Spring MVC, Hibernate, and Swing.

## 🚀 Tech Stack

### Backend
- **Java 17** - Core programming language
- **Spring MVC 5.3.30** - Web framework
- **Spring Core** - Dependency Injection & IoC
- **Spring ORM** - Hibernate integration
- **Hibernate 5.6.15** - ORM framework with JPA annotations
- **MySQL 8.0** - Database (via XAMPP)
- **JDBC** - Database connectivity
- **Maven** - Build automation

### Frontend (Web)
- **JSP** - Server-side rendering
- **JSTL** - JSP Standard Tag Library
- **Bootstrap 5** - CSS framework
- **Bootstrap Icons** - Icon library
- **HTML5/CSS3/JavaScript** - Core web technologies

### Desktop Application
- **Java Swing** - GUI framework for admin desktop app

### Server
- **Jetty 9.4** - Embedded servlet container

## 📁 Project Structure

```
smart-vehicle-fleet/
├── pom.xml
├── README.md
├── sql/
│   └── schema.sql
├── src/
│   └── main/
│       ├── java/
│       │   └── com/fleet/
│       │       ├── model/          # Entity classes
│       │       ├── dao/            # Data Access Layer
│       │       ├── service/        # Business Logic Layer
│       │       ├── controller/     # Spring MVC Controllers
│       │       └── swing/          # Swing Desktop App
│       ├── resources/
│       │   └── hibernate.cfg.xml
│       └── webapp/
│           ├── WEB-INF/
│           │   ├── web.xml
│           │   ├── dispatcher-servlet.xml
│           │   └── views/          # JSP pages
│           └── resources/
│               ├── css/
│               └── js/
```

## 🎯 Features

### User Module (Web Application)
- ✅ User Registration & Authentication
- ✅ Browse Available Vehicles
- ✅ Search & Filter Vehicles
- ✅ Vehicle Details View
- ✅ Book Vehicles with Date Selection
- ✅ View Booking History
- ✅ Cancel Bookings
- ✅ Responsive UI with Bootstrap 5

### Admin Module (Swing Desktop)
- ✅ Admin Login
- ✅ Add New Vehicles
- ✅ Update Vehicle Information
- ✅ Delete Vehicles
- ✅ View All Vehicles
- ✅ Toggle Vehicle Status (Available/Booked)
- ✅ View All Bookings
- ✅ Modern Swing UI

## 🗄️ Database Schema

### Tables
- **users** - Customer information
- **vehicles** - Vehicle inventory
- **bookings** - Rental bookings
- **admins** - Administrator accounts

## 🛠️ Setup Instructions

### Prerequisites
- Java 17 JDK installed
- Maven 3.6+ installed
- XAMPP with MySQL running
- Git (optional)

### 1. Database Setup

1. Start XAMPP and ensure MySQL is running
2. Open phpMyAdmin or MySQL command line
3. Run the SQL script:

```bash
mysql -u root -p < sql/schema.sql
```

Or manually:
- Create database: `fleet_management`
- Execute the SQL statements in `sql/schema.sql`

### 2. Configure Database Connection

The default configuration uses:
- **Host:** localhost:3306
- **Database:** fleet_management
- **Username:** root
- **Password:** (empty)

To change these, edit:
- `src/main/webapp/WEB-INF/dispatcher-servlet.xml`
- `src/main/resources/hibernate.cfg.xml`

### 3. Build the Project

```bash
mvn clean install
```

### 4. Run the Web Application

```bash
mvn jetty:run
```

The application will start at: **http://localhost:8080/fleet**

### 5. Run the Swing Admin Application

In a new terminal:

```bash
mvn exec:java -Dexec.mainClass="com.fleet.swing.AdminLogin"
```

## 👥 Default Credentials

### Web Application (Users)
- **Username:** admin
- **Password:** admin123

OR

- **Username:** nancy29
- **Password:** nancy29

### Desktop Application (Admins)
- **Username:** admin
- **Password:** admin123

OR

- **Username:** manager
- **Password:** manager123

## 📱 Web Application URLs

| Page | URL |
|------|-----|
| Login | http://localhost:8080/fleet/login |
| Register | http://localhost:8080/fleet/register |
| Home | http://localhost:8080/fleet/home |
| Vehicles | http://localhost:8080/fleet/vehicles |
| My Bookings | http://localhost:8080/fleet/myBookings |

## 🎨 UI Features

### Web Application
- Modern, clean Bootstrap 5 design
- Responsive layout for all devices
- Card-based vehicle display
- Real-time booking calculations
- Professional color scheme
- Interactive hover effects
- Alert notifications
- Form validation

### Swing Application
- Clean, modern interface
- Sidebar navigation
- Table-based data display
- Modal dialogs for confirmations
- Color-coded buttons
- Professional styling

## 🔧 Architecture

### Design Pattern: MVC + Service Layer

```
┌─────────────┐
│ Controllers │ ← Spring MVC Controllers
└──────┬──────┘
       │
┌──────▼──────┐
│  Services   │ ← Business Logic (@Service)
└──────┬──────┘
       │
┌──────▼──────┐
│    DAOs     │ ← Data Access Layer (@Repository)
└──────┬──────┘
       │
┌──────▼──────┐
│  Hibernate  │ ← ORM Layer
└──────┬──────┘
       │
┌──────▼──────┐
│    MySQL    │ ← Database
└─────────────┘
```

## 📦 Key Dependencies

```xml
- Spring MVC: 5.3.30
- Spring ORM: 5.3.30
- Hibernate Core: 5.6.15.Final
- MySQL Connector: 8.0.33
- JSTL: 1.2
- Servlet API: 4.0.1
- Jetty Maven Plugin: 9.4.51
```

## 🔒 Security Features

- Password storage (Note: Use BCrypt in production)
- Session management
- Input validation (client & server-side)
- SQL injection prevention via Hibernate HQL
- CSRF protection (can be enhanced)

## 🚀 Deployment

### WAR Deployment
```bash
mvn clean package
```
Deploy the generated WAR file (`target/smart-vehicle-fleet.war`) to any servlet container.

### Jetty (Development)
```bash
mvn jetty:run
```

## 📝 Development Notes

### Adding New Features
1. Create entity in `model/` package
2. Create DAO interface and implementation
3. Create Service interface and implementation
4. Create Controller with request mappings
5. Create JSP view
6. Update navigation

### Database Changes
- Hibernate is set to `update` mode
- Changes to entities auto-update schema
- For production, use migration tools

## 🐛 Troubleshooting

### Port Already in Use
```bash
# Kill process on port 8080
lsof -ti:8080 | xargs kill -9  # Mac/Linux
netstat -ano | findstr :8080   # Windows
```

### Database Connection Failed
- Ensure MySQL is running
- Check credentials in config files
- Verify database exists

### Hibernate Errors
- Check entity mappings
- Verify foreign key relationships
- Check transaction boundaries

## 📚 Additional Resources

- [Spring MVC Documentation](https://docs.spring.io/spring-framework/docs/5.3.x/reference/html/web.html)
- [Hibernate Documentation](https://hibernate.org/orm/documentation/5.6/)
- [Bootstrap 5 Documentation](https://getbootstrap.com/docs/5.3/)
- [Maven Documentation](https://maven.apache.org/guides/)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

This project is created for educational purposes.

## 👨‍💻 Author

Nancy Sangani

---

**Happy Coding! 🚗💨**