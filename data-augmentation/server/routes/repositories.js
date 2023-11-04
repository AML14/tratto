const express = require('express');
const router = express.Router();
const Repository = require('../models/Repository').Repository;
const RepositoryClass = require("../models/RepositoryClass").RepositoryClass;
const JDoctorCondition = require("../models/JDoctorCondition").JDoctorCondition;
const PreCondition = require("../models/PreCondition").PreCondition;
const PostCondition = require("../models/PostCondition").PostCondition;
const ThrowsCondition = require("../models/ThrowsCondition").ThrowsCondition;
const getRepository = require('./middlewares/repositories').getRepository;
const getRepositoryClass = require('./middlewares/repositoryclasses').getRepositoryClass;
const getJDoctorCondition = require('./middlewares/jdoctorconditions').getJDoctorCondition;
const getPreCondition = require('./middlewares/jdoctorconditions').getPreCondition;
const getPostCondition = require('./middlewares/jdoctorconditions').getPostCondition;
const getThrowsCondition = require('./middlewares/jdoctorconditions').getThrowsCondition;

// Get all repositories
router.get(
    '/',
    async (req, res) => {
        try {
            const repositories = await Repository.find();
            res.json(repositories);
        } catch (e) {
            res.status(500).json({ message: e.message });
        }
    }
);

// Get all repository classes
router.get(
    '/:idRepository/repositoryclasses',
    getRepository,
    async (req, res) => {
        console.log(res.repository)
        try {
            const repository = res.repository;
            const repositoryClasses = [];
            for (const idRepositoryClass of repository.classes) {
                const repositoryClass = await RepositoryClass.findById(idRepositoryClass);
                repositoryClasses.push(repositoryClass.toJSON());
            }
            res.json(repositoryClasses);
        } catch (e) {
            res.status(500).json({ message: e.message });
        }
    }
);

// Get all jdoctorcondition documents
router.get(
    '/:idRepository/repositoryclasses/:idRepositoryClass/jdoctorconditions',
    getRepositoryClass,
    async (req, res) => {
        try {
            const repositoryClass = res.repositoryClass;
            const jDoctorConditions = [];
            for (const idJDoctorCondition of repositoryClass.jDoctorConditions) {
                const jDoctorCondition = await JDoctorCondition.findById(idJDoctorCondition);
                jDoctorConditions.push(jDoctorCondition.toJSON());
            }
            res.json(jDoctorConditions);
        } catch (e) {
            res.status(500).json({ message: e.message });
        }
    }
);

// Get all pre-condition documents
router.get(
    '/:idRepository/repositoryclasses/:idRepositoryClass/jdoctorconditions/:idJDoctorCondition/pre',
    getJDoctorCondition,
    async (req, res) => {
        try {
            const jDoctorCondition = res.jDoctorCondition;
            const preConditions = [];
            for (const idPreCondition of jDoctorCondition.pre) {
                const preCondition = await PreCondition.findById(idPreCondition);
                preConditions.push(preCondition.toJSON());
            }
            res.json(preConditions);
        } catch (e) {
            res.status(500).json({ message: e.message });
        }
    }
);

// Get all post-condition documents
router.get(
    '/:idRepository/repositoryclasses/:idRepositoryClass/jdoctorconditions/:idJDoctorCondition/post',
    getJDoctorCondition,
    async (req, res) => {
        try {
            const jDoctorCondition = res.jDoctorCondition;
            const postConditions = [];
            for (const idPostCondition of jDoctorCondition.post) {
                const postCondition = await PostCondition.findById(idPostCondition);
                postConditions.push(postCondition.toJSON());
            }
            res.json(postConditions);
        } catch (e) {
            res.status(500).json({ message: e.message });
        }
    }
);

