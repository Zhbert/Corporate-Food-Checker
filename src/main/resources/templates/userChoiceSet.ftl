<#import "parts/common.ftl" as common>
<@common.page>
    <blockquote class="blockquote mb-3">
        <p class="mb-0">Выберите желаемые обеды:</p>
    </blockquote>

    <form action="/user-choice/set" method="post">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Дата</th>
                <th scope="col">Название обеда</th>
                <th scope="col">Описание</th>
                <th scope="col">Функции</th>
            </tr>
            </thead>
            <#if choice??>
                <tbody>
                <tr>
                    <th scope="row">${choice.date}</th>
                    <td>
                        <ul class="list-group">
                            <li class="list-group-item">
                                <#list dsa as dsa>
                                    <#if dsa??>
                                        <#if dsa.dinnerDate == choice.date>
                                            <#if dsa.dinner??>
                                                ${dsa.dinner.name}
                                            </#if>
                                        </#if>
                                    </#if>
                                </#list>
                            </li>
                            <li class="list-group-item">
                                <#list dsa as dsa>
                                    <#if dsa??>
                                        <#if dsa.dinnerDate == choice.date>
                                            <#if dsa.dinnerTwo??>
                                                ${dsa.dinnerTwo.name}
                                            </#if>
                                        </#if>
                                    </#if>
                                </#list>
                            </li>
                        </ul>
                        <div class="input-group mb-3 mt-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputGroupSelect01">Выберите обед:</label>
                            </div>
                            <input type="hidden" value="${choice.id}" name="choiceId">
                            <input type="hidden" value="${_csrf.token}" name="_csrf">
                            <select class="custom-select" name="dinner_id">
                                <option selected>Выберите...</option>
                                <#list dsa as dsa>
                                    <#if dsa??>
                                        <#if dsa.dinnerDate == choice.date>
                                            <#if dsa.dinner??>
                                                <option value="${dsa.dinner.id}">${dsa.dinner.name}</option>
                                            </#if>
                                        </#if>
                                    </#if>
                                </#list>
                                <#list dsa as dsa>
                                    <#if dsa??>
                                        <#if dsa.dinnerDate == choice.date>
                                            <#if dsa.dinner??>
                                                <option value="${dsa.dinnerTwo.id}">${dsa.dinnerTwo.name}</option>
                                            </#if>
                                        </#if>
                                    </#if>
                                </#list>
                            </select>
                        </div>
                    </td>
                    <td>
                        <ul class="list-group">
                            <li class="list-group-item">
                                <#list dsa as dsa>
                                    <#if dsa??>
                                        <#if dsa.dinnerDate == choice.date>
                                            <#if dsa.dinner??>
                                                ${dsa.dinner.description}
                                            </#if>
                                        </#if>
                                    </#if>
                                </#list>
                            </li>
                            <li class="list-group-item">
                                <#list dsa as dsa>
                                    <#if dsa??>
                                        <#if dsa.dinnerDate == choice.date>
                                            <#if dsa.dinnerTwo??>
                                                ${dsa.dinnerTwo.description}
                                            </#if>
                                        </#if>
                                    </#if>
                                </#list>
                            </li>
                        </ul>
                    </td>
                    <td><button type="submit" class="btn btn-primary">Сохранить</button>
                    </td>
                </tr>
                </tbody>
            </#if>
        </table>
    </form>
</@common.page>