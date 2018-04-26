const router = require('koa-router')()

router.prefix('/goods')

router.post('/disaplay', function (ctx, next) {
    let body = ctx.request.body;
    console.log(body);
    ctx.body = {
        'goods': [
            {
                'goods': 1,
                'goodsName': '辣条',
                'goodsStock': 1000,
                'goodsPrice': 2.5,
                'goodsPicture': 'picture.jpg',
                'goodsBelong': 1
            },
            {
                'goods': 2,
                'goodsName': '鸡腿',
                'goodsStock': 50,
                'goodsPrice': 5,
                'goodsPicture': 'picture.jpg',
                'goodsBelong': 1
            },
            {
                'goods': 3,
                'goodsName': '汉堡包',
                'goodsStock': 60,
                'goodsPrice': 20,
                'goodsPicture': 'picture.jpg',
                'goodsBelong': 1
            }
        ]
    }
})

router.post('/allDisaplay', function (ctx, next) {
    let body = ctx.request.body;
    console.log(body);
    ctx.response.status = 200;
    switch(body.id) {
        case '1':
            ctx.body = {
                'goods': 1,
                'goodsName': '辣条',
                'goodsStock': 1000,
                'goodsPrice': 2.5,
                'goodsPicture': 'picture.jpg',
                'goodsBelong': 1
            }
            return;
        case '2':
            ctx.body = {
                'goods': 2,
                'goodsName': '鸡腿',
                'goodsStock': 50,
                'goodsPrice': 5,
                'goodsPicture': 'picture.jpg',
                'goodsDepict': "辣",
                'goodsBelong': 1
            }
            return;
        case '3':
            ctx.body = {
                'goods': 3,
                'goodsName': '汉堡包',
                'goodsStock': 60,
                'goodsPrice': 20,
                'goodsPicture': 'picture.jpg',
                'goodsBelong': 1
            }
            return;
        default:
            ctx.body = {
                'status': false,
                'info': 'wrong disaplay'
            }
            return;
    }
})

module.exports = router