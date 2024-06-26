<#import "layout.ftlh" as base>
<@base.layout>
    <h1>Abgelegtes Paket anlegen</h1>
    <form action="/newparcelsform" method="post">
        <input type="text" name="trackingNumber" placeholder="Bitte Sendungsnummer eingeben...">
        <input type="email" name="nachbarEmail" placeholder="E-Mail des Nachbarn (optional)">
        <input type="submit" value="Abschicken">
    </form>
</@base.layout>