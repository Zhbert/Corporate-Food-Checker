<#import "parts/common.ftl" as common>
<@common.page>
    <blockquote class="blockquote mb-3">
        <p class="mb-0">Количество обедов по датам:</p>
    </blockquote>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Дата</th>
            <th scope="col">Обед №1</th>
            <th scope="col">Обед №2</th>
        </tr>
        </thead>
        <tbody>
        <#list counters as counter>
            <tr>
                <th scope="row">${counter.date}</th>
                <td>
                    <#if counter.dinnerOne??>
                        ${counter.dinnerOne}, ${counter.dinOneCount}
                    </#if>
                </td>
                <td>
                    <#if counter.dinnerTwo??>
                        ${counter.dinnerTwo}, ${counter.dinTwoCount}
                    </#if>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</@common.page>