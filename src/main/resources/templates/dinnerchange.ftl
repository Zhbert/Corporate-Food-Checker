<#import "parts/common.ftl" as common>
<@common.page>
    <blockquote class="blockquote mb-3">
        <p class="mb-0">Изменение обеда №${dinner.id}:</p>
    </blockquote>

    <div>
        <div class="form-group mt-3">
            <form action="/admin/dinner-change" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" name="name" value="${dinner.name}"/>
                </div>
                <div class="form-group">
                    <textarea class="form-control" name="description">
                        ${dinner.description}
                    </textarea>
                </div>
                <input type="hidden" name="id" value="${dinner.id}">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-success btn-sm">Изменить обед</button>
                </div>
            </form>
        </div>
    </div>
</@common.page>