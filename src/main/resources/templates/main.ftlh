<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div>
        <@l.logout />
        <span><a href="/user">User list</a></span>
    </div>
    <div>
        <form method="post"  action="add" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="Enter the message">
            <input type="text" name="tag" placeholder="Enter the tag">
            <input type="file" name="file">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">add</button>
        </form>
    </div>
    <div> Messages list </div>
    <form method="get" action="main">
        <input type="text" name="filter" value="${filter!}" placeholder="Enter the tag">
        <button type="submit">Find messages</button>
    </form>
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <strong>${message.authorName}</strong>
            <span>${message.text}</span>
            <i>${message.tag}</i><br>
            <div>
                <#if message.filename??>
                    <img src="/img/${message.filename}">
                </#if>
            </div>
        </div>
        <#else>
        No messages!
    </#list>
</@c.page>