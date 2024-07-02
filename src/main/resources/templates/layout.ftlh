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
            <div>
                <a class="basic-link" href="/new-parcels">Chat starten</a>
                <a class="basic-link" href="/new-parcels">Support</a>
                <a class="basic-link" href="/new-parcels">Über uns</a>
            </div>
            <div>
                <button class="user-button">Login</button>
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