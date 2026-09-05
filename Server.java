import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Server {
    static Bank bank = new Bank();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", Server::serveStaticFile);
        server.createContext("/api/login", Server::handleLogin);
        server.createContext("/api/deposit", Server::handleDeposit);
        server.createContext("/api/withdraw", Server::handleWithdraw);
        server.createContext("/api/create", Server::handleCreate);
        server.createContext("/api/transactions", Server::handleTransactions);

        server.setExecutor(null);

        // Seed one test account so you have something to log into immediately
        bank.createAccount(101, "Test User", 1000);

        server.start();
        System.out.println("Server running at http://localhost:8080");
    }

    static void serveStaticFile(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        if (path.equals("/")) path = "/index.html";
        File file = new File("frontend" + path);

        if (file.exists()) {
            byte[] response = Files.readAllBytes(file.toPath());
            String contentType = "text/html";
            if (path.endsWith(".css")) contentType = "text/css";
            else if (path.endsWith(".js")) contentType = "application/javascript";
            exchange.getResponseHeaders().set("Content-Type", contentType);
            exchange.sendResponseHeaders(200, response.length);
            OutputStream os = exchange.getResponseBody();
            os.write(response);
            os.close();
        } else {
            String notFound = "404 Not Found";
            exchange.sendResponseHeaders(404, notFound.length());
            OutputStream os = exchange.getResponseBody();
            os.write(notFound.getBytes());
            os.close();
        }
    }

    static Map<String, String> parseParams(String data) {
        Map<String, String> params = new HashMap<>();
        if (data == null) return params;
        for (String pair : data.split("&")) {
            String[] kv = pair.split("=");
            if (kv.length == 2) params.put(kv[0], kv[1]);
        }
        return params;
    }

    static void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "text/plain");
        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    static void handleLogin(HttpExchange exchange) throws IOException {
        String body = new String(exchange.getRequestBody().readAllBytes());
        Map<String, String> params = parseParams(body);
        int accNo = Integer.parseInt(params.get("accNo"));

        Account acc = bank.findAccount(accNo);
        if (acc != null) {
            sendResponse(exchange, "SUCCESS:" + acc.getName() + ":" + acc.getBalance());
        } else {
            sendResponse(exchange, "FAIL:Account not found");
        }
    }

    static void handleDeposit(HttpExchange exchange) throws IOException {
        String body = new String(exchange.getRequestBody().readAllBytes());
        Map<String, String> params = parseParams(body);
        int accNo = Integer.parseInt(params.get("accNo"));
        double amount = Double.parseDouble(params.get("amount"));

        Account acc = bank.findAccount(accNo);
        if (acc != null) {
            acc.deposit(amount);
            sendResponse(exchange, "SUCCESS:" + acc.getBalance());
        } else {
            sendResponse(exchange, "FAIL:Account not found");
        }
    }

    static void handleWithdraw(HttpExchange exchange) throws IOException {
        String body = new String(exchange.getRequestBody().readAllBytes());
        Map<String, String> params = parseParams(body);
        int accNo = Integer.parseInt(params.get("accNo"));
        double amount = Double.parseDouble(params.get("amount"));

        Account acc = bank.findAccount(accNo);
        if (acc == null) {
            sendResponse(exchange, "FAIL:Account not found");
            return;
        }
        if (amount > 0 && amount <= acc.getBalance()) {
            acc.withdraw(amount);
            sendResponse(exchange, "SUCCESS:" + acc.getBalance());
        } else {
            sendResponse(exchange, "FAIL:Insufficient balance or invalid amount");
        }
    }

    static void handleTransactions(HttpExchange exchange) throws IOException {
    String query = exchange.getRequestURI().getQuery();
    Map<String, String> params = parseParams(query);
    int accNo = Integer.parseInt(params.get("accNo"));

    Account acc = bank.findAccount(accNo);
    if (acc == null) {
        sendResponse(exchange, "FAIL:Account not found");
        return;
    }

    ArrayList<String> transactions = acc.getTransactions();
    if (transactions.isEmpty()) {
        sendResponse(exchange, "SUCCESS:No transactions yet");
    } else {
        String joined = String.join("|", transactions);
        sendResponse(exchange, "SUCCESS:" + joined);
    }
}

    static void handleCreate(HttpExchange exchange) throws IOException {
        String body = new String(exchange.getRequestBody().readAllBytes());
        Map<String, String> params = parseParams(body);
        int accNo = Integer.parseInt(params.get("accNo"));
        String name = params.get("name");
        double balance = Double.parseDouble(params.get("balance"));

        bank.createAccount(accNo, name, balance);
        sendResponse(exchange, "SUCCESS");
    }
}