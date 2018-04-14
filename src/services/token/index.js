const Koa = require('koa');
const app = new Koa();
const route = require('koa-route');
const koaBody = require('koa-body');
const tokenExp = require('./config/default').token;
const base64url = require('base64url')
const crypto = require('crypto');

app.use(koaBody());

const getCrypto = (type, key) => {
    return crypto.createHmac('sha256', key);
}

const authGet = async ctx => {
    const post = ctx.request.body;
    let token = "";
    let header = tokenExp.header;
    let payload = tokenExp.payload;
    let secret = ""; 
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
    //prepare for builting secret
    const curTime = new Date().getTime()
    payload["iat"] = curTime;
    payload["exp"] = parseInt(payload["iat"]) + tokenExp.limit.exp;
    payload["nbf"] = curTime;
    //built secret
    header = base64url(JSON.stringify(header));
    payload = base64url(JSON.stringify(payload));
    let hamc = getCrypto(tokenExp.header.alg, tokenExp.secret);
    hamc.update(`${header}.${payload}`);
    secret = hamc.digest('hex');
    token = `${header}.${payload}.${secret}`;
    //set response
    ctx.response.status = 200;
    ctx.response.type = 'json';
    ctx.response.body = {
        "token": token
    };
};

const authUpdate = ctx => {
    //do update a old token and return a new token if former token is legal
    let oldToken = ctx.request.body.oldToken;
    if(oldToken == undefined)
        ctx.throw(400);
    oldToken = oldToken.split(".");
    const header = oldToken[0];
    let payload = oldToken[1];
    let secret = oldToken[2];
    // check old token
    let hamc = getCrypto(tokenExp.header.alg, tokenExp.secret);
    hamc.update(`${header}.${payload}`);
    let tempSecret = hamc.digest('hex');
    // decode payload -> JSON & verify
    payload = JSON.parse(new Buffer(payload, 'base64').toString());
    const curTime = parseInt(new Date().getTime());
    if(secret != tempSecret || parseInt(payload.exp) < curTime || parseInt(payload.nbf) > curTime)
        ctx.throw(403);
    // update payload
    payload["iat"] = curTime;
    payload["exp"] = parseInt(payload["iat"]) + tokenExp.limit.exp;
    payload["nbf"] = curTime;
    payload = base64url(JSON.stringify(payload));
    // built secret
    hamc = getCrypto(tokenExp.header.alg, tokenExp.secret);
    hamc.update(`${header}.${payload}`);
    secret = hamc.digest('hex');
    token = `${header}.${payload}.${secret}`;
    //set response
    ctx.response.status = 200;
    ctx.response.type = 'json';
    ctx.response.body = {
        "token": token
    };
}

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

app.use(route.post('/token/get', authGet));
app.use(route.post('/token/update', authUpdate));
app.use(route.post('/token/getSecret', getSecret));

app.listen(3030);