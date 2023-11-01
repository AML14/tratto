
const mongoose = require('mongoose');

const connect = async () => {
    try {
        const connection = await mongoose.connect(process.env.MONGODB_URI, {
            useNewUrlParser: true
        })
        console.log(`Mongo Connected: ${connection.connection.host}`);
    } catch (e) {
        console.error(e);
        process.exit(1);
    }
}

exports.connect = connect;