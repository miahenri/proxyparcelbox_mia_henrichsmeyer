<#macro layout>
    <!doctype html>
    <html lang="de">
    <meta charset="UTF-8">
    <head>
        <title>Document</title>
        <link rel="stylesheet" href="/css/reset.css">
        <link rel="stylesheet" href="/css/variables.css">
        <link rel="stylesheet" href="/css/base.css">
        <link rel="stylesheet" href="/css/components.css">
    </head>
    <body>
    <header class="main-header">
        <nav class="main-nav-bar">
            <img class="logo" src="/images/proxyparcel-logo-hell-kopie.png" alt="Logo" height="90">
            <div>
                <a class="basic-link" href="/login">Chat starten</a>
                <a class="basic-link" href="/new-parcels">Support</a>
                <a class="basic-link" href="/new-parcels">Über uns</a>
                <button class="user-button"><a href="/login">Login</a></button>
                <button class="user-button">Registrierung</button>
            </div>
        </nav>
    </header>
    <main>
        <#nested>
    </main>
    <footer class="main-footer">
        © Mia Henrichsmeyer 2024
    </footer>
    </body>
    </html>
</#macro>