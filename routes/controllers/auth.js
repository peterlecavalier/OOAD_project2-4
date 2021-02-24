const mysql = require('mysql');
const bcrypt = require('bcryptjs');
// const jwt = require('jsonwebtoken');

const db = mysql.createConnection({
  host: process.env.DATABASE_HOST,
  user: process.env.DATABASE_USER,
  password: process.env.DATABASE_PASSWORD,
  database: process.env.DATABASE,
});

exports.register = (req, res) => {
  const {
    username, email, password, passwordConfirm,
  } = req.body;

  db.query('SELECT email FROM users WHERE email = ?', [email], (error, users) => {
    if (error) {
      console.log(error);
      res.render('user/register', { message: error });
      return;
    }

    if (users.length > 0) {
      res.render('user/register', { message: 'User already exists' });
    }

    if (password !== passwordConfirm) {
      res.render('user/register', { message: 'Passwords do no match' });
    }

    const hashedPassword = bcrypt.hash(password, 8);

    db.query('INSERT INTO users SET ?', { username, email, password: hashedPassword }, (err) => {
      if (err) {
        console.log(err);
      } else {
        res.redirect('/');
      }
    });
  });
};
