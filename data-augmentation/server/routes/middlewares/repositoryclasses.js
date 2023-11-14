const RepositoryClass = require('../../models/RepositoryClass').RepositoryClass;
async function getRepositoryClass(req, res, next) {
    let repositoryClass
    try {
        repositoryClass = await RepositoryClass.findById(req.params.idRepositoryClass);
        if (repositoryClass == null) {
            return res.status(404).json({ message: "Cannot find repository class" });
        }
    } catch (e) {
        return res.status(500).json({ message: e.message });
    }
    res.repositoryClass = repositoryClass;
    next();
}

exports.getRepositoryClass = getRepositoryClass;