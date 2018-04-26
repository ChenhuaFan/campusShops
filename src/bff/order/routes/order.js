const router = require('koa-router')()
const service = require('../config/services');
const agent = require('../common/agent');

router.post('/get', async function (ctx, next) {
    // request user -> request token.
    let body = ctx.request.body;
    // do request.
    let orders = await agent(service.order.getOrderByUserId, body);
    if (orders.status === 'false' || orders.status === false) {
        ctx.response.status = 403;
        ctx.body = orders;
        return;
    }
    // set response.
    ctx.response.status = 200;
    ctx.response.body = orders;
})

router.post('/post', async function (ctx, next) {
    let body = ctx.request.body;
    //
    let order = await agent(service.order.createOrder, body);
    if (order.status === 'false' || order.status === false) {
        ctx.response.status = 403;
        ctx.body = order;
        return;
    }
    // set response.
    ctx.response.status = 200;
    ctx.response.body = order;
})

module.exports = router