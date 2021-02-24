const express = require('express');
const authController = require('./controllers/auth');

const router = express.Router();

router.get('/register', (req, res) => {
  res.render('user/register');
});

router.get('/login', (req, res) => {
  res.render('user/login');
});

router.post('/register', authController.register);

module.exports = router;
