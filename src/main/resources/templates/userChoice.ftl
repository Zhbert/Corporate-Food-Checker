<#import "parts/common.ftl" as common>
<@common.page>
    <div class="container">
        <blockquote class="blockquote mb-3">
            <p class="mb-0">Выберите желаемые обеды:<br>
            (Выбор на сегодняшнюю дату возможен только до 10:00)</p>
        </blockquote>
    </div>

    <#if choices??>
        <#list choices as choice>
            <div class="container">
                <a class="btn
                <#if isAfter==false>
                    <#if choice.dinner??>
                            btn-primary mt-3 btn-lg btn-block"
                    <#else>btn-secondary mt-3 btn-lg btn-block"
                    </#if>
                <#else>
                    btn-danger mt-3 btn-lg btn-block disabled"
                    <#assign isAfter = false>
                </#if>
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
    <div class="mt-3"></div>
</@common.page>