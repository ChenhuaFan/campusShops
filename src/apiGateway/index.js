const Koa = require('koa');
const app = new Koa();
const route = require('koa-route');
const koaBody = require('koa-body');
const radis = require('radis');
const request = require('superagent');
let conf = require('./config/default');
const fs = require('fs');
const path=require('path');

// 解析请求参数
app.use(koaBody());

// 读取 services.json & exApis.json
// 使用 path.join 构建配置文件路径
let services = JSON.parse(fs.readFileSync(__dirname + "/config/services.json"));
let exApis = JSON.parse(fs.readFileSync(__dirname + "/config/exApis.json"));

// 网关初始化
const initServices = () => {
    // 根据 exApis 为每个api生成路由函数
    let apiArry = exApis.apis;
    let tempApi = "";
    let a = {};
    for (let i = 0,len=apiArry.length; i < len; i++) {
        for(let url in apiArry[i]) {
            tempApi = (apiArry[i])[url];
            console.log(tempApi);
            a[url] = {
                method: tempApi.method,
                bff: ctx => {
                    const body = ctx.request.body;
                    // 生成请求依赖链
                    ctx.response.status = 200;
                    ctx.response.type = 'json';
                    ctx.response.body = {
                        info: body
                    };
                }
            }
        }
    }
    //遍历注册所有路由
    for (let temU in a) {
        if (a[temU].method == "post") {
            console.log(a[temU]);
            app.use(route.post(temU, a[temU].bff));
        }
        else
            app.use(route.get(temU, a[temU].bff));
    }
};

const builtBff = (ctx, api) => {
    console.log(api);
    let dependences = api.dependences;
    let entrance = dependences.result;
    // 申明层数（同一层并行，不同层穿行执行）
    let depth = 0;
    let relyConstruct = {};
    // 从入口依赖数组出发，递归得到依赖结构
    findParents(depth, entrance);
    
    const findParents = (depth, node) => {
        // 如果依赖数组为null，则说明已经到该层的头结点，退出.
        if(node.rely == null)
            console.log(depth+": "+ node);
            return;
        depth += 1;
        for (let tempNode in node.rely) {
            findParents(depth, dependences[tempNode]);
        }
    }

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

// 初始化服务
// initServices();

// 注册中间件
// 注册表单表单数据处理
app.use(koaBody());
// 注册错误处理
app.use(handler);
// 注册路由
app.use(route.get('/', web));

app.use(route.post('/userLogin', ctx => {

    console.log("test route ok!");

    const body = ctx.request.body;
    if(body.userName == "sam" && body.pw == "123456") {
        ctx.response.status = 200;
        ctx.response.type = 'json';
        ctx.response.body = {
            "userId": 1,
            "userName": body.userName,
            "role": "user",
            "headPortrait": "default.png"
        };
    } else {
        ctx.response.status = 403;
        ctx.response.type = 'json';
        ctx.response.body = {
            "status": false,
            "info": "wrong pw or userName"
        };
    }
}))

// 启动服务器
app.listen(3032);