

function addRow(scheme, type, bank) {
    $('#card-table').append(`
    <tr>
        <td>${scheme}</td>
        <td>${type}</td>
        <td>${bank}</td>
    </tr>
    `);
}

function checkForUpdate() {
    fetch('/fetch_new')
    .then(response => response.json())
    .then(data => {
        if (data) {
            for (var card of data) {
                card = JSON.parse(card);
                addRow(card.scheme, card.type, card.bank);
            }
        }
        setTimeout(function(){ checkForUpdate() }, 3000);
    })
    .catch((error) => {
      console.error('Error:', error);
    });
}

setTimeout(function(){ checkForUpdate() }, 3000);