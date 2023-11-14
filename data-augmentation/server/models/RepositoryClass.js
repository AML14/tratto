const mongoose = require('mongoose');

const RepositoryClassSchema = new mongoose.Schema({
    name: String,
    jDoctorConditions: [String]
});

exports.RepositoryClass = mongoose.model("RepositoryClass", RepositoryClassSchema);
exports.RepositoryClassSchema = RepositoryClassSchema;