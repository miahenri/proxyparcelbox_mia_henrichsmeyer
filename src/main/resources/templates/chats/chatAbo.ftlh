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
<#import "../layout.ftlh" as base>
<@base.layout>
    <h1 id="trackingNumber">Chat abonnieren: ${chat.trackingNumber}</h1>
    <form class="input-form" action="/chats/${chat.trackingNumber}/subscribe" method="post">
        <input type="email" name="receiver" placeholder="E-Mail-Adresse">
        <input type="submit" id="subscribe" value="Abonnieren">
    </form>
    <script src="/scripts/chatroomScript.js"></script>
</@base.layout>
</html>