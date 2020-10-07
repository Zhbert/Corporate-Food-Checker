<#import "parts/common.ftl" as common>
<@common.page>
    <blockquote class="blockquote mb-3">
        <p class="mb-0">Редактирование информации о пользователе:</p>
    </blockquote>
    <form action="/user" method="post">
        <input type="hidden" value="${user.id}" name="userId">
        <label>Имя пользователя:</label>
        <input type="text" name="username" class="form-control" value="${user.username}">
        <label class="mt-2">Пароль:</label>
        <input type="text" name="password" class="form-control" placeholder="Введите новый пароль">
        <label class="mt-3">Права пользователя:</label>
        <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit" class="btn btn-primary">Сохранить</button>

    </form>
</@common.page>