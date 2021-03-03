const bcrypt = require('bcryptjs');
const { pool } = require('./dbConfig');

async function createUser(req, res, next) {
  const {
    username,
    email,
    password,
  } = req.body;

  // Form validation has passed
  const hashedPassword = await bcrypt.hash(password, 10);

  try {
    const userQuery = await pool.query('SELECT * FROM users WHERE email = $1', [email]);
    if (userQuery.rows.length !== 0) {
      res.render('user/register', { message: 'Email already registered' });
    }

    const insertQuery = await pool.query(`INSERT INTO users (username, email, password)
                      VALUES ($1, $2, $3)
                      RETURNING id`, [username, email, hashedPassword]);

    console.log(insertQuery.rows);
    res.redirect('/');
  } catch (err) {
    next(err);
  }
}

module.exports = { createUser };
