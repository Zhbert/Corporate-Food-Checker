<#import "parts/common.ftl" as common>
<@common.page>
    <blockquote class="blockquote mb-3">
        <p class="mb-0">Установка обедов на текущую дату:</p>
    </blockquote>

    <a class="btn btn-primary mb-3" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Настроить новую дату
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post">
                <select class="custom-select" id="inputGroupSelect01">
                    <#list dates as date>
                    <option>${date}</option>
                    </#list>
                </select>
                <select class="custom-select mt-3" id="inputGroupSelect01">
                    <#list dinners as dinner>
                        <option>${dinner.name}</option>
                    </#list>
                </select>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group mt-3">
                    <button type="submit" class="btn btn-success btn-sm">Добавить обед на выбранную дату</button>
                </div>
            </form>
        </div>
    </div>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Дата</th>
            <th scope="col">Тип обеда</th>
            <th scope="col">Описание</th>
        </tr>
        </thead>
        <tbody>
        <#if dinnersByDate??>
            <#list dinnersByDate as dinnerByDate>
                <tr>
                    <th scope="row">${dinnerByDate.dinnerDate}</th>
                    <td>${dinnerByDate.dinner.name}</td>
                    <td>${dinnerByDate.dinner.description}</td>
                    <td><a class="btn btn-primary" href="/admin/dinner-change?id=${dinnerByDate.id}" role="button">Изменить</a>
                        <a class="btn btn-danger" href="/admin/dinner-delete?id=${dinnerByDate.id}" role="button">Удалить</a>
                    </td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>


    <blockquote class="blockquote mb-3">
        <p class="mb-0">Установка обедов на следующие 13 дней:</p>
    </blockquote>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Дата</th>
            <th scope="col">Тип обеда</th>
            <th scope="col">Описание</th>
        </tr>
        </thead>
        <tbody>
        <#if dinnersSets??>
            <#list dinnersSets as dinner>
                <tr>
                    <th scope="row">${dinner.id}</th>
                    <td></td>
                    <td></td>
                    <td><a class="btn btn-primary" href="/admin/dinner-change?id=${dinner.id}"
                           role="button">Изменить</a>
                        <a class="btn btn-danger" href="/admin/dinner-delete?id=${dinner.id}" role="button">Удалить</a>
                    </td>
                </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</@common.page>