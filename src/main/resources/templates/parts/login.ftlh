<#macro login path isRegisterForm>
    <#if !isRegisterForm>
        <h1 class="display-3 mb-3 text-center">Sign In</h1>
    </#if>
    <#if isRegisterForm>
        <h1 class="display-3 mb-3 text-center">Sign Up</h1>
    </#if>
    <div class="container mt-5 pt-5">
        <div class="row">
            <div class="col-12 col-sm-7 col-md-6 m-auto">
                <form action="${path}" method="post">
                    <div class="form-group form-floating mb-3">
                        <input type="text" name="username" id="floatingInput" class="form-control my-4 placeholder-sky-300" placeholder="Username" />
                        <label for="floatingInput">Username</label>
                    </div>
                    <div class="form-group form-floating mb-3">
                        <input type="password" name="password" id="floatingInput" class="form-control my-4 placeholder-sky-300" placeholder="Password" />
                        <label for="floatingInput">Password</label>
                    </div>
                    <#if isRegisterForm>
                        <div class="form-group form-floating mb-3">
                            <input type="email" name="email" id="floatingInput" class="form-control my-4 placeholder-sky-300" placeholder="Email" />
                            <label for="floatingInput">Email</label>
                        </div>
                    </#if>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div class="text-center mt-3">
                        <button type="submit" class="btn btn-primary">Sign In</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <#if !isRegisterForm>
        <div class="text-center mt-3">
            Don't have an account?
            <a href="/registration">Sign Up</a>
        </div>
    </#if>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-primary">Sign Out</button>
    </form>
</#macro>

