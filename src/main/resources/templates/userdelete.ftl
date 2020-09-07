<#import "parts/common.ftl" as common>
<@common.page>
    <blockquote class="blockquote mb-3">
        <p class="mb-0">Удаление пользователя ${user.username}:</p>
        <div>Вы уверены, что хотите удалить этого пользователя?</div>
    </blockquote>

    <div>
        <div class="form-group mt-3">
            <form action="/user/user-delete/${user.username}" method="post">
                <h3>
                    ${user.username}<br>
                    <small class="text-muted">${user.username}</small>
                </h3>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-success btn-sm">Удалить пользователя</button>
                </div>
            </form>
        </div>
    </div>
</@common.page>