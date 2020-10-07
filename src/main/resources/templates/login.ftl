<#import "parts/common.ftl" as common>
<#import "parts/login.ftl" as login>
<@common.page>
    <#if RequestParameters.error??>
        <div class="alert alert-danger" role="alert">
            <#if Session.SPRING_SECURITY_LAST_EXCEPTION.message == "Bad credentials">
                Неверные логин или пароль!
            </#if>
        </div>
    </#if>
    <@login.login "/login" false/>
</@common.page>