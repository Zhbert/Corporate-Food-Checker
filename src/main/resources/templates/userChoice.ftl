<#import "parts/common.ftl" as common>
<@common.page>
    <blockquote class="blockquote mb-3">
        <p class="mb-0">Выберите желаемые обеды:</p>
    </blockquote>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Дата</th>
            <th scope="col">Название обеда</th>
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
                                <#if choice.dinner??>
                                    ${choice.dinner.name}
                                </#if>
                            </li>
                        </ul>
                    </td>
                    <td>
                        <ul class="list-group">
                            <li class="list-group-item">
                                <#if choice.dinner??>
                                    ${choice.dinner.description}
                                </#if>
                            </li>
                        </ul>
                    </td>
                    <td><a class="btn btn-primary" href="/user-choice/set?id=${choice.id}"
                           role="button">Изменить</a>
                    </td>
                </tr>
            </#list>
            </tbody>
        </#if>
    </table>
    <#if choices??>
        <#list choices as choice>
            <div class="container">
                <button type="button" class="btn <#if choice.dinner??>btn-primary<#else>btn-secondary</#if> mt-3 btn-lg btn-block">
                    <table class="container">
                        <td width="50%">${choice.date}</td>
                        <td width="50%">
                            <#if choice.dinner??>
                                <b>${choice.dinner.name}</b>
                                <br>
                                ${choice.dinner.description}
                            </#if>
                        </td>
                    </table>
                </button>
            </div>
        </#list>
    </#if>
</@common.page>