const router = require('koa-router')()

router.prefix('/shop')

router.post('/shopDisaplay', function (ctx, next) {
    let body = ctx.request.body;
    console.log(body);
    ctx.body = {
        'shop': [
            {
                "shopId": 1,
                "shopName": "爱上堡",
                "shopRank": 5,
                "shopPicture": "picture.jpg",
                "isOpen": 0,
                "shopBelong": 1
            },
            {
                "shopId": 2,
                "shopName": "徽大厨",
                "shopRank": 5,
                "shopPicture": "picture.jpg",
                "isOpen": 0,
                "shopBelong": 2
            },
            {
                "shopId": 3,
                "shopName": "家常菜馆",
                "shopRank": 5,
                "shopPicture": "picture.jpg",
                "isOpen": 0,
                "shopBelong": 2
            },
            {
                "shopId": 4,
                "shopName": "黄焖鸡米饭",
                "shopRank": 5,
                "shopPicture": "picture.jpg",
                "isOpen": 0,
                "shopBelong": 2
            }
        ]
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