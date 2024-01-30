<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <h1 class="display-3 mb-3 text-center">Sign In</h1>
    <@l.login "/login" />
    <div class="text-center mt-3">
        Don't have an account?
        <a href="/registration">Sign Up</a>
    </div>
</@c.page>



