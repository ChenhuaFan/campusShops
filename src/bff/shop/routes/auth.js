const router = require('koa-router')()
const service = require('../config/services');
const agent = require('../common/agent');

router.prefix('/auth')

router.post('/login', async function (ctx, next) {
    // request user -> request token.
    let body = ctx.request.body;
    // do request.
    let user = await agent(service.user.login, body);
    if (user.status === 'false' || user.status === false) {
        ctx.response.status = 403;
        ctx.body = user;
        return;
    }
    // prepare other necessary info
    switch(user["role"])
    {
        case "admin":
            // get adminLevel
            user["adminLevel"] = 1;
            break;
        case "saller":
            // get shopId
            user["shopId"] = 1;
            break;
        default:
            // none
    }
    // get token
    let token = await agent(service.token.get, user);
    if (user.status === 'false' || user.status === false) {
        ctx.response.status = 403;
        ctx.body = token;
        return;
    }
    // set response.
    ctx.response.status = 200;
    ctx.response.body = token;
})

router.post('/register', async function (ctx, next) {
    let body = ctx.request.body;
    //
    let user = await agent(service.user.register, body);
    if (user.status === 'false' || user.status === false) {
        ctx.response.status = 403;
        ctx.body = user;
        return;
    }
    // prepare other necessary info
    switch(user["role"])
    {
        case "admin":
            // get adminLevel
            user["adminLevel"] = 1;
            break;
        case "saller":
            // get shopId
            user["shopId"] = 1;
            break;
        default:
            // none
    }
    // get token
    let token = await agent(service.token.get, user);
    if (user.status === 'false' || user.status === false) {
        ctx.response.status = 403;
        ctx.body = token;
        return;
    }
    // set response.
    ctx.response.status = 200;
    ctx.response.body = token;
})

router.post('/update', async function (ctx, next) {
    let body = ctx.request.body;
    let token = await agent(service.token.update, body);
    if (token.status === 'false' || token.status === false) {
        ctx.response.status = 403;
        ctx.body = token;
        return;
    }
    // set
    ctx.response.status = 200;
    ctx.response.body = token;
})

module.exports = router