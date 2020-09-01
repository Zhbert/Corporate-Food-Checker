<#import "parts/common.ftl" as common>
<@common.page>
    <blockquote class="blockquote mb-3">
        <p class="mb-0">Список пользователей:</p>
    </blockquote>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Имя пользователя</th>
            <th>Права</th>
            <th>Функции</th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a class="btn btn-primary" href="/user/${user.id}">Изменить</a>
                    <a class="btn btn-danger" href="/user/user-delete/${user.username}" role="button">Удалить</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@common.page>