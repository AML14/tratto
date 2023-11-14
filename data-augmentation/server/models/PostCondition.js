const mongoose = require('mongoose');
const GuardSchema = require('./JDoctorCondition').GuardSchema;

const PropertySchema = new mongoose.Schema({
    condition: String,
    description: String
});

const PostConditionSchema = new mongoose.Schema({
    description: String,
    property: PropertySchema,
    guard: GuardSchema
});

exports.PostCondition = mongoose.model("PostCondition", PostConditionSchema);
