<#import "parts/common.ftlh" as c>

<@c.page>
    <h1 class="mb-3">List of users</h1>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Role</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody class="table-group-divider">
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/user/${user.id}">Edit</a> </td>
            </tr>
        </#list>
        </tbody>
    </table>



</@c.page>