<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <h1 class="display-3 mb-3 text-center">Sign Up</h1>
    ${message!}
    <@l.login "/registration" />
</@c.page>