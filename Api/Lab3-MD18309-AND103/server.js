
const express = require('express');

const app = express();

const port = 3000;

const bodyParser = require("body-parser");
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));


app.listen(port, () => {
    console.log(`Server dang chay cong ${port}`)
})

const api = require('./api');
app.use ('/api', api);

const uri = 'mongodb+srv://truongasm:Xe43PCVOD1O1aOPC@cluster0.drtagx5.mongodb.net/md18309';

const svModel = require('./sinhvienModel');
const mongoose = require('mongoose');

app.get('/', async (req, res)=>{
    await mongoose.connect(uri);

    let sinhviens = await svModel.find();

    console.log(sinhviens);

    res.send(sinhviens);
})

app.post('/add_sv', async (req, res) => {
    await mongoose.connect(uri);

    // let sinhvien = {
    //     ten: 'sinhvien 4',
    //     gia: 500,
    //     soluong: 10,
    //     tonkho: false
    // }

    let sinhvien = req.body;

    let kq = await svModel.create(sinhvien);

    console.log(kq);

    let sinhviens = await svModel.find();

    res.send(sinhviens);
})

app.get('/xoa/:id', async(req, res) => {
    await mongoose.connect(uri);

    let id = req.params.id;
    let kq = await svModel.deleteOne({_id: id});

    console.log(kq);

    res.redirect('../')
})

app.get('/update/:id', async (req, res) => {

    await mongoose.connect(uri);

    console.log('Ket noi DB thanh cong');

    let id = req.params.id;

    let tensvMoi = 'San pham phien ban moi 2024';

    await svModel.updateOne({_id: id}, {ten: tensvMoi});

    let sinhviens = await svModel.find({});

    res.send(sinhviens);
}) 

// module.exports = {
//     uri: uri,
// }

exports.uri = uri;
exports.mongoose = mongoose;
exports.svModel = svModel;




