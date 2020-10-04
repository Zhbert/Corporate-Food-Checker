<#import "parts/common.ftl" as common>
<@common.page>
    <div class="container">
        <blockquote class="blockquote mb-3">
            <p class="mb-0">Выберите желаемые обеды:</p>
        </blockquote>
    </div>

    <#if choices??>
        <#list choices as choice>
            <div class="container">
                <a class="btn <#if choice.dinner??>btn-primary<#else>btn-secondary</#if> mt-3 btn-lg btn-block"
                   href="/user-choice/set?id=${choice.id}" role="button">
                    <table class="container">
                        <td width="50%"><b>${choice.date}</b><br>${choice.dayOfWeek}</td>
                        <td width="50%">
                            <#if choice.dinner??>
                                <b>${choice.dinner.name}</b>
                                <br>
                                ${choice.dinner.description}
                            </#if>
                        </td>
                    </table>
                </a>
            </div>
        </#list>
    </#if>
</@common.page>