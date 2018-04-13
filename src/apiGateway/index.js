const Koa = require('koa');
const app = new Koa();
const route = require('koa-route');
const koaBody = require('koa-body');
const radis = require('radis');
const request = require('superagent');
let conf = require('./config/default');
const fs = require('fs');
const path=require('path');


// 读取 services.json & exApis.json
// 使用 path.join 构建配置文件路径
let services = JSON.parse(fs.readFileSync(__dirname + "/config/services.json"));
let exApis = JSON.parse(fs.readFileSync(__dirname + "/config/exApis.json"));

// 网关初始化
const initServer = (app) => {
    // 根据 exApis 动态配置路由。遍历api数组
    let apiArry = exApis.apis;
    let tempApi = "";
    for (let i = 0,len=apiArry.length; i < len; i++) {
        for(let url in apiArry[i]) {
            tempApi = (apiArry[i])[url];
            if (tempApi.method == "get") {
                // 在此注册get路由
                app.use(route.get(url, (ctx, tempApi)=> builtBff()));
            }
            else {
                // 在此注册post路由
                app.use(route.post(url, (ctx, tempApi)=> builtBff()));
            }
        }
    }
};

const builtBff = (ctx, api) => {
    let dependences = api.dependences;
    
}

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

// 全局err监控
app.on('error', (err) => {
    console.log('info: ' + err.message);
    console.log(err);
});

initServer();

// 注册中间件
// 注册表单表单数据处理
app.use(koaBody());
// 注册错误处理
app.use(handler);
// 注册路由
app.use(route.get('/', web));

// 启动服务器
app.listen(3031);