<html>
<head lang = "de">
    <meta charset="UTF-8">
    <title>Chat zeigen</title>
    <link rel="stylesheet" href="../static/css/reset.css">
    <link rel="stylesheet" href=../static/css/variables.css">
    <link rel="stylesheet" href="../static/css/base.css">
    <link rel="stylesheet" href="../static/css/components.css">
</head>
<#import "layout.ftlh" as base>
<@base.layout>
    <h1>Abgelegtes Paket anlegen</h1>
    <form action="/parcels" method="post">
        <input type="text" name="trackingNumber" placeholder="Bitte Sendungsnummer eingeben...">
        <input type="email" name="nachbarEmail" placeholder="E-Mail des Nachbarn (optional)">
        <input type="submit" value="Abschicken">
    </form>
</@base.layout>
</html>