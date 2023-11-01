const Repository = require('../../models/Repository').Repository;
async function getRepository(req, res, next) {
    let repository
    try {
        repository = await Repository.findById(req.params.idRepository);
        if (repository == null) {
            return res.status(404).json({ message: "Cannot find repository" });
        }
    } catch (e) {
        return res.status(500).json({ message: e.message });
    }
    res.repository = repository;
    next();
}

exports.getRepository = getRepository;