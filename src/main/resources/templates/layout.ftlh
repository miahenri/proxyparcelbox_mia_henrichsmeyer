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
            <a href="/"><img class="logo" src="/images/proxyparcel-logo-hell-kopie.png" alt="Logo" height="90"></a>
            <div>
                <a class="basic-link" href="/">Chat starten</a>
                <a class="basic-link" href="/support">Support</a>
                <a class="basic-link" href="/ueberuns">Über uns</a>
                <button class="user-button"><a href="/login">Login</a></button>
                <button class="user-button"><a href="/login">Registrierung</a></button>
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