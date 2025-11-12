<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Vehicles - Smart Vehicle Fleet</title>
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
                                <a class="nav-link" href="${pageContext.request.contextPath}/home">
                                    <i class="bi bi-house-fill me-1"></i>Home
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="${pageContext.request.contextPath}/vehicles">
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

            <div class="container my-5">
                <div class="row mb-4">
                    <div class="col-md-8">
                        <h2 class="fw-bold">Available Vehicles</h2>
                        <p class="text-muted">Choose from our wide selection of premium vehicles</p>
                    </div>
                    <div class="col-md-4">
                        <form action="${pageContext.request.contextPath}/vehicles" method="get">
                            <div class="input-group">
                                <input type="text" class="form-control" name="search" placeholder="Search vehicles..."
                                    value="${search}">
                                <button class="btn btn-primary" type="submit">
                                    <i class="bi bi-search"></i>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>

                <c:if test="${empty vehicles}">
                    <div class="alert alert-info text-center">
                        <i class="bi bi-info-circle me-2"></i>No vehicles found matching your search criteria.
                    </div>
                </c:if>

                <div class="row g-4">
                    <c:forEach items="${vehicles}" var="vehicle">
                        <div class="col-md-4">
                            <div class="card h-100 shadow-sm border-0 vehicle-card">
                                <img src="${pageContext.request.contextPath}${vehicle.imageUrl}" class="card-img-top"
                                    alt="${vehicle.make} ${vehicle.model}" style="height: 220px; object-fit: cover;">
                                <div class="card-body">
                                    <span class="badge bg-success mb-2">${vehicle.status}</span>
                                    <h5 class="card-title fw-bold">${vehicle.make} ${vehicle.model}</h5>
                                    <p class="text-muted small mb-2">${vehicle.year} | ${vehicle.vehicleType}</p>

                                    <div class="row g-2 mb-3">
                                        <div class="col-6">
                                            <small class="text-muted">
                                                <i class="bi bi-fuel-pump me-1"></i>${vehicle.fuelType}
                                            </small>
                                        </div>
                                        <div class="col-6">
                                            <small class="text-muted">
                                                <i class="bi bi-gear me-1"></i>${vehicle.transmission}
                                            </small>
                                        </div>
                                        <div class="col-6">
                                            <small class="text-muted">
                                                <i class="bi bi-people me-1"></i>${vehicle.seatingCapacity} Seats
                                            </small>
                                        </div>
                                        <div class="col-6">
                                            <small class="text-muted">
                                                <i class="bi bi-credit-card me-1"></i>${vehicle.licensePlate}
                                            </small>
                                        </div>
                                    </div>

                                    <p class="card-text text-muted small">${vehicle.description}</p>

                                    <div class="d-flex justify-content-between align-items-center mt-3">
                                        <span
                                            class="h5 text-primary mb-0">$${vehicle.dailyRate}<small>/day</small></span>
                                        <a href="${pageContext.request.contextPath}/vehicle/${vehicle.id}"
                                            class="btn btn-primary">
                                            <i class="bi bi-calendar-plus me-1"></i>Book Now
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
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