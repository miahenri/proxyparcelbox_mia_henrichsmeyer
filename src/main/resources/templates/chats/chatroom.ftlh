<#import "../layout.ftlh" as base>
<@base.layout>
    <body>
    <nav class="info-nav">
        <div>
            <h2 id="trackingNumber">Chat Sendungsnummer: ${chat.trackingNumber}</h2>
        </div>
        <button class="user-button">Chat abonnieren</button>
    </nav>

    <section class="chatBox">
        <div class="messages">
            <#-- Loop over the messages -->
            <#list messages as message>
            <#-- Check if the message was sent or received -->
                <#if message.sender == "User">
                    <div class="sent">
                        <p>${message.text}</p>
                        <span class="timestamp">${message.createdAt}</span>
                    </div>
                <#else>
                    <div class="received">
                        <p class="sender">${message.text}</p>
                        <span class="timestamp">${message.createdAt}</span>
                    </div>
                </#if>
            </#list>
        </div>
    </section>

    <form class="send-message" action="/chat" method="post">
        <div>
            <input type="text" id="messageEmail" placeholder="E-Mail..">
            <input type="text" id="messageText"  placeholder="Message..">
        </div>
        <button class="user-button" id="sending" type="button">Send</button>
    </form>
    <script src="/scripts/chatroomScript.js"></script>
    </body>
</@base.layout>