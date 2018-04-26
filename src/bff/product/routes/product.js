const router = require('koa-router')()
const service = require('../config/services');
const agent = require('../common/agent');

router.post('/get', async function (ctx, next) {
    // request
    let body = ctx.request.body;
    // do request.
    let goods = await agent(service.goods.disaplay, body);
    if (goods.status === 'false' || goods.status === false) {
        ctx.response.status = 403;
        ctx.body = goods;
        return;
    }
    // set response.
    ctx.response.status = 200;
    ctx.response.body = goods;
})

router.post('/getDetail', async function (ctx, next) {
    let body = ctx.request.body;
    let good = await agent(service.goods.allDisaplay, body);
    if (good.status === 'false' || good.status === false) {
        ctx.response.status = 403;
        ctx.body = good;
        return;
    }
    // set
    ctx.response.status = 200;
    ctx.response.body = good;
})

module.exports = router