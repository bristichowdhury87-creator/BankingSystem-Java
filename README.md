# Banking Management System

A full-stack banking application built with **Java** on the backend and **HTML, CSS, and JavaScript** on the frontend. The project simulates core banking operations — account creation, login, deposits, withdrawals, and transaction history — with a lightweight custom-built HTTP server handling all client-server communication.

## Features

- **Account Creation** — create a new account with an account number, name, and starting balance
- **Login** — log in using an account number
- **Deposit** — add funds to the logged-in account's balance
- **Withdraw** — withdraw funds from the logged-in account's balance
- **Transaction History** — view a running list of past deposits and withdrawals for the logged-in account
- **Seeded Demo Account** — account `101` ("Test User", balance `1000`) is pre-loaded so the app can be tested immediately without creating a new account

## Tech Stack

- **Backend:** Java (`com.sun.net.httpserver.HttpServer`) — a lightweight, dependency-free HTTP server built from scratch, without a framework like Spring Boot, in order to understand how HTTP request handling works at a lower level
- **Frontend:** HTML, CSS, JavaScript (vanilla, no frameworks)
- **Communication:** Browser and server exchange plain-text messages over HTTP using the Fetch API — requests are sent in `application/x-www-form-urlencoded` format, and responses are simple colon/pipe-separated strings (e.g. `SUCCESS:Test User:1000`) rather than JSON

## Project Structure

## Project Structure

BankingSystem/
├── Server.java        # HTTP server — routes requests to the right handler
├── Bank.java           # Manages the collection of accounts
├── Account.java        # Represents a single account (balance, transactions)
├── Main.java            # Entry point
├── index.html           # Frontend markup
├── style.css             # Frontend styling
└── script.js             # Frontend logic — fetch calls, DOM updates, event handling


## API Endpoints

| Endpoint             | Method | Description                          |
|-----------------------|--------|---------------------------------------|
| `/api/login`          | POST   | Log in with an account number         |
| `/api/create`         | POST   | Create a new account                  |
| `/api/deposit`        | POST   | Deposit an amount into an account     |
| `/api/withdraw`       | POST   | Withdraw an amount from an account    |
| `/api/transactions`   | GET    | Fetch transaction history for an account |


## How to Run
1. git clone <repo-url>
2. cd BankingSystem
3. javac *.java
4. java Server
5. Open http://localhost:8080

Note: This project uses Java built-in HttpServer, so after any code change you need to recompile with javac *.java and restart.


## Known Limitations
- Login with only account number, no password authentication
- Plain HTTP, not HTTPS
- Basic frontend validation only

## What I Learned
Built the full request-response cycle by hand without any framework, which helped me understand how client-server communication actually works.
