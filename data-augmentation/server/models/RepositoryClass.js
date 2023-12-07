const mongoose = require('mongoose');

const RepositoryClassSchema = new mongoose.Schema({
    name: String,
    jDoctorConditions: [{
        _id: String,
        name: String
    }]
});

exports.RepositoryClass = mongoose.model("RepositoryClass", RepositoryClassSchema);
exports.RepositoryClassSchema = RepositoryClassSchema;