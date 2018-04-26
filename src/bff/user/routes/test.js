const router = require('koa-router')()

router.prefix('/user')

router.post('/userLogin', function (ctx, next) {
    let body = ctx.request.body;
    console.log(body);
    if (body.userName == 'sam' && body.pw == 'd123456f') {
        ctx.body = {
            'userID': 1,
            'userName': 'sam',
            'role': 'admin',
            'headPortrait': 'sam.jpg'
          };
    } else {
        ctx.body = {
            'status': false,
            'info': 'fuck!'
          };
    }
})

router.post('/userRegister', function (ctx, next) {
    let body = ctx.request.body;
    console.log(body);
    ctx.response.status = 200;
    ctx.body = {
        'userID': 1,
        'userName': body.userName,
        'role': 'user',
        'headPortrait': body.userName+'.jpg'
    }
})

module.exports = router