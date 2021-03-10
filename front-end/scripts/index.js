function add() {
    clearMessage();
    let id = document.querySelector('#id').value;
    let name = document.querySelector('#name').value;
    let cost = document.querySelector('#cost').value;
    let desc = document.querySelector('#desc').value;
    let obj = {
        id, name, cost, desc
    }
    const promise = fetch('http://localhost:8080/addexpense', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
            // 'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: JSON.stringify(obj)
    });
    promise.then(response => {
        response.text().then(data => {
            document.querySelector('#message').classList.add("alert", "alert-success")
            document.querySelector('#message').innerText = data;
            clearOldData();
            list();
        }).catch(err => {
            document.querySelector('#message').classList.add("alert", "alert-danger")
            document.querySelector('#message').innerText = 'Error in Add ';
            console.log(err);
        }).catch(err => {
            document.querySelector('#message').classList.add("alert", "alert-danger")
            document.querySelector('#message').innerText = 'Server Response Error';
            console.log(err);
        })
    })
}

function list() {
    const promise = fetch('http://localhost:8080/expenses', {
        method: 'GET',
    });
    promise.then(response => {
        response.text().then(data => {
            let obj = JSON.parse(data);
            if (obj.expenses.length == 0) {
                alert("No data");
            } else {
                (function () {
                    var table = document.getElementById('tbody');
                    table.innerHTML = "";
                })();
                obj.expenses.forEach(createRows);
            }
            console.log(obj.expenses);
        }).catch(err => {
            document.querySelector('#message').classList.add("alert", "alert-danger")
            document.querySelector('#message').innerText = 'Error in List ';
            console.log(err);
        }).catch(err => {
            document.querySelector('#message').classList.add("alert", "alert-danger")
            document.querySelector('#message').innerText = 'Server Response Error for lising';
            console.log(err);
        })
    })
}

function deleteById() {
    clearMessage();
    let id = document.querySelector('#id').value;
    if (!id) {
        alert("Please enter ID")
    } else {
        const promise = fetch('http://localhost:8080/deleteexpense/' + id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
        });
        promise.then(response => {
            response.text().then(data => {
                document.querySelector('#message').classList.add("alert", "alert-success")
                document.querySelector('#message').innerText = data;
                clearOldData();
                list();
            }).catch(err => {
                document.querySelector('#message').classList.add("alert", "alert-danger")
                document.querySelector('#message').innerText = 'Error in Delete ';
                console.log(err);
            }).catch(err => {
                document.querySelector('#message').classList.add("alert", "alert-danger")
                document.querySelector('#message').innerText = 'Server Response Error while deleting';
                console.log(err);
            })
        })
    }
}

function find() {
    clearMessage();
    let id = document.querySelector('#id').value;

    if (!id) {
        alert("Please enter ID")
    } else {
        const promise = fetch('http://localhost:8080/expense/' + id, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
        });
        promise.then(response => {
            response.text().then(data => {
                var obj = JSON.parse(data);
                if (obj.status && obj.status == 404) {
                    document.querySelector('#message').classList.add("alert", "alert-danger")
                document.querySelector('#message').innerText = 'Not Found';
                } else {
                    var objArr = [];
                    objArr[0] = obj;
                    (function () {
                        var table = document.getElementById('tbody');
                        table.innerHTML = "";
                    })();
                    objArr.forEach(createRows);
                    clearOldData();
                }
            }).catch(err => {
                document.querySelector('#message').classList.add("alert", "alert-danger")
                document.querySelector('#message').innerText = 'Error in Delete ';
                console.log(err);
            }).catch(err => {
                document.querySelector('#message').classList.add("alert", "alert-danger")
                document.querySelector('#message').innerText = 'Server Response Error while deleting';
                console.log(err);
            })
        })
    }
}

function createRows(expense, index) {
    var table = document.getElementById('tbody');
    var row = table.insertRow();

    var id = row.insertCell(0);
    var name = row.insertCell(1);
    var cost = row.insertCell(2);
    var descr = row.insertCell(3);

    id.innerText = expense.id;
    name.innerText = expense.name;
    cost.innerText = expense.cost;
    descr.innerText = expense.desc;
}

function clearOldData() {
    let elements = document.querySelectorAll("input");

    for (let i = 0; i < elements.length; i++) {
        elements[i].value = "";
    }

    document.getElementById("desc").value = "";
}

function clearMessage() {
    document.querySelector('#message').classList.remove("alert", "alert-success", "alert-danger");
    document.querySelector('#message').innerText = "";
}