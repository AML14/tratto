const mongoose = require('mongoose');

const RepositorySchema = new mongoose.Schema({
    name: String,
    githubLink: String,
    classes: [String]
})

exports.Repository = mongoose.model('Repository', RepositorySchema);