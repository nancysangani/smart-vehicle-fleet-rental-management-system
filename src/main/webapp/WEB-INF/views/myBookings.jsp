<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>My Bookings - Smart Vehicle Fleet</title>
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
                                <a class="nav-link" href="${pageContext.request.contextPath}/vehicles">
                                    <i class="bi bi-car-front me-1"></i>Vehicles
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" href="${pageContext.request.contextPath}/myBookings">
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
                <h2 class="fw-bold mb-4">My Bookings</h2>

                <c:if test="${not empty success}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <i class="bi bi-check-circle-fill me-2"></i>${success}
                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                    </div>
                </c:if>

                <c:if test="${empty bookings}">
                    <div class="card border-0 shadow-sm">
                        <div class="card-body text-center py-5">
                            <i class="bi bi-calendar-x text-muted" style="font-size: 4rem;"></i>
                            <h4 class="mt-3">No Bookings Yet</h4>
                            <p class="text-muted">You haven't made any bookings. Browse our vehicles and book your first
                                ride!</p>
                            <a href="${pageContext.request.contextPath}/vehicles" class="btn btn-primary">
                                <i class="bi bi-search me-2"></i>Browse Vehicles
                            </a>
                        </div>
                    </div>
                </c:if>

                <c:if test="${not empty bookings}">
                    <div class="row g-4">
                        <c:forEach items="${bookings}" var="booking">
                            <div class="col-12">
                                <div class="card shadow-sm border-0">
                                    <div class="card-body">
                                        <div class="row align-items-center">
                                            <div class="col-md-2">
                                                <img src="${pageContext.request.contextPath}${booking.vehicle.imageUrl}"
                                                    class="img-fluid rounded"
                                                    alt="${booking.vehicle.make} ${booking.vehicle.model}"
                                                    style="height: 100px; width: 100%; object-fit: cover;">
                                            </div>
                                            <div class="col-md-3">
                                                <h5 class="mb-1 fw-bold">${booking.vehicle.make}
                                                    ${booking.vehicle.model}</h5>
                                                <p class="text-muted small mb-0">${booking.vehicle.licensePlate}</p>
                                                <p class="text-muted small mb-0">${booking.vehicle.vehicleType}</p>
                                            </div>
                                            <div class="col-md-3">
                                                <small class="text-muted d-block">Booking Period</small>
                                                <strong>${booking.startDate}</strong> to
                                                <strong>${booking.endDate}</strong>
                                                <br>
                                                <small class="text-muted">Booked on: ${booking.bookingDate}</small>
                                            </div>
                                            <div class="col-md-2">
                                                <small class="text-muted d-block">Total Amount</small>
                                                <h5 class="text-primary mb-0">$${booking.totalAmount}</h5>
                                            </div>
                                            <div class="col-md-2 text-end">
                                                <c:choose>
                                                    <c:when test="${booking.status == 'CONFIRMED'}">
                                                        <span class="badge bg-success mb-2">CONFIRMED</span>
                                                        <form
                                                            action="${pageContext.request.contextPath}/cancelBooking/${booking.id}"
                                                            method="post"
                                                            onsubmit="return confirm('Are you sure you want to cancel this booking?');">
                                                            <button type="submit" class="btn btn-sm btn-outline-danger">
                                                                <i class="bi bi-x-circle me-1"></i>Cancel
                                                            </button>
                                                        </form>
                                                    </c:when>
                                                    <c:when test="${booking.status == 'CANCELLED'}">
                                                        <span class="badge bg-danger">CANCELLED</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="badge bg-warning">${booking.status}</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </div>

            <footer class="bg-dark text-white py-4 mt-5">
                <div class="container text-center">
                    <p class="mb-0">&copy; 2024 Smart Vehicle Fleet Management System. All rights reserved.</p>
                </div>
            </footer>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        </body>

        </html>