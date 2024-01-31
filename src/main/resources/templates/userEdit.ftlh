<#import "parts/common.ftlh" as c>

<@c.page>
    <h1 class="mb-3">User editor</h1>
    <form action="/user" method="post">
        <div class="col-auto">
            <label for="inputNewUserName" class="col-form-label">Edit Username</label>
        </div>
        <div class="col-auto">
            <input type="text" name="username" id="inputNewUserName" class="form-control mb-3" aria-describedby="passwordHelpInline" value="${user.username}">
        </div>
        <#list roles as role>
            <div class="form-check">
                <label class="form-check-label"><input type="checkbox" class="form-check-input mb-3" id="flexCheckDefault" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
            </div>
        </#list>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button class="btn btn-primary" type="submit">Save</button>
    </form>
</@c.page>