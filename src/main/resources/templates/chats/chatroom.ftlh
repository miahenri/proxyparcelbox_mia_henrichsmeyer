<#import "../layout.ftlh" as base>
<@base.layout>
    <body>
    <nav class="info-nav">
        <div>
            <h2>Chat Sendungsnummer: ${paket.trackingNumber}</h2>
        </div>
        <button class="user-button">Chat abonnieren</button>
    </nav>

    <section class="chatBox">
        <div class="messages">
            <div class="received">
                <p>Your received text here long text to test the chatbox messages</p>
                <span class="timestamp">15:44</span>
            </div>
            <div class="sent">
                <p>Your sent text here longer text to test the chatbox message and see how it reacts</p>
                <span class="timestamp">18:34</span>
            </div>
        </div>
    </section>

    <form class="send-message" action="/chat" method="post">
        <div>
            <input type="text" name="messageUser" placeholder="Username..">
            <input type="email" name="messageText" placeholder="Message..">
        </div>
        <input id="sending" type="submit" value="Send">
    </form>
    <footer class="main-footer">
        © Mia Henrichsmeyer 2024
    </footer>
    </body>
</@base.layout>