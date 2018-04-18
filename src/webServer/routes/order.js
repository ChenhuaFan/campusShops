var express = require('express');
var router = express.Router();
var path = require('path');

/* GET users listing. */
router.get('/orderStatus', function(req, res, next) {
    res.sendFile(path.join(__dirname, '../views/pages/orderStatus.html')); 
});

module.exports = router;
