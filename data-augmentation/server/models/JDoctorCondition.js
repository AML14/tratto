const mongoose = require('mongoose');

const SourceSchema = new mongoose.Schema({
    methodJavadoc: String,
    methodSourceCode: String,
    classSourceCode: String
});

const OperationSchema = new mongoose.Schema({
    className: String,
    name: String,
    parameterTypes: [String]
});

const IdentifiersSchema = new mongoose.Schema({
    parameters: [String],
    receiverName: String,
    returnName: String
});

const GuardSchema = new mongoose.Schema({
    condition: String,
    description: String
});

const JDoctorConditionSchema = new mongoose.Schema({
    source: SourceSchema,
    operation: OperationSchema,
    identifiers: IdentifiersSchema,
    pre: [String],
    post: [String],
    throws: [String]
});

exports.JDoctorCondition = mongoose.model("JdoctorCondition", JDoctorConditionSchema);
exports.JDoctorConditionSchema = JDoctorConditionSchema;
exports.GuardSchema = GuardSchema;
