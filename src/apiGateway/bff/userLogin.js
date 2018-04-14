const request = require('superagent');

let postUrl = (url, body) => {
    request
        .post(url)
        .send(body)
        .set('Accept', 'application/json')
        .end(res => {
            console.log(res);
            return res;
        })
};

let getUrl = (url, query) => {

};

module.exports = {
    userLogin: async (ctx, services) => {
        // 得到参数
        const body = ctx.request.body;
        console.log("111");
        // 检查
        if (services == {} || services == undefined || body == {} || body == undefined)
            ctx.throw(500);
        // 声明user，token
        const user = services.user;
        const userLogin = (user.apis)["/userLogin"];
        const token = services.token;
        const tokenGet = (token.apis)["/get"];
        let temUrl;
        let temBody;
        // 请求
        temUrl = `${userLogin.protocol}${user.url}:${user.port}/userLogin`;
        // 构造参数
        let param;
        for (param in userLogin.body) {
            temBody[param] = body[param];
        }
        for (param in userLogin.optionalBody) {
            temBody[param] = body[param];
        }
        let firstRes = await postUrl(temUrl, temBody);
        console.log(firstRes);
        ctx.response.body = {
            "a": firstRes
        }
    }
}