// Get all throws-condition documents
router.get(
    '/:idRepository/repositoryclasses/:idRepositoryClass/jdoctorconditions/:idJDoctorCondition/throws',
    getJDoctorCondition,
    async (req, res) => {
        try {
            const jDoctorCondition = res.jDoctorCondition;
            const throwsConditions = [];
            for (const idThrowsCondition of jDoctorCondition.throws) {
                const throwsCondition = await ThrowsCondition.findById(idThrowsCondition);
                throwsConditions.push(throwsCondition.toJSON());
            }
            res.json(throwsConditions);
        } catch (e) {
            res.status(500).json({ message: e.message });
        }
    }
);

// Get one repository
router.get(
    '/:idRepository',
    getRepository,
    (req, res) => {
        res.json(res.repository);
    }
);

// Create repository
router.post(
    '/',
    async (req, res) => {
        // Get the repository to create
        const repository = req.body.repository;
        console.log(repository);
        // Generate repository document
        const repositoryDocument = new Repository({
            projectName: repository.projectName,
            githubLink: repository.githubLink,
            commit: repository.commit,
            srcPathList: repository.srcPathList,
            rootPathList: repository.rootPathList,
            classes: []
        });
        try {
            // Save document to database
            await repositoryDocument.save();
            res.status(201).json(repositoryDocument);
        } catch (e) {
            res.status(400).json({ message: e.message });
        }
    }
);

// Delete repository
router.delete(
    '/:idRepository',
    getRepository,
    async (req, res) => {
        try {
            await res.repository.deleteOne();
            for(const idRepositoryClass of res.repository.classes) {
                const repositoryClass = await RepositoryClass.findById(idRepositoryClass);
                for(const idJDoctorCondition of repositoryClass.jDoctorConditions) {
                    const jDoctorCondition = await JDoctorCondition.findById(idJDoctorCondition);
                    for(const idPreCondition of jDoctorCondition.pre) {
                        await PreCondition.findByIdAndDelete(idPreCondition);
                    }
                    for(const idPostCondition of jDoctorCondition.post) {
                        await PostCondition.findByIdAndDelete(idPostCondition);
                    }
                    for(const idThrowsCondition of jDoctorCondition.throws) {
                        await ThrowsCondition.findByIdAndDelete(idThrowsCondition);
                    }
                    await jDoctorCondition.deleteOne();
                }
                await repositoryClass.deleteOne();
            }
            res.json({ message: "Deleted repository" });
        } catch (e) {
            res.status(500).json({ message: e.message });
        }
    }
)

// Get one repository class
router.get(
    '/:idRepository/repositoryclasses/:idRepositoryClass',
    getRepositoryClass,
    (req, res) => {
        res.json(res.repositoryClass);
    }
);

// Create repository class
router.post(
    '/:idRepository/repositoryclasses/',
    getRepository,
    async (req, res) => {
        // Get class
        const repositoryClass = req.body.repositoryClass;

        if(!repositoryClass || (repositoryClass && !(repositoryClass.name && repositoryClass.jDoctorConditions))) {
            res.status(400).json({ message: "Missing parameters." });
        }

        // Define jDoctorConditions list
        const jDoctorConditions = [];

        // Create jDoctorConditions
        for(const jDoctorCondition of repositoryClass.jDoctorConditions) {
            // Create jDoctorCondition document
            const jDoctorConditionDocument = new JDoctorCondition(jDoctorCondition);
            try {
                // Save document to database
                await jDoctorConditionDocument.save();
                jDoctorConditions.push(jDoctorConditionDocument._id);
            } catch (e) {
                res.status(400).json({ message: e.message });
            }
        }

        // Generate repository class document
        const repositoryClassDocument = new RepositoryClass({
            name: repositoryClass.name,
            jDoctorConditions: jDoctorConditions
        });
        try {
            // Save document to database
            repositoryClassDocument.save();
            // Get repository
            repository = res.repository;
            repository.classes.push(repositoryClassDocument._id);
            repository.save();
            res.status(201).json(repositoryClassDocument);
        } catch (e) {
            res.status(400).json({ message: e.message });
        }
    }
);

// Get JDoctorCondition document
router.get(
    '/:idRepository/repositoryclasses/:idRepositoryClass/jdoctorconditions/:idJDoctorCondition',
    getJDoctorCondition,
    (req, res) => {
        res.json(res.jDoctorCondition);
    }
);

