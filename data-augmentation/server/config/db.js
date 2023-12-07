
const mongoose = require('mongoose');

const connect = async () => {
    try {
        const mongoURI = process.env.MONGODB_URI_CLOUD;
        const connection = await mongoose.connect(mongoURI, {
            useNewUrlParser: true
        })
        console.log(`Mongo Connected: ${connection.connection.host}`);
    } catch (e) {
        console.error(e);
        process.exit(1);
    }
}

exports.connect = connect;