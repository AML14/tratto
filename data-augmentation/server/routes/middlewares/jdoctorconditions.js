const PreCondition = require("../../models/PreCondition").PreCondition;
const PostCondition = require("../../models/PostCondition").PostCondition;
const ThrowsCondition = require("../../models/ThrowsCondition").ThrowsCondition;
const JDoctorCondition = require('../../models/JDoctorCondition').JDoctorCondition;
async function getJDoctorCondition(req, res, next) {
    let jDoctorCondition
    try {
        jDoctorCondition = await JDoctorCondition.findById(req.params.idJDoctorCondition);
        if (jDoctorCondition == null) {
            return res.status(404).json({ message: "Cannot find JDoctorCondition." });
        }
    } catch (e) {
        return res.status(500).json({ message: e.message });
    }
    res.jDoctorCondition = jDoctorCondition;
    next();
}

async function getPreCondition(req, res, next) {
    let preCondition
    try {
        preCondition = await PreCondition.findById(req.params.idPreCondition);
        if (preCondition == null) {
            return res.status(404).json({ message: "Cannot find PreCondition." });
        }
    } catch (e) {
        return res.status(500).json({ message: e.message });
    }
    res.preCondition = preCondition;
    next();
}

async function getPostCondition(req, res, next) {
    let postCondition
    try {
        postCondition = await PostCondition.findById(req.params.idPostCondition);
        if (postCondition == null) {
            return res.status(404).json({ message: "Cannot find PostCondition." });
        }
    } catch (e) {
        return res.status(500).json({ message: e.message });
    }
    res.postCondition = postCondition;
    next();
}

async function getThrowsCondition(req, res, next) {
    let throwsCondition
    try {
        throwsCondition = await ThrowsCondition.findById(req.params.idThrowsCondition);
        if (throwsCondition == null) {
            return res.status(404).json({ message: "Cannot find ThrowsCondition." });
        }
    } catch (e) {
        return res.status(500).json({ message: e.message });
    }
    res.throwsCondition = throwsCondition;
    next();
}

exports.getJDoctorCondition = getJDoctorCondition;
exports.getPreCondition = getPreCondition;
exports.getPostCondition = getPostCondition;
exports.getThrowsCondition = getThrowsCondition;
