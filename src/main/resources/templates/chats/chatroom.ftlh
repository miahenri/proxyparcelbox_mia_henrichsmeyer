<#import "../layout.ftlh" as base>
<@base.layout>
    <body>
    <nav>
    <div>
    <p>Chat</p>
    <p><b>Sendungsnummer:</b> ${paket.trackingNumber}</p>
    </div>
        <a class="basic-link" href="/new-parcels">Chat abonnieren</a>
    </nav>

    <div>
        <p>Textnachricht von Nachbar</p>
        <p>Eigene Textnachricht</p>
    </div>

    <div>
        <form class="input-form" action="/parcels" method="post">
            <input type="text" name="trackingNumber" placeholder="Username...">
            <input type="email" name="messageText" placeholder="Message...">
            <input type="submit" value="Abschicken">
        </form>
    </div>
    </body>
</@base.layout>