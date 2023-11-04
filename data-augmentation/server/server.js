const dotenv = require('dotenv');
const express = require('express');
const app = express();
const mongoose = require('mongoose');
const cors = require('cors');
const db = require('./config/db');

// Load configuration file and environment variables
dotenv.config({ path: "./config/config.env" });

// Connect mongodb database
db.connect();

// Allow all origin
app.use(cors());

app.use(express.json({ limit: '10mb' }));

// Setup server rest api
app.use(express.json());
// Setup router
const repositoriesRouter = require('./routes/repositories').router;
app.use('/repositories', repositoriesRouter);

app.listen(3000);