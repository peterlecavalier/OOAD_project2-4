const mysql = require('mysql');
const jwt = require('jsonwebtoken');
const bcrypt = require('bcryptjs');

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

  db.query('SELECT email FROM users WHERE email = ?', [email], async (error, results) => {
    if (error) {
      console.log(error);
    }

    if (results.length > 0) {
      return res.render('register', {
        message: 'That email is already in use.',
      });
    } if (password !== passwordConfirm) {
      return res.redirect('register.html');
      // return res.render("register", {
      //     message: "The passwords do not match."
      // });
    }

    const hashedPassword = await bcrypt.hash(password, 8);

    db.query('INSERT INTO users SET ?', { username, email, password: hashedPassword }, (error, results) => {
      if (error) {
        console.log(error);
      } else {
        return res.redirect('/');

        // return res.render("register", {
        //     message: "User registred!"
        // });
      }
    });
  });

  // res.send("Form submitted");
};
