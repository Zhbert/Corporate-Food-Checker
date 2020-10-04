<#import "parts/common.ftl" as common>
<@common.page>
    <blockquote class="blockquote mb-3">
        <p class="mb-0">Выберите обед на ${choice.date}<br>${choice.dayOfWeek}:</p>
    </blockquote>
    
    <div class="container mb-2">Выберите один из доступных обедов:</div>
    <form action="/user-choice/set" method="post">
        <#if choice??>
        <table class="table table-striped">
            <ul class="list-group">
                <li class="list-group-item">
                    <#list dsa as dsa>
                        <#if dsa??>
                            <#if dsa.dinnerDate == choice.date>
                                <#if dsa.dinner??>
                                    <b>${dsa.dinner.name}</b><br>
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
                                    <b>${dsa.dinnerTwo.name}</b><br>
                                    ${dsa.dinnerTwo.description}
                                </#if>
                            </#if>
                        </#if>
                    </#list>
                </li>
                <li class="list-group-item">
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
                    <#list dsa as dsa>
                        <#if dsa??>
                            <#if dsa.dinnerDate == choice.date>
                                <#if dsa.dinner??>
                                    <button type="submit" class="btn btn-primary">Сохранить</button>
                                <#else>
                                    <button type="submit" class="btn btn-primary" disabled>Сохранить</button>
                                    <div class="alert alert-danger" role="alert">
                                        Обед не назначен администратором!
                                    </div>
                                </#if>
                            </#if>
                        </#if>
                    </#list>
                </li>
                </#if>
        </table>
    </form>
</@common.page>