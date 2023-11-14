const mongoose = require('mongoose');
const GuardSchema = require('./JDoctorCondition').GuardSchema;

const ThrowsConditionSchema = new mongoose.Schema({
    exception: String,
    description: String,
    guard: GuardSchema
});

exports.ThrowsCondition = mongoose.model("ThrowsCondition", ThrowsConditionSchema);