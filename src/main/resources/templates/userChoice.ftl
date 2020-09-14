<#import "parts/common.ftl" as common>
<@common.page>
    <blockquote class="blockquote mb-3">
        <p class="mb-0">Выберите желаемые обеды:</p>
    </blockquote>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Дата</th>
            <th scope="col">Возможные обеды</th>
            <th scope="col">Описание</th>
            <th scope="col">Функции</th>
        </tr>
        </thead>
        <#if choices??>
            <tbody>
            <#list choices as choice>
                <tr>
                    <th scope="row">${choice.date}</th>
                    <td>
                        <ul class="list-group">
                            <li class="list-group-item">
                                <#list dsa as dsa>
                                    <#if dsa??>
                                        <#if dsa.dinnerDate == choice.date>
                                            <#if dsa.dinner??>
                                                ${dsa.dinner.name}
                                            </#if>
                                        </#if>
                                    </#if>
                                </#list>
                            </li>
                            <li class="list-group-item">
                                <#list dsa as dsa>
                                    <#if dsa??>
                                        <#if dsa.dinnerDate == choice.date>
                                            <#if dsa.dinnerTwo??>
                                                ${dsa.dinnerTwo.name}
                                            </#if>
                                        </#if>
                                    </#if>
                                </#list>
                            </li>
                        </ul>
                    </td>
                    <td>
                        <ul class="list-group">
                            <li class="list-group-item">
                                <#list dsa as dsa>
                                    <#if dsa??>
                                        <#if dsa.dinnerDate == choice.date>
                                            <#if dsa.dinner??>
                                                ${dsa.dinner.description}
                                            </#if>
                                        </#if>
                                    </#if>
                                </#list>
                            </li>
                            <li class="list-group-item">
                                <#list dsa as dsa>
                                    <#if dsa??>
                                        <#if dsa.dinnerDate == choice.date>
                                            <#if dsa.dinnerTwo??>
                                                ${dsa.dinnerTwo.description}
                                            </#if>
                                        </#if>
                                    </#if>
                                </#list>
                            </li>
                        </ul>
                    </td>
                    <td><a class="btn btn-primary" href="/admin/dinner-set?id=${choice.id}"
                           role="button">Изменить</a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </#if>
    </table>
</@common.page>