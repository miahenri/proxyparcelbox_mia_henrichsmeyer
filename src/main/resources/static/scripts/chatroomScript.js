document.addEventListener('DOMContentLoaded', function() {
    const ws = new WebSocket("/messages");
    const echo = document.querySelector('.messages');
    const input = document.querySelector('#messageText');
    const email = document.querySelector('#messageEmail');
    const sendButton = document.getElementById('sending');
    const trackingNumber = document.getElementById('trackingNumber').textContent.split(': ')[1];
    const chatOwner = document.getElementById('chatOwner').textContent.split(': ')[1];

    function getTimestamp() {
        const currentTime = new Date();
        return currentTime.toLocaleTimeString('de-DE', { hour: '2-digit', minute: '2-digit' });
    }

    function appendMessage(type, text, timestamp, sender) {
        if(sender === chatOwner) {
            console.log('Comparing sender(' + sender + ' and chatOwner ' + chatOwner +'. Same');
            type = 'sent';
        } else {
            console.log('Comparing sender(' + sender + ' and chatOwner' + chatOwner + '. Different');
            type = 'received';
        }

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
        echo.scrollTop = echo.scrollHeight; // Scroll to the bottom
    }

    // WebSocket event handlers
    ws.onopen = function() {
        console.log("WebSocket connection opened");
    };

    ws.onerror = function(error) {
        console.error("WebSocket error observed:", error);
    };

    ws.onmessage = function(message) {
        console.log("WebSocket message received: " + message.data);
        appendMessage('received', message.data, getTimestamp(), message.email);
    };

    ws.onclose = function(event) {
        console.log('WebSocket is closed now.', event);
    };

    function sendMessage() {
        const messageText = input.value;
        if (!messageText.trim()) return; // Prevent sending empty messages
        const messageEmail = email.value;

        appendMessage('sent', messageText, getTimestamp(), messageEmail);

        const message = {
            sender: 'User',
            email: messageEmail,
            text: messageText
        };

        console.log("Sending message to server:", message);

        fetch('/chats/' + trackingNumber, 

            {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(message)
        }).then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            console.log('Message saved to chat history');
        }).catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });

        ws.send(messageText);
        input.value = '';
    }

    sendButton.addEventListener('click', sendMessage);
    input.addEventListener('keypress', function(event) {
        if (event.key === 'Enter') {
            event.preventDefault();
            sendMessage();
        }
    });
});