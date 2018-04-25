const router = require('koa-router')()
const agent = require('superagent');

router.prefix('/auth')

let temp = (url, body) => {
    return agent
        .post(url)
        .send(body)
        .set('Accept', 'application/json')
        .then(function(res) {
            return res.body;
        });
};

router.post('/login', async function (ctx, next) {
    // request user -> request token.
    let body = ctx.request.body;
    // do request. 'http://localhost:3000/user/userLogin'
    let user = await temp('http://localhost:3000/user/userLogin', body);
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
    let token = await temp('http://localhost:5000/token/get', user);
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
    let user = await temp('http://localhost:3000/user/userRegister', body);
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
    let token = await temp('http://localhost:5000/token/get', user);
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
    let token = await temp('http://localhost:5000/token/update', body);
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