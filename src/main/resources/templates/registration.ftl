<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>
<@common.page>
    ${message!" "}
    <@login.login "/registration" />
</@common.page>