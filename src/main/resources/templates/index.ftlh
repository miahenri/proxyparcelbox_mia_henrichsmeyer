<!DOCTYPE html>
<html lang="de">
<head lang = "de">
    <meta charset="UTF-8">
    <title>Chat zeigen</title>
    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/variables.css">
    <link rel="stylesheet" href="/css/base.css">
    <link rel="stylesheet" href="/css/components.css">
</head>
<#import "layout.ftlh" as base>
<@base.layout>
    <h1>Abgelegtes Paket anlegen</h1>
    <form class="input-form" action="/parcels" method="post">
        <input type="text" name="trackingNumber" placeholder="Bitte Sendungsnummer eingeben...">
        <input type="email" name="nachbarEmail" placeholder="E-Mail des Nachbarn (optional)">
        <input type="submit" value="Abschicken">
    </form>
</@base.layout>
</html>