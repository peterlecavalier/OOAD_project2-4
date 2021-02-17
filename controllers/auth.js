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
      res.redirect('assets/404-page.html');
      return;
    }

    if (users.length > 0) {
      // the user already exists
      // TODO: proper error returns
      res.redirect('back');
    }

    if (password !== passwordConfirm) {
      res.redirect('register.html');
      // TODO: passwords do not match
    }

    const hashedPassword = bcrypt.hash(password, 8);

    db.query('INSERT INTO users SET ?', { username, email, password: hashedPassword }, (err) => {
      if (err) {
        console.log(err);
      } else {
        // TODO: user registered
        res.redirect('/');
      }
    });
  });
};
