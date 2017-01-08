<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <link rel="icon" href="/resources/img/train.png">
    <meta charset="UTF-8">
    <script src="/resources/js/jquery-1.11.1.min.js"></script>
    <script src="/resources/js/moment-with-locales.min.js"></script>
    <script src="/resources/js/bootstrap.js"></script>
    <script src="/resources/js/bootstrap-datetimepicker.min.js"></script>
    <title>${title}</title>
</head>
<body>
<div class="container">
    <div class="row">
        <span align="right">User: ${user.fio}</span>
        <a href="/user/tickets">My tickets</a>
        <div class="wrapper">
            <header class="main_header">
                <div>
                    RailTrans
                </div>
            </header>
            <nav class="main_menu">
                <a href="/routes">
                    <div> Routes </div>
                </a>
                <a href="/stations">
                    <div> Stations </div>
                </a>
                <a href="/findway">
                    <div> Tickets </div>
                </a>
                <a href="/trains">
                    <div> Trains </div>
                </a>
                <a href="/out">
                    <div> Log out </div>
                </a>
                <a >
                    <div class="train_head">  </div>
                </a>
            </nav>