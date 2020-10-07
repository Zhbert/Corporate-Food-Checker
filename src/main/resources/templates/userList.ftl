<#import "parts/common.ftl" as common>
<@common.page>
    <blockquote class="blockquote mb-3">
        <p class="mb-0">Список пользователей:</p>
    </blockquote>


    <#if message??>
        <#if message == "User exists!">
            <div class="alert alert-danger" role="alert">
                Такой пользователь уже существует!
            </div>
        </#if>
    </#if>


    <a class="btn btn-primary mb-3" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Добавить нового пользователя
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" action="/user/register">
                <div class="form-group">
                    <input type="text" class="form-control ${(usernameError??)?string('is-invalid', '')}"
                           name="username"
                           value="<#if error??>${error.username}</#if>"
                           placeholder="Введите имя пользователя"/>
                    <#if usernameError??>
                        <div class="invalid-feedback">
                            ${usernameError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <input type="password" name="password"
                           class="form-control ${(passwordError??)?string('is-invalid', '')}"
                           placeholder="Пароль"/>
                    <#if passwordError??>
                        <div class="invalid-feedback">
                            ${passwordError}
                        </div>
                    </#if>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-success btn-sm">Добавить пользователя</button>
                </div>
            </form>
        </div>
    </div>


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