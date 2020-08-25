<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>
<@common.page>
Login page
    <@login.login "/login" />
<div><a href="/registration">Add new user</a></div>
</@common.page>