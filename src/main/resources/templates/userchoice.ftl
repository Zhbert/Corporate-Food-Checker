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
        <#if dinnersByDate??>
            <tbody>

            <#list dinnersByDate as dinnerByDate>
                <tr>
                    <th scope="row">${dinnerByDate.dinnerDate}</th>
                    <td>
                        <ul class="list-group">
                            <li class="list-group-item"><#if dinnerByDate.dinner??>${dinnerByDate.dinner.name}</#if></li>
                            <li class="list-group-item"><#if dinnerByDate.dinnerTwo??>${dinnerByDate.dinnerTwo.name}</#if></li>
                        </ul>
                    </td>
                    <td>
                        <ul class="list-group">
                            <li class="list-group-item"><#if dinnerByDate.dinner??>${dinnerByDate.dinner.description}</#if></li>
                            <li class="list-group-item"><#if dinnerByDate.dinnerTwo??>${dinnerByDate.dinnerTwo.description}</#if></li>
                        </ul>
                    </td>
                    <td><a class="btn btn-primary" href="/admin/dinner-set?id=${dinnerByDate.id}" role="button">Изменить</a>
                    </td>
                </tr>
            </#list>

            </tbody>
        </#if>
    </table>
</@common.page>