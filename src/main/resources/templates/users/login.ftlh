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
    <h1>User Login</h1>
    <form class="input-form" action="/login" method="post">
        <input type="text" name="name" placeholder="Name">
        <input type="email" name="email" placeholder="E-Mail">
        <input type="submit" value="Einloggen">
    </form>
</@base.layout>
</html>