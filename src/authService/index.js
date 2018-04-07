const Koa = require('koa');
const app = new Koa();
const route = require('koa-route');
const koaBody = require('koa-body');
var superagent = require('superagent');

app.use(koaBody());

const authGet = async ctx => {
    //do signin and return a token if user info is correct.
    const body = ctx.request.body;
    //request other apis and get return values
    let userReq = body => {
        return superagent
        .post('http://localhost:3000/user/auth')
        .type('application/json')
        .set('Accept', 'application/json')
        .send(body)
        .then((res, err) => {
            return res.res.text;
        });
    }
    //async get return values
    let userRes = await userReq(body);
    //response
    ctx.response.type = 'json';
    ctx.response.body = {
        "status": true,
        "info": userRes
    };
};

const authPost = ctx => {
    //do signup and return a token if request params are correct.
}

const authUpdate = ctx => {
    //do update a old token and return a new token if former token is legal
}

const test = ctx => {
    //here is a test module for returning a json.
    const body = ctx.request.body;
    if(body.a == 'a') {
        ctx.response.type = 'json';
        ctx.response.body = {
            "status": true
        }
    }
    else {
        ctx.response.type = 'json';
        ctx.response.body = {
            "status": false
        }
    }
}

app.use(route.post('/auth/get', authGet));
app.use(route.post('/auth/post', authPost));
app.use(route.post('/auth/update', authUpdate));
//test link
app.use(route.post('/user/auth', test));

app.listen(3000);