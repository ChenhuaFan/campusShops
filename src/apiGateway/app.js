const Koa = require('koa');
const app = new Koa();
const route = require('koa-route');
const koaBody = require('koa-body');
const radis = require('radis');
const jwt = require('jsonwebtoken');
const token = require('./services/token');

// 解析请求参数
app.use(koaBody());

// 网关初始化
const initServices = () => {
    
};

// 将当前配置写入JSON中
const saveToJson = () => {

}

// 1. 错误处理中间件
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

// 2. api跟踪中间件
const trace = (ctx, next) => {
    // before
    next();
    // after
};

// 3. 日志记录中间件
const logger = (ctx, next) => {
    // before
    next();
    // after
};

// 4. 鉴权记录中间件
const auth = (ctx, next) => {
    // before
    next();
    // after
};

// web界面监控
const web = ctx => {
    ctx.response.status = 200;
    ctx.response.body = "Hello world!";
};

// 初始化服务
// initServices();

// 注册错误处理
app.use(handler);
// 注册路由
app.use(route.get('/', web));

// 测试，注册内部api接口
app.use(route.post('/test', async ctx => {
    let req = {};
    req.body = ctx.request.body;
    let res = await token.get(req);
    ctx.response.status = 200;
    ctx.response.type = 'json';
    ctx.response.body = res;
}));

// 全局err监控
app.on('error', (err) => {
    console.log('info: ' + err.message);
    console.log(err);
});

// apigw 启动服务器
module.exports = app;