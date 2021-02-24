require("dotenv").config();

const {Pool} = require("pg");

const isProduction = process.end.NODE_ENV === "production";
const constructionString = `postgresql://${process.env.DB_USER}:${process.end.DB_PASSWORD}@${process.env.DB_HOST}:${process.end.DB_PORT}/${process.env.DB_DATABASE}`;
