const router = require('koa-router')()
const service = require('../config/services');
const agent = require('../common/agent');

router.post('/get', async function (ctx, next) {
    // request user -> request token.
    let body = ctx.request.body;
    // do request.
    let shops = await agent(service.shop.shopDisaplay, body);
    if (shops.status === 'false' || shops.status === false) {
        ctx.response.status = 403;
        ctx.body = shops;
        return;
    }
    // set response.
    ctx.response.status = 200;
    ctx.response.body = shops;
})

module.exports = router