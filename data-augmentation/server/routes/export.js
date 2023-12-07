const express = require('express');
const router = express.Router();
const JSZip = require('jszip');
const Repository = require('../models/Repository').Repository;
const RepositoryClass = require("../models/RepositoryClass").RepositoryClass;
const JDoctorCondition = require("../models/JDoctorCondition").JDoctorCondition;
const PreCondition = require("../models/PreCondition").PreCondition;
const PostCondition = require("../models/PostCondition").PostCondition;
const ThrowsCondition = require("../models/ThrowsCondition").ThrowsCondition;
router.get(
    '',
    async (req, res) => {
        try {
            const zip = new JSZip();
            const repositories = await Repository.find();
            const folderStructure = {
                "inputProjects.json": JSON.stringify(repositories.map(r => { return {
                    projectName: r.projectName,
                    rootPathList: r.rootPathList,
                    srcPathList: ["raw", ...r.srcPathList],
                    jarPathList: ["jar"],
                    jDoctorConditionsPathList: ["conditions"]
                } }), null, 4),
            };

            for (const repository of repositories) {
                for (const idRepositoryClass of repository.classes) {
                    const repositoryClass = await RepositoryClass.findById(idRepositoryClass);
                    const jDoctorConditions = [];

                    for (const idJDoctorCondition of repositoryClass.jDoctorConditions) {
                        const jDoctorCondition = await JDoctorCondition.findById(idJDoctorCondition);
                        const preConditions = [];
                        for (const idPreCondition of jDoctorCondition.pre) {
                            const preCondition = await PreCondition.findById(idPreCondition);
                            preConditions.push(preCondition);
                        }
                        const postConditions = [];
                        for (const idPostCondition of jDoctorCondition.post) {
                            const postCondition = await PostCondition.findById(idPostCondition);
                            postConditions.push(postCondition);
                        }
                        const throwsConditions = [];
                        for (const idThrowsCondition of jDoctorCondition.throws) {
                            const throwsCondition = await ThrowsCondition.findById(idThrowsCondition);
                            throwsConditions.push(throwsCondition);
                        }

                        jDoctorConditions.push({
                            source: jDoctorCondition.source,
                            operation: jDoctorCondition.operation,
                            identifiers: jDoctorCondition.identifiers,
                            pre: preConditions,
                            post: postConditions,
                            throws: throwsConditions
                        });
                    }
                    folderStructure[`repositories/${repository.projectName}/${repositoryClass.name}.json`] = JSON.stringify({
                        _id: repositoryClass._id,
                        name: repositoryClass.name,
                        jDoctorConditions
                    }, null, 4);
                }
            }

            for (const filePath in folderStructure) {
                zip.file(filePath, folderStructure[filePath]);
            }

            const zipStream = zip.generateNodeStream({ type: 'nodebuffer', streamFiles: true });

            res.setHeader('Content-Type', 'application/zip');
            res.setHeader('Content-Disposition', 'attachment; filename=db.zip');

            zipStream.pipe(res);
        } catch (e) {
            // Handle errors here
            console.error(e);
            res.status(500).json({ error: e.message });
        }
    }
);

exports.router = router;