// Add JDoctorCondition document to RepositoryClass
router.post(
    '/:idRepository/repositoryclasses/:idRepositoryClass/jdoctorconditions',
    getRepositoryClass,
    async (req, res) => {
        // Get the list of repositories to create
        const jDoctorCondition = req.body.jDoctorCondition;
        // Generate jDoctorCondition document
        const jDoctorConditionDocument = new JDoctorCondition(jDoctorCondition);
        try {
            // Save document to database
            jDoctorConditionDocument.save();
            // Get repository class
            const repositoryClass = res.repositoryClass;
            repositoryClass.jDoctorConditions.push(jDoctorConditionDocument._id);
            repositoryClass.save();
            res.status(201).json(jDoctorConditionDocument);
        } catch (e) {
            res.status(400).json({ message: e.message });
        }
    }
);

// Delete jdoctor condition
router.delete(
    '/:idRepository/repositoryclasses/:idRepositoryClass/jdoctorconditions/:idJDoctorCondition',
    getRepositoryClass,
    getJDoctorCondition,
    async (req, res) => {
        try {
            await res.jDoctorCondition.deleteOne();
            for(const idPreCondition of res.jDoctorCondition.pre) {
                await PreCondition.findByIdAndDelete(idPreCondition);
            }
            for(const idPostCondition of res.jDoctorCondition.post) {
                await PostCondition.findByIdAndDelete(idPostCondition);
            }
            for(const idThrowsCondition of res.jDoctorCondition.throws) {
                await ThrowsCondition.findByIdAndDelete(idThrowsCondition);
            }
            res.repositoryClass.jDoctorConditions = res.repositoryClass.jDoctorConditions.filter(j => j != req.params.idJDoctorCondition);
            res.repositoryClass.save();
            res.json({ message: "Deleted JDoctorCondition." });
        } catch (e) {
            console.log("entro qui purtroppo");
            res.status(500).json({ message: e.message });
        }
    }
)

// Create pre-condition
router.post(
    '/:idRepository/repositoryclasses/:idRepositoryClass/jdoctorconditions/:idJDoctorCondition/pre',
    getJDoctorCondition,
    (req, res) => {
        try {
            // Create pre-condition
            const preCondition = new PreCondition(req.body.condition);
            // Save pre-condition to database
            preCondition.save();
            // Add id to list of pre-conditions associated to the corresponding JDoctorCondition
            const jDoctorCondition = res.jDoctorCondition;
            jDoctorCondition.pre.push(preCondition._id);
            jDoctorCondition.save();
            res.status(201).json(preCondition);
        } catch (e) {
            res.status(400).json({ message: e.message });
        }
    }
);

// Get pre-condition
router.get(
    '/:idRepository/repositoryclasses/:idRepositoryClass/jdoctorconditions/:idJDoctorCondition/pre/:idPreCondition',
    getPreCondition,
    (req, res) => {
        res.json(res.preCondition);
    }
);

// Delete pre-condition
router.delete(
    '/:idRepository/repositoryclasses/:idRepositoryClass/jdoctorconditions/:idJDoctorCondition/pre/:idPreCondition',
    getJDoctorCondition,
    getPreCondition,
    async (req, res) => {
        try {
            await res.preCondition.deleteOne();
            res.jDoctorCondition.pre = res.jDoctorCondition.pre.filter(p => p != req.params.idPreCondition);
            res.jDoctorCondition.save();
            res.json({ message: "Deleted JDoctorCondition." });
        } catch (e) {
            res.status(500).json({ message: e.message });
        }
    }
)

