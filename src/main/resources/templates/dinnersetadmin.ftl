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
            <th scope="col">Функции</th>
        </tr>
        </thead>
        <#if dinnersByDate??>
            <tbody>

            <#list dinnersByDate as dinnerByDate>
                <tr>
                    <th scope="row">${dinnerByDate.dinnerDate}</th>
                    <td><#if dinnerByDate.dinner??>${dinnerByDate.dinner.name}</#if></td>
                    <td><#if dinnerByDate.dinner??>${dinnerByDate.dinner.description}</#if></td>
                    <td><a class="btn btn-primary" href="/admin/dinner-change?id=${dinnerByDate.id}" role="button">Изменить</a>
                    </td>
                </tr>
            </#list>

            </tbody>
        </#if>
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
            <th scope="col">Функции</th>
        </tr>
        </thead>
        <#if dinnersSets??>
            <tbody>

            <#list dinnersSets as dinnerSet>
                <tr>
                    <th scope="row">${dinnerSet.dinnerDate}</th>
                    <td><#if dinnerSet.dinner??>${dinnerSet.dinner.name}</#if></td>
                    <td><#if dinnerSet.dinner??>${dinnerSet.dinner.description}</#if></td>
                    <td><a class="btn btn-primary" href="/admin/dinner-change?id=${dinnerSet.id}"
                           role="button">Изменить</a>
                    </td>
                </tr>
            </#list>

            </tbody>
        </#if>
    </table>
</@common.page>