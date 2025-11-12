<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Book Vehicle - Smart Vehicle Fleet</title>
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
                <div class="row">
                    <div class="col-md-6">
                        <div class="card shadow-sm border-0">
                            <img src="${pageContext.request.contextPath}${vehicle.imageUrl}" class="card-img-top"
                                alt="${vehicle.make} ${vehicle.model}" style="height: 300px; object-fit: cover;">
                            <div class="card-body">
                                <h3 class="card-title fw-bold">${vehicle.make} ${vehicle.model}</h3>
                                <p class="text-muted">${vehicle.year} | ${vehicle.vehicleType}</p>

                                <div class="row g-3 mb-3">
                                    <div class="col-6">
                                        <div class="d-flex align-items-center">
                                            <i class="bi bi-fuel-pump text-primary fs-4 me-2"></i>
                                            <div>
                                                <small class="text-muted d-block">Fuel Type</small>
                                                <strong>${vehicle.fuelType}</strong>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <div class="d-flex align-items-center">
                                            <i class="bi bi-gear text-primary fs-4 me-2"></i>
                                            <div>
                                                <small class="text-muted d-block">Transmission</small>
                                                <strong>${vehicle.transmission}</strong>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <div class="d-flex align-items-center">
                                            <i class="bi bi-people text-primary fs-4 me-2"></i>
                                            <div>
                                                <small class="text-muted d-block">Seating</small>
                                                <strong>${vehicle.seatingCapacity} Seats</strong>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <div class="d-flex align-items-center">
                                            <i class="bi bi-credit-card text-primary fs-4 me-2"></i>
                                            <div>
                                                <small class="text-muted d-block">License Plate</small>
                                                <strong>${vehicle.licensePlate}</strong>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="alert alert-light border">
                                    <h5 class="mb-0">Daily Rate: <span class="text-primary">$${vehicle.dailyRate}</span>
                                    </h5>
                                </div>

                                <p class="text-muted">${vehicle.description}</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="card shadow-sm border-0">
                            <div class="card-body p-4">
                                <h4 class="card-title fw-bold mb-4">Book This Vehicle</h4>

                                <c:if test="${not empty error}">
                                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                        <i class="bi bi-exclamation-triangle-fill me-2"></i>${error}
                                        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
                                    </div>
                                </c:if>

                                <form action="${pageContext.request.contextPath}/bookVehicle" method="post"
                                    id="bookingForm">
                                    <input type="hidden" name="vehicleId" value="${vehicle.id}">

                                    <div class="mb-3">
                                        <label for="startDate" class="form-label">Start Date</label>
                                        <input type="date" class="form-control" id="startDate" name="startDate"
                                            required>
                                    </div>

                                    <div class="mb-3">
                                        <label for="endDate" class="form-label">End Date</label>
                                        <input type="date" class="form-control" id="endDate" name="endDate" required>
                                    </div>

                                    <div class="alert alert-info">
                                        <h6 class="mb-2">Booking Summary</h6>
                                        <div class="d-flex justify-content-between">
                                            <span>Vehicle:</span>
                                            <strong>${vehicle.make} ${vehicle.model}</strong>
                                        </div>
                                        <div class="d-flex justify-content-between">
                                            <span>Daily Rate:</span>
                                            <strong>$${vehicle.dailyRate}</strong>
                                        </div>
                                        <div class="d-flex justify-content-between">
                                            <span>Duration:</span>
                                            <strong id="duration">-</strong>
                                        </div>
                                        <hr>
                                        <div class="d-flex justify-content-between">
                                            <span class="fw-bold">Total Amount:</span>
                                            <strong class="text-primary" id="totalAmount">$0.00</strong>
                                        </div>
                                    </div>

                                    <button type="submit" class="btn btn-primary w-100 py-2">
                                        <i class="bi bi-check-circle me-2"></i>Confirm Booking
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <footer class="bg-dark text-white py-4 mt-5">
                <div class="container text-center">
                    <p class="mb-0">&copy; 2024 Smart Vehicle Fleet Management System. All rights reserved.</p>
                </div>
            </footer>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
            <script>
                const dailyRate = ${ vehicle.dailyRate };
                const startDateInput = document.getElementById('startDate');
                const endDateInput = document.getElementById('endDate');

                const today = new Date().toISOString().split('T')[0];
                startDateInput.min = today;
                endDateInput.min = today;

                function calculateTotal() {
                    const startDate = new Date(startDateInput.value);
                    const endDate = new Date(endDateInput.value);

                    if (startDate && endDate && endDate >= startDate) {
                        const days = Math.ceil((endDate - startDate) / (1000 * 60 * 60 * 24)) + 1;
                        const total = days * dailyRate;
                        document.getElementById('duration').textContent = days + ' day(s)';
                        document.getElementById('totalAmount').textContent = '$' + total.toFixed(2);
                    } else {
                        document.getElementById('duration').textContent = '-';
                        document.getElementById('totalAmount').textContent = '$0.00';
                    }
                }

                startDateInput.addEventListener('change', calculateTotal);
                endDateInput.addEventListener('change', calculateTotal);
            </script>
        </body>

        </html>