const mongoose = require('mongoose');

const sinhvienSchema = mongoose.Schema({

    ten: {
        type: String,
        required: true
    },

    anh: {
        type: String,
        required: true
    },

    masv: {
        type: String,
        required: true
    },

    diem: {
        type: Number,
        required: true
    },
    trangthai: {
        type: Boolean
    },

},{versionKey: false});

const sinhvienModel = mongoose.model('sinhvien', sinhvienSchema);

module.exports = sinhvienModel;

