const Koa = require('koa');
const app = new Koa();
const route = require('koa-route');
const koaBody = require('koa-body');
const radis = require('radis');
const request = require('superagent');

// 服务器初始化


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
    ctx.response.status = 200;
    ctx.response.body = "Hello world!";
};

// 全局err监控
app.on('error', (err) => {
    console.log('info: ' + err.message);
    console.log(err);
});

// 注册中间件
// 注册表单表单数据处理
app.use(koaBody());
// 注册错误处理
app.use(handler);
// 注册路由
app.use(route.get('/', web));

// 启动服务器
app.listen(3031);