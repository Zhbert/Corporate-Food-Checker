<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>
<@common.page>
    ${message?ifExists}
    <@login.login "/registration" />
</@common.page>