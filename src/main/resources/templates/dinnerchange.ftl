<#import "parts/common.ftl" as common>
<@common.page>
    <blockquote class="blockquote mb-3">
        <p class="mb-0">Изменение обеда №${dinner.id}:</p>
    </blockquote>

    <div>
        <div class="form-group mt-3">
            <form action="/admin/dinner-change" method="post">
                <div class="form-group">
                    <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}"
                           name="name" value="<#if error??>${error.name}<#else>${dinner.name}</#if>"/>
                    <#if nameError??>
                        <div class="invalid-feedback">
                            ${nameError}
                        </div>
                    </#if>
                </div>
                <div class="form-group">
                    <textarea class="form-control ${(descriptionError??)?string('is-invalid', '')}" name="description"><#if error??>${error.description}<#else>${dinner.description}</#if></textarea>
                    <#if descriptionError??>
                        <div class="invalid-feedback">
                            ${descriptionError}
                        </div>
                    </#if>
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