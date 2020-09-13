<#import "parts/common.ftl" as common>
<@common.page>
    <blockquote class="blockquote mb-3">
        <p class="mb-0">Установка обеда на ${date}:</p>
    </blockquote>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Дата</th>
            <th scope="col">Тип обеда</th>
            <th scope="col">Функции</th>
        </tr>
        </thead>
        <#if dinnerSet??>
            <tbody>
            <form action="/admin/dinner-set/change" method="post">
                <tr>
                    <th scope="row">${dinnerSet.dinnerDate}
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <input type="hidden" name="set_id" value="${dinnerSet.id}"/>
                    </th>
                    <td>
                        <ul class="list-group">
                            <li class="list-group-item">
                                <div class="mb-1">Первый обед:</div>
                                <select class="custom-select" name="firstDinner">
                                    <#list dinners as dinner>
                                        <option
                                                <#if dinnerSet.dinner??>
                                                    <#if dinnerSet.dinner.name == dinner.name> selected </#if>
                                                </#if>
                                                value="${dinner.id}">${dinner.name}</option>
                                    </#list>
                                </select>
                            </li>
                            <li class="list-group-item">
                                <div class="mb-1">Второй обед:</div>
                                <select class="custom-select" name="secondDinner">
                                    <#list dinners as dinner>
                                        <option
                                                <#if dinnerSet.dinnerTwo??>
                                                    <#if dinnerSet.dinnerTwo.name == dinner.name> selected </#if>
                                                </#if>
                                                value="${dinner.id}">
                                            ${dinner.name}</option>
                                    </#list>
                                </select>
                            </li>
                        </ul>
                    </td>
                    <td>
                        <button type="submit" class="btn btn-primary">Сохранить</button>
                    </td>
                </tr>
            </form>
            </tbody>
        </#if>
    </table>
</@common.page>