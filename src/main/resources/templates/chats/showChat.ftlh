<#import "../layout.ftlh" as base>
<@base.layout>
    <body>
    <h1 class="headline">Sendungsnummer: ${paket.trackingNumber}</h1>
    <section class="text-section">
        <p>Über den folgenden Link gelangen Sie zum Chat mit Ihrem Nachbarn:  <a href="http://mivs21.gm.fh-koeln.de:8080/chats/${paket.trackingNumber}">Link zum Chat</a></p>
        <p>Alternativ können Sie auch den QR-Code scannen:</p>
        <a href="/http://mivs21.gm.fh-koeln.de:8080/chats/${paket.trackingNumber}">
            <img class="qr-code" src="https://api.qrserver.com/v1/create-qr-code/?size=150x150&data={http://mivs21.gm.fh-koeln.de:8080/chats/${paket.trackingNumber}"
                 alt="QR Code" width="120" height="120">
        </a>
    </section>
    </body>
</@base.layout>