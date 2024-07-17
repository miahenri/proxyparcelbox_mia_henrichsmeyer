<#import "../layout.ftlh" as base>
<@base.layout>
    <body>
    <p><b>Sendungsnummer:</b> ${paket.trackingNumber}</p>
    <p><b>Email:</b> ${paket.nachbarMail}</p>
    <a href="http://mivs21.gm.fh-koeln.de:8080/chats/${paket.trackingNumber}">
        <img src="https://api.qrserver.com/v1/create-qr-code/?size=150x150&data={http://mivs21.gm.fh-koeln.de:8080/chats/${paket.trackingNumber}" alt="QR Code" width="200" height="200">
    </a>
    <a href="http://mivs21.gm.fh-koeln.de:8080/chats/${paket.trackingNumber}">Link zum Chat</a>
    </body>
</@base.layout>