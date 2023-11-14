const mongoose = require('mongoose');
const GuardSchema = require('./JDoctorCondition').GuardSchema;

const PreConditionSchema = new mongoose.Schema({
    description: String,
    guard: GuardSchema
});

exports.PreCondition = mongoose.model("PreCondition", PreConditionSchema);
