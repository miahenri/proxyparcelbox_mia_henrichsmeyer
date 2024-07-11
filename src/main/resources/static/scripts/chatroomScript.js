const ws = new WebSocket("/chats");
const echo = document.querySelector('.messages');
const trackingNumber = document.getElementById('trackingNumber').textContent.split(': ')[1];

ws.onmessage = function (message) {
    console.log("Nachricht angekommen: " + message.data);
    let receivedDiv = document.createElement('div');
    receivedDiv.classList.add('received');

    let messageText = document.createElement('p');
    messageText.textContent = message.data;
    receivedDiv.appendChild(messageText);

    let timestampSpan = document.createElement('span');
    timestampSpan.classList.add('timestamp');
    timestampSpan.textContent = getTimestamp();
    receivedDiv.appendChild(timestampSpan);

    echo.appendChild(receivedDiv);
};

function sendMessage() {
    const input = document.querySelector('#messageText');
    const messageText = input.value;

    // let date = new Date();
    let timestamp = new Date().toLocaleTimeString('de-DE', {hour: '2-digit', minute: '2-digit'});

    const messageHtml = '<div class="sent">' +
        '<p>' + messageText + '</p>' +
        '<span class="timestamp">' + timestamp + '</span>' +
        '</div>';

    echo.innerHTML += messageHtml;

    const trackingNumber = document.getElementById('trackingNumber').textContent.split(': ')[1];

    const message = {
        sender: 'User',
        text: messageText
    };

    fetch('/chats/' + trackingNumber, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(message)
    }).then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
    }).catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
    });

    ws.send(messageText);

    input.value = '';
}

function getTimestamp() {
    const currentTime = new Date();
    return currentTime.toLocaleTimeString('de-DE', {hour: '2-digit', minute: '2-digit'});
}

document.getElementById('sending').addEventListener('click', sendMessage)
document.querySelector('#messageText').addEventListener('keypress', function (event) {
    if (event.key === 'Enter') {
        sendMessage();
        event.preventDefault(); // Verhindert das Standardverhalten der Entertaste (z.B. neues Zeile in einem Textfeld)
    }
});