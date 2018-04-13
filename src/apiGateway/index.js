const Koa = require('koa');
const app = new Koa();
const route = require('koa-route');
const koaBody = require('koa-body');

app.use(koaBody());

// 日志记录中间件
const logger = (ctx, next) => {
    // before
    next();
    // after
};

// 鉴权记录中间件
const auth = (ctx, next) => {
    // before
    next();
    // after
};

// api跟踪中间件
const trace = (ctx, next) => {
    // before
    next();
    // after
};

// 错误处理中间件
const handler = async (ctx, next) => {
    try {
      await next();
    } catch (err) {
      ctx.response.status = err.statusCode || err.status || 500;
      ctx.response.body = {
        "status": false,
        "info": err.message
      };
      ctx.app.emit('error', err, ctx);
    }
};


// web界面监控
const web = ctx => {

};

// 全局err监控
app.on('error', (err) => {
    console.log('info: ' + err.message);
    console.log(err);
});

// 注册错误处理
app.use(handler);
// 注册路由
app.use(route.get(web));

app.listen(3031);