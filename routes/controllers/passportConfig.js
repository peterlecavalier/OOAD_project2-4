const LocalStrategy = require('passport-local').Strategy;
const bcrypt = require('bcryptjs');
const { pool } = require('./dbConfig');

function initialize(passport) {
  const authenticateUser = (email, password, done) => {
    pool.query(
      'SELECT * FROM users WHERE email = $1', [email], (queryErr, results) => {
        if (queryErr) {
          done(queryErr);
          return;
        }

        console.log(results.rows);

        if (results.rows.length === 0) {
          done(null, false, { message: 'There is no account with this email!' });
          return;
        }

        const user = results.rows[0];

        bcrypt.compare(password, user.password, (validationErr, isMatch) => {
          if (validationErr) {
            done(validationErr);
            return;
          }

          if (!isMatch) {
            done(null, false, { message: 'Password is not correct!' });
            return;
          }

          done(null, user);
        });
      },
    );
  };

  passport.use(
    new LocalStrategy(
      {
        usernameField: 'email',
        passwordField: 'password',
      },
      authenticateUser,
    ),
  );

  passport.serializeUser((user, done) => done(null, user.id));

  passport.deserializeUser((id, done) => {
    pool.query(
      'SELECT * FROM users WHERE id = $1', [id], (err, results) => {
        if (err) {
          throw err;
        }

        return done(null, results.rows[0]);
      },
    );
  });
}

module.exports = initialize;
