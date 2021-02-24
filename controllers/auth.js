const mysql = require('mysql');
const bcrypt = require('bcryptjs');
const {pool} = require("../dbconfig");

exports.register = async (req, res) => {
  const {username, email, password, passwordConfirm} = req.body;

  let errors = [];

  if(!username || !email || !password || !passwordConfirm) {
      errors.push({message: "Please complete all fields."});
  }

  if(password.length < 6) {
      errors.push({message: "The password should be at least 6 characters."});
  }

  if(password != passwordConfirm) {
      errors.push({message: "The passwords do not match."});
  }

  if(errors.length > 0) {
      res.redirect("assets/404-page.html");
  } else {
      // Form validation has passed
      let hashedPassword = await bcrypt.hash(password, 10);

      pool.query(
          `SELECT * FROM users WHERE email = $1`, [email], (err, results) => {
              if(err) {
                  throw err;
              }

              if(results.rows.length > 0) {
                  errors.push({message: "Email already registered"});
                  res.redirect("assets/404-page.html");
              } else {
                  pool.query(
                      `INSERT INTO users (username, email, password)
                      VALUES ($1, $2, $3)
                      RETURNING id, password`, [username, email, hashedPassword], (err, results) => {
                          if(err) {
                              throw err;
                          }

                          console.log(results.rows);
                          res.redirect('login.html');
                      }
                  );
              }
          }
      );
  }
};
