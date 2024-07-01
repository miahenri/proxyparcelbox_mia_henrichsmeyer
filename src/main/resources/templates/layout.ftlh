<#macro layout>
    <!doctype html>
    <html lang="de">
    <meta charset="UTF-8">
    <head>
        <title>Document</title>
<#--        <link rel="stylesheet" href="/css/reset.css">-->
        <link rel="stylesheet" href=/css/variables.css">
        <link rel="stylesheet" href="/css/base.css">
        <link rel="stylesheet" href="/css/components.css">
    </head>
    <body>
    <header class="main-header">
        <nav class="main-nav-bar">
            <a href="/new-parcels">Chat starten</a>
            <a href="/new-parcels">Support</a>
            <a href="/new-parcels">Über uns</a>
            <button>Login</button>
            <button>Registrierung</button>
        </nav>
    </header>
    <hr>
    <main>
        <#nested>
    </main>
    <hr>
    <footer>
        © Mia Henrichsmeyer 2024
    </footer>
    </body>
    </html>
</#macro>