<#import "parts/common.ftl" as common>
<@common.page>
    <blockquote class="blockquote mb-3">
        <p class="mb-0">Созданные варианты обедов</p>
    </blockquote>

    <a class="btn btn-primary mb-3" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Добавить новый обед
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control ${(usernameError??)?string('is-invalid', '')}"
                           value="<#if message??>${message.name}</#if>"
                           name="name" placeholder="Введите наименование"/>
                    <#if nameError??>
                        <div class="invalid-feedback">
                            ${usernameError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <textarea class="form-control ${(descriptionError??)?string('is-invalid', '')}"
                              value="<#if message??>${message.description}</#if>"
                              name="description"
                              placeholder="Введите описание и состав обеда"></textarea>
                    <#if descriptionError??>
                        <div class="invalid-feedback">
                            ${descriptionError}
                        </div>
                    </#if>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-success btn-sm">Добавить обед</button>
                </div>
            </form>
        </div>
    </div>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">№</th>
            <th scope="col">Название</th>
            <th scope="col">Описание</th>
            <th scope="col">Функции</th>
        </tr>
        </thead>
        <tbody>
        <#list dinners as dinner>
            <tr>
                <th scope="row">${dinner.id}</th>
                <td>${dinner.name}</td>
                <td>${dinner.description}</td>
                <td><a class="btn btn-primary" href="/admin/dinner-change?id=${dinner.id}" role="button">Изменить</a>
                    <a class="btn btn-danger" href="/admin/dinner-delete?id=${dinner.id}" role="button">Удалить</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@common.page>