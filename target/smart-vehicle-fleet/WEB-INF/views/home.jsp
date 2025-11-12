<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - Smart Vehicle Fleet</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/custom.css">
</head>
<body class="bg-light">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home">
                <i class="bi bi-car-front-fill me-2"></i>Smart Vehicle Fleet
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="${pageContext.request.contextPath}/home">
                            <i class="bi bi-house-fill me-1"></i>Home
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/vehicles">
                            <i class="bi bi-car-front me-1"></i>Vehicles
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/myBookings">
                            <i class="bi bi-calendar-check me-1"></i>My Bookings
                        </a>
                    </li>
                    <li class="nav-item">
                        <span class="nav-link text-white">
                            <i class="bi bi-person-circle me-1"></i>${user.username}
                        </span>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/logout">
                            <i class="bi bi-box-arrow-right me-1"></i>Logout
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="hero-section bg-primary text-white py-5">
        <div class="container text-center">
            <h1 class="display-4 fw-bold mb-3">Welcome to Smart Vehicle Fleet</h1>
            <p class="lead mb-4">Find and rent the perfect vehicle for your journey</p>
            <a href="${pageContext.request.contextPath}/vehicles" class="btn btn-light btn-lg">
                <i class="bi bi-search me-2"></i>Browse Vehicles
            </a>
        </div>
    </div>

    <div class="container my-5">
        <div class="row g-4 mb-5">
            <div class="col-md-4">
                <div class="card border-0 shadow-sm text-center h-100">
                    <div class="card-body p-4">
                        <i class="bi bi-shield-check text-primary" style="font-size: 3rem;"></i>
                        <h5 class="card-title mt-3">Safe & Secure</h5>
                        <p class="card-text text-muted">All vehicles are regularly inspected and maintained</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card border-0 shadow-sm text-center h-100">
                    <div class="card-body p-4">
                        <i class="bi bi-wallet2 text-primary" style="font-size: 3rem;"></i>
                        <h5 class="card-title mt-3">Best Prices</h5>
                        <p class="card-text text-muted">Competitive rates with no hidden charges</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card border-0 shadow-sm text-center h-100">
                    <div class="card-body p-4">
                        <i class="bi bi-headset text-primary" style="font-size: 3rem;"></i>
                        <h5 class="card-title mt-3">24/7 Support</h5>
                        <p class="card-text text-muted">Round-the-clock customer assistance</p>
                    </div>
                </div>
            </div>
        </div>

        <h2 class="text-center mb-4 fw-bold">Featured Vehicles</h2>
        <div class="row g-4">
            <c:forEach items="${vehicles}" var="vehicle" begin="0" end="5">
                <div class="col-md-4">
                    <div class="card h-100 shadow-sm border-0">
                        <img src="${vehicle.imageUrl}" class="card-img-top" alt="${vehicle.make} ${vehicle.model}" style="height: 200px; object-fit: cover;">
                        <div class="card-body">
                            <h5 class="card-title">${vehicle.make} ${vehicle.model}</h5>
                            <p class="card-text text-muted">${vehicle.year} | ${vehicle.vehicleType}</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <span class="h5 text-primary mb-0">$${vehicle.dailyRate}/day</span>
                                <a href="${pageContext.request.contextPath}/vehicle/${vehicle.id}" class="btn btn-primary btn-sm">
                                    <i class="bi bi-calendar-plus me-1"></i>Book Now
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="text-center mt-4">
            <a href="${pageContext.request.contextPath}/vehicles" class="btn btn-outline-primary btn-lg">
                View All Vehicles <i class="bi bi-arrow-right ms-2"></i>
            </a>
        </div>
    </div>

    <footer class="bg-dark text-white py-4 mt-5">
        <div class="container text-center">
            <p class="mb-0">&copy; 2024 Smart Vehicle Fleet Management System. All rights reserved.</p>
        </div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>