// Delete repository class
router.delete(
    '/:idRepository/repositoryclasses/:idRepositoryClass',
    getRepository,
    getRepositoryClass,
    async (req, res) => {
        try {
            for(const idJDoctorCondition of res.repositoryClass.jDoctorConditions) {
                const jDoctorCondition = await JDoctorCondition.findById(idJDoctorCondition);
                for(const idPreCondition of jDoctorCondition.pre) {
                    await PreCondition.findByIdAndDelete(idPreCondition);
                }
                for(const idPostCondition of jDoctorCondition.post) {
                    await PostCondition.findByIdAndDelete(idPostCondition);
                }
                for(const idThrowsCondition of jDoctorCondition.throws) {
                    await ThrowsCondition.findByIdAndDelete(idThrowsCondition);
                }
                await jDoctorCondition.deleteOne();
            }
            res.repository.classes = res.repository.classes.filter(c => c != req.params.idRepositoryClass);
            res.repository.save();
            await res.repositoryClass.deleteOne();
            res.json({ message: "Deleted repository class." });
        } catch (e) {
            res.status(500).json({ message: e.message });
        }
    }
)

// Create post-condition
router.post(
    '/:idRepository/repositoryclasses/:idRepositoryClass/jdoctorconditions/:idJDoctorCondition/post',
    getJDoctorCondition,
    (req, res) => {
    try {
        // Create post-condition
        const postCondition = new PostCondition(req.body.condition);
        // Save post-condition to database
        postCondition.save();
        // Add id to list of post-conditions associated to the corresponding JDoctorCondition
        const jDoctorCondition = res.jDoctorCondition;
        jDoctorCondition.post.push(postCondition._id);
        jDoctorCondition.save();
        res.status(201).json(postCondition);
    } catch (e) {
        res.status(400).json({ message: e.message });
    }
});

// Get post-condition
router.get(
    '/:idRepository/repositoryclasses/:idRepositoryClass/jdoctorconditions/:idJDoctorCondition/post/:idPostCondition',
    getPostCondition,
    (req, res) => {
        res.json(res.postCondition);
    }
);

// Delete post-condition
router.delete(
    '/:idRepository/repositoryclasses/:idRepositoryClass/jdoctorconditions/:idJDoctorCondition/post/:idPostCondition',
    getJDoctorCondition,
    getPostCondition,
    async (req, res) => {
        try {
            //await res.postCondition.remove();
            res.jDoctorCondition.post = res.jDoctorCondition.post.filter(p => p != req.params.idPostCondition);
            res.jDoctorCondition.save();
            res.json({ message: "Deleted post-condition." });
        } catch (e) {
            res.status(500).json({ message: e.message });
        }
    }
)

// Create throws-condition
router.post(
    '/:idRepository/repositoryclasses/:idRepositoryClass/jdoctorconditions/:idJDoctorCondition/throws',
    getJDoctorCondition,
    (req, res) => {
        try {
            // Create throws-condition
            const throwsCondition = new ThrowsCondition(req.body.condition);
            // Save throws-condition to database
            throwsCondition.save();
            // Add id to list of throws-conditions associated to the corresponding JDoctorCondition
            const jDoctorCondition = res.jDoctorCondition;
            jDoctorCondition.throws.push(throwsCondition._id);
            jDoctorCondition.save();
            res.status(201).json(throwsCondition);
        } catch (e) {
            res.status(400).json({ message: e.message });
        }
    }
);

// Get throws-condition
router.get(
    '/:idRepository/repositoryclasses/:idRepositoryClass/jdoctorconditions/:idJDoctorCondition/throws/:idThrowsCondition',
    getThrowsCondition,
    (req, res) => {
        res.json(res.throwsCondition);
    }
);

// Delete throws-condition
router.delete(
    '/:idRepository/repositoryclasses/:idRepositoryClass/jdoctorconditions/:idJDoctorCondition/throws/:idThrowsCondition',
    getJDoctorCondition,
    getThrowsCondition,
    async (req, res) => {
        try {
            await res.throwsCondition.deleteOne();
            res.jDoctorCondition.throws = res.jDoctorCondition.throws.filter(t => t != req.params.idThrowsCondition);
            res.jDoctorCondition.save();
            res.json({ message: "Deleted throws-condition." });
        } catch (e) {
            res.status(500).json({ message: e.message });
        }
    }
)


exports.router = router;