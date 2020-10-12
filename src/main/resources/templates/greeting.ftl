<#import "parts/common.ftl" as common>
<#include "parts/security.ftl">
<@common.page>
    <div id="muderolisag" align="center">
        <h2>Corporate Food Checker</h2><br>
        Система для выбора корпоративных обедов на предприятии<br>
        <#if isUser==false>
            Для начала работы <a href="/login">войдите</a> в систему<br>
        </#if>
        <br>Copyright © 2020 <a href="https://zhbert.ru">Zhbert</a>
    </div>
</@common.page>