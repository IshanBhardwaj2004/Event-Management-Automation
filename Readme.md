# Event Management System

A full-stack web application for managing events, bookings, and payments with admin dashboard capabilities.


## Demo Video

[Event Management System Demo](https://github.com/user-attachments/assets/ffef42bc-071b-4a52-aaf1-5bf34845928e)


## Table of Contents

- [Motivation & Goals](#motivation--goals)
- [Features](#features)
- [Architecture Overview](#architecture-overview)
- [Tech Stack](#tech-stack)
- [Installation & Local Setup](#installation--local-setup)
- [Configuration & Environment Variables](#configuration--environment-variables)
- [Running the Application](#running-the-application)
- [Testing Strategy](#testing-strategy)
- [QA Artifacts](#qa-artifacts)
- [CI/CD](#cicd)
- [Observability & Logging](#observability--logging)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)
- [License & Contacts](#license--contacts)
- [Changelog](#changelog)
- [Roadmap](#roadmap)

## Motivation & Goals

This Event Management System provides a comprehensive platform for:
- Event discovery and booking
- User authentication and profile management
- Payment processing via Stripe
- Admin dashboard for event and user management
- Review and rating system for events

## Features

### User Features
- User registration and authentication
- Event browsing with filtering (price, category, ratings)
- Event search functionality
- Shopping cart and booking management
- Payment processing with Stripe
- User profile management
- Password reset functionality
- Event reviews and ratings

### Admin Features
- Event creation, update, and deletion
- User management and role assignment
- Booking processing and status updates
- Dashboard with analytics
- Review management

## Architecture Overview

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Frontend      │    │   Backend       │    │   Database      │
│   (React)       │◄──►│   (Express.js)  │◄──►│   (MongoDB)     │
│                 │    │                 │    │                 │
│ - User Interface│    │ - REST API      │    │ - User Data     │
│ - State Mgmt    │    │ - Authentication│    │ - Event Data    │
│ - Payment UI    │    │ - File Upload   │    │ - Booking Data  │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         │                       │                       │
         ▼                       ▼                       ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Stripe        │    │   Cloudinary    │    │   Email Service │
│   (Payments)    │    │   (File Storage)│    │   (SMTP)        │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### Data Flow
1. User interacts with React frontend
2. Frontend makes API calls to Express backend
3. Backend validates requests and interacts with MongoDB
4. File uploads handled via Cloudinary
5. Payments processed through Stripe
6. Email notifications sent via SMTP

## Tech Stack

### Backend
- **Runtime**: Node.js
- **Framework**: Express.js
- **Database**: MongoDB with Mongoose ODM
- **Authentication**: JWT tokens
- **File Storage**: Cloudinary
- **Payment Processing**: Stripe
- **Email Service**: SMTP

### Frontend
- **Framework**: React 18.2.0
- **State Management**: Redux with Redux Toolkit
- **UI Components**: Material-UI
- **Routing**: React Router DOM
- **HTTP Client**: Axios
- **Payment UI**: Stripe React components

### Testing
- **Unit Tests**: Jest + React Testing Library
- **E2E Tests**: Selenium WebDriver (Java)
- **API Testing**: Postman/Newman

### DevOps
- **CI/CD**: GitHub Actions
- **Containerization**: Docker
- **Deployment**: Heroku (Procfile present)

## Installation & Local Setup

### Prerequisites
- Node.js (v14 or higher)
- MongoDB (local or cloud instance)
- Git

### Step-by-Step Setup

1. **Clone the repository**
   ```bash
   git clone {{REPLACE_ME_WITH_REPO_URL}}
   cd Event_Management_System
   ```

2. **Install backend dependencies**
   ```bash
   npm install
   ```

3. **Install frontend dependencies**
   ```bash
   cd frontend
   npm install
   cd ..
   ```

4. **Set up environment variables**
   ```bash
   cp backend/config/config.env.example backend/config/config.env
   # Edit backend/config/config.env with your values
   ```

5. **Start MongoDB**
   ```bash
   # If using local MongoDB
   mongod
   ```

6. **Run the application**
   ```bash
   # Terminal 1 - Backend
   npm run dev
   
   # Terminal 2 - Frontend
   cd frontend
   npm start
   ```

## Configuration & Environment Variables

Create `backend/config/config.env` with the following variables:

```env
# Server Configuration
PORT=4000
NODE_ENV=development

# Database
MONGODB_URI=mongodb://localhost:27017/event_management

# JWT Configuration
JWT_SECRET=your_jwt_secret_key_here
JWT_EXPIRE=7d
COOKIE_EXPIRE=7

# Stripe Configuration
STRIPE_API_KEY=pk_test_your_stripe_publishable_key
STRIPE_SECRET_KEY=sk_test_your_stripe_secret_key

# Email Configuration
SMTP_SERVICE=gmail
SMTP_MAIL=your_email@gmail.com
SMTP_PASSWORD=your_app_password
SMTP_HOST=smtp.gmail.com
SMTP_PORT=587

# Cloudinary Configuration
CLOUDINARY_NAME=your_cloudinary_name
CLOUDINARY_API_KEY=your_cloudinary_api_key
CLOUDINARY_API_SECRET=your_cloudinary_api_secret
```

## Running the Application

### Development Mode
```bash
# Backend (Terminal 1)
npm run dev

# Frontend (Terminal 2)
cd frontend && npm start
```

### Production Mode
```bash
# Build frontend
cd frontend && npm run build

# Start production server
npm start
```

### Docker (Optional)
```bash
# Build and run with Docker Compose
docker-compose up --build
```

## Testing Strategy

### Unit Tests
```bash
# Backend unit tests
npm test

# Frontend unit tests
cd frontend && npm test
```


### Integration Tests
```bash
# Run integration tests
npm run test:integration
```


### End-to-End Tests
```bash
# Run Selenium tests
javac NewTest.java
java -cp ".:selenium-java.jar:testng.jar" NewTest
```


### API Testing
```bash
# Run API tests with Postman
newman run api-tests.postman_collection.json
```

### Performance Testing
```bash
# Load testing with Artillery
artillery run load-test.yml

# Stress testing
artillery run stress-test.yml
```

### Security Testing
```bash
# Dependency vulnerability scan
npm audit

# Static analysis
npm run lint:security
```

## QA Artifacts

### Test Plan Summary
- **Unit Tests**: 80% code coverage target
- **Integration Tests**: All API endpoints covered
- **E2E Tests**: Critical user journeys
- **Performance Tests**: Load testing for 100 concurrent users
- **Security Tests**: OWASP Top 10 compliance

### Acceptance Criteria

#### User Registration
- ✅ User can register with valid email and password
- ✅ System validates email format and password strength
- ✅ Duplicate email registration is rejected
- ✅ User receives confirmation email

#### Event Booking
- ✅ User can browse and filter events
- ✅ User can add events to cart
- ✅ Payment processing completes successfully
- ✅ Booking confirmation is generated
- ✅ User receives booking confirmation email

#### Admin Functions
- ✅ Admin can create, update, and delete events
- ✅ Admin can manage user roles
- ✅ Admin can process bookings
- ✅ Admin dashboard displays accurate metrics

---
