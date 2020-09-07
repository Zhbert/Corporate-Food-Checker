<#import "parts/common.ftl" as common>
<@common.page>
    <blockquote class="blockquote mb-3">
        <p class="mb-0">Удаление обеда №${dinner.id}:</p>
        <div>Вы уверены, что хотите удалить этот обед?</div>
    </blockquote>

    <div>
        <div class="form-group mt-3">
            <form action="/admin/dinner-delete/${dinner.id}" method="post">
                <h3>
                    ${dinner.name}<br>
                    <small class="text-muted">${dinner.description}</small>
                </h3>
                <input type="hidden" name="id" value="${dinner.id}">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-success btn-sm">Удалить обед</button>
                </div>
            </form>
        </div>
    </div>
</@common.page>