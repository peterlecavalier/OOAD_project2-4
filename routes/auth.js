const express = require('express');
const authController = require('./controllers/auth');

const router = express.Router();

router.get('/register', function(req, res, next) {
  res.render('register');
});

router.get('/login', function(req, res, next) {
  res.render('login');
});

router.post('/register', authController.register);

module.exports = router;
