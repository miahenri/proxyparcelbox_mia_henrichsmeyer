document.addEventListener('DOMContentLoaded', function() {
    const ws = new WebSocket("/messages");
    const echo = document.querySelector('.messages');
    const input = document.querySelector('#messageText');
    const sendButton = document.getElementById('sending');
    const trackingNumber = document.getElementById('trackingNumber').textContent.split(': ')[1];

    function getTimestamp() {
        const currentTime = new Date();
        return currentTime.toLocaleTimeString('de-DE', {hour: '2-digit', minute: '2-digit'});
    }

    function appendMessage(type, text, timestamp) {
        let messageDiv = document.createElement('div');
        messageDiv.classList.add(type);

        let messageText = document.createElement('p');
        messageText.textContent = text;
        messageDiv.appendChild(messageText);

        let timestampSpan = document.createElement('span');
        timestampSpan.classList.add('timestamp');
        timestampSpan.textContent = timestamp || getTimestamp();
        messageDiv.appendChild(timestampSpan);

        echo.appendChild(messageDiv);
    }

    ws.onmessage = function (message) {
        console.log("Nachricht angekommen: " + message.data);
        appendMessage('received', message.data);
    };

    function sendMessage() {
        const messageText = input.value;
        if (!messageText.trim()) return; // Prevent sending empty messages

        appendMessage('sent', messageText);

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

    sendButton.addEventListener('click', sendMessage);
    input.addEventListener('keypress', function (event) {
        if (event.key === 'Enter') {
            sendMessage();
        }
    });
});