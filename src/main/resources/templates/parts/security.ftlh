<#assign
known = SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = SPRING_SECURITY_CONTEXT.authentication.principal
    name = user.getUsername()
    isAdmin = user.getAuthorities()?seq_contains('ADMIN')
    isOnline = true
    >
<#else>
    <#assign
    name = ""
    isAdmin = false
    isOnline = false
    >
</#if>