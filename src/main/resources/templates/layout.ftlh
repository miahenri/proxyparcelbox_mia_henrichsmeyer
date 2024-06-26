<#macro layout>
    <!doctype html>
    <html lang="de">
    <meta charset="UTF-8">
    <head>
        <title>Document</title>
    </head>
    <body>
    <header>
        <nav>
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