require("dotenv").config();

const {Pool} = require("pg");

const isProduction = process.env.NODE_ENV === "production";
const constructionString = `postgresql://blake:password@localhost:5432/nodelogin`;

const pool = new Pool({
    connectionString: isProduction ? process.env.DATABASE_URL : constructionString
});

module.exports = { pool };
