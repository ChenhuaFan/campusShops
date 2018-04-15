const Koa = require('koa');
const app = new Koa();
const route = require('koa-route');
const koaBody = require('koa-body');
const jwt = require('jsonwebtoken');
const tokenExp = require('./config/default').token;

app.use(koaBody());

const tokenGet = ctx => {
    const post = ctx.request.body;
    let payload = tokenExp.payload;
    let secret = tokenExp.secret;
    //built payload
    for(let key in payload) {
        payload[key] = post[key];
    }
    if(payload["role"]=="admin" && post["adminLevel"]==undefined)
        ctx.throw(400);
    else
        payload["adminLevel"] = post["adminLevel"];
    if(payload["role"]=="saller" && post["shopId"]==undefined)
        ctx.throw(400);
    else
        payload["shopId"] = post["shopId"];
    // test webjsontoken
    const token = jwt.sign(payload, secret, {
        expiresIn:  1200 //秒到期时间
    });
    //set response
    ctx.response.status = 200;
    ctx.response.type = 'json';
    ctx.response.body = {
        "token": token
    };
};

const tokenUpdate = ctx => {
    //do update a old token and return a new token if former token is legal
    let token = ctx.request.body.oldToken;
    if(token == undefined)
        ctx.throw(400);
    let secret = tokenExp.secret;
    //解密token
    jwt.verify(token, secret, function (err, decoded) {
        if (!err){
            //update
            delete decoded['iat'];
            delete decoded['exp'];
            token = jwt.sign(decoded, secret, {
                expiresIn:  1200 //秒到期时间
            });
                //set response
            ctx.response.status = 200;
            ctx.response.type = 'json';
            ctx.response.body = {
                "token": token
            };
        } else {
            ctx.throw(403);
        }
    });
};

const getSecret = ctx => {
    ctx.response.status = 200;
    ctx.response.type = 'json';
    ctx.response.body = {
        "secret": base64url(tokenExp.secret)
    };
};

const handler = async (ctx, next) => {
    try {
      await next();
    } catch (err) {
      ctx.response.status = err.statusCode || err.status || 500;
      ctx.response.body = {
        "status": false,
        "info": err.message
      };
    }
};

// 注册错误处理
app.use(handler);

app.use(route.post('/token/get', tokenGet));
app.use(route.post('/token/update', tokenUpdate));
app.use(route.post('/token/getSecret', getSecret));

app.listen(3030);