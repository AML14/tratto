const dotenv = require('dotenv');
const express = require('express');
const app = express();
const mongoose = require('mongoose');
const cors = require('cors');
const db = require('./config/db');

// Load configuration file and environment variables
dotenv.config({ path: ".env" });

// Connect mongodb database
db.connect();

// Allow all origin
const allowedOrigins = [
    "http://localhost:5173",
    "http://localhost:5174",
    "https://tratto-jdc.onrender.com",
    "https://tratto-jdc-api.onrender.com",
    "https://star.inf.usi.ch"
];

const corsOptions = {
    origin: function (origin, callback) {
        if (allowedOrigins.includes(origin) || !origin) {
            // If the origin is in the allowedOrigins array or it's undefined (e.g., a same-origin request), allow the request
            callback(null, true);
        } else {
            // If the origin is not in the allowedOrigins array, reject the request
            callback(new Error('Not allowed by CORS'));
        }
    },
};

app.use(cors(corsOptions));

app.use(express.json({ limit: '100mb' }));

// Setup server rest api
app.use(express.json());
// Setup router
const repositoriesRouter = require('./routes/repositories').router;
app.use('/repositories', repositoriesRouter);
const exportRouter = require('./routes/export').router;
app.use('/export', exportRouter);

app.listen(3000);