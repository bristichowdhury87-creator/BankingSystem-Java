let currentAccNo = null;
document.getElementById("show-create").addEventListener("click", function(event) {
    event.preventDefault();
    document.querySelector(".login-box").style.display = "none";
    document.querySelector(".create-box").style.display = "block";
});

document.getElementById("create-btn").addEventListener("click", function() {
    const accNo = document.getElementById("new-acc-number").value;
    const name = document.getElementById("new-acc-name").value;
    const balance = document.getElementById("new-acc-balance").value;

    if (accNo.trim() === "" || name.trim() === "" || balance.trim() === "") {
        alert("Please fill in all fields.");
        return;
    }

    fetch('/api/create', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `accNo=${accNo}&name=${encodeURIComponent(name)}&balance=${balance}`
    })
    .then(res => res.text())
    .then(result => {
        if (result === "SUCCESS") {
            alert("Account created! You can now log in.");
            document.querySelector(".create-box").style.display = "none";
            document.querySelector(".login-box").style.display = "block";
        } else {
            alert("Something went wrong.");
        }
    });
});

document.getElementById("login-btn").addEventListener("click", function() {
    const accNo = document.getElementById("acc-input").value;

    fetch('/api/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `accNo=${accNo}`
    })
    .then(res => res.text())
    .then(result => {
        const parts = result.split(":");
        if (parts[0] === "SUCCESS") {
            currentAccNo = accNo;
            document.querySelector(".login-box").style.display = "none";
            document.querySelector(".dashboard").style.display = "block";
            document.getElementById("user-name").textContent = parts[1];
            document.getElementById("user-balance").textContent = parts[2];
           loadTransactions();    
        } else {
            alert("Account not found.");
        }
    });
});

document.getElementById("deposit-btn").addEventListener("click", function() {
    const amount = document.getElementById("deposit-amount").value;

    fetch('/api/deposit', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `accNo=${currentAccNo}&amount=${amount}`
    })
    .then(res => res.text())
    .then(result => {
        const parts = result.split(":");
        if (parts[0] === "SUCCESS") {
            document.getElementById("user-balance").textContent = parts[1];
            alert("Deposit successful!");
            loadTransactions();
        } else {
            alert(parts[1] || "Deposit failed.");
        }
    });
});

document.getElementById("withdraw-btn").addEventListener("click", function() {
    const amount = document.getElementById("withdraw-amount").value;

    fetch('/api/withdraw', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `accNo=${currentAccNo}&amount=${amount}`
    })
    .then(res => res.text())
    .then(result => {
        const parts = result.split(":");
        if (parts[0] === "SUCCESS") {
            document.getElementById("user-balance").textContent = parts[1];
            alert("Withdrawal successful!");
            loadTransactions();
        } else {
            alert(parts[1] || "Withdrawal failed.");
        }
    });
});
function loadTransactions() {
    fetch(`/api/transactions?accNo=${currentAccNo}`)
    .then(res => res.text())
    .then(result => {
        const separatorIndex = result.indexOf(":");
        const status = result.substring(0,separatorIndex);
        const data = result.substring(separatorIndex + 1);
        const list = document.getElementById("transaction-list");
        list.innerHTML = "";

        if (status === "SUCCESS") {
            if (data === "No transactions yet") {
                list.innerHTML = "<li>No transactions yet</li>";
            } else {
                const transactions = data.split("|");
                transactions.forEach(function(t) {
                    const li = document.createElement("li");
                    li.textContent = t;
                    list.appendChild(li);
                });
            }
        }
    });
}