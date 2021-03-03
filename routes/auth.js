const express = require('express');
const passport = require('passport');
const authController = require('./controllers/auth');

const router = express.Router();

router.get('/register', (req, res) => {
  res.render('user/register');
});

router.get('/login', (req, res) => {
  res.render('user/login');
});

router.post('/register', authController.createUser);

router.post('/login', passport.authenticate('local', {
  successRedirect: '/',
  failureRedirect: '/auth/login',
}));

module.exports = router;
