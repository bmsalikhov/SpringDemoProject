<#import "parts/common.ftlh" as c>

<@c.page>
    <form class="row g-3" method="get" action="main">
        <div class="col-auto">
            <label class="visually-hidden">Enter the tag</label>
            <input type="text" name="filter" class="form-control" placeholder="Search by tag" value="${filter!}">
        </div>
        <div class="col-auto">
            <button type="submit" class="btn btn-primary mb-3">Search</button>
        </div>
    </form>
    <a class="btn btn-primary mb-3" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new message
    </a>
    <div class="collapse" id="collapseExample">
        <div>
            <form method="post" action="add" enctype="multipart/form-data">
                <input type="text" name="text" class="form-control mt-3 mb-3 ml-3" id="message" placeholder="Enter the message">
                <input type="text" name="tag" class="form-control mb-3 ml-3" id="message" placeholder="Enter the tag">
                <input class="form-control mb-3 ml-3" name="file" type="file">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit" class="btn btn-primary ml-3 mb-3">Submit</button>
            </form>
        </div>
    </div>
    <div class="row" data-masonry='{"percentPosition": true }'>
        <#list messages as message>
        <div class="col-sm-6 col-lg-4 mb-4">
            <div class="card">
                <#if message.filename??>
                    <img src="/img/${message.filename}" class="card-img-top">
                </#if>
                <div class="card-body">
                    <p class="card-text">${message.text}</p>
                </div>
                <div class="card-footer">
                    <small class="text-muted">Author: ${message.authorName}</small>
                </div>
                <div class="card-footer">
                    <small class="text-muted">Tag: ${message.tag}</small>
                </div>
            </div>
        </div>
        <#else>
            No messages!
        </#list>
    </div>
</@c.page>


