const express = require('express');

const router = express.Router();
module.exports = router;
const server = require('./server');


router.get('/', (req, res) => {
    res.send('URI:' + app.uri);
});

router.get('/list', async (req, res) => {
    await server.mongoose.connect(server.uri);

    let sinhviens = await server.svModel.find();

    console.log(sinhviens);

    res.send(sinhviens);
});
// Thêm sản phẩm
router.post("/add", async (req, res) => {
    try {
        const { ten, anh, masv, diem, trangthai } = req.body;

        // Tạo một instance mới của model sản phẩm
        const newsinhvien = new server.svModel({
            ten,
            anh,
            masv,
            diem,
            trangthai
        });

        // Lưu sản phẩm mới vào cơ sở dữ liệu
        const savedsinhvien = await newsinhvien.save();

        res.status(201).json(savedsinhvien); // Trả về sản phẩm vừa được tạo thành công
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

// Sửa sản phẩm
router.put("/update/:id", async (req, res) => {
    try {
        const { id } = req.params;
        const { ten, anh, masv, diem, trangthai  } = req.body;

        const updatedsinhvien = await server.svModel.findByIdAndUpdate(
            id,
            { ten, anh, masv, diem, trangthai  },
            { new: true }
        );

        if (!updatedsinhvien) {
            return res.status(404).json({ message: "Không tìm thấy sản phẩm" });
        }

        res.json(updatedsinhvien);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

// Xóa sản phẩm
router.delete("/delete/:id", async (req, res) => {
    try {
        const { id } = req.params;

        const deletedsinhvien = await server.svModel.findByIdAndDelete(id);

        if (!deletedsinhvien) {
            return res.status(404).json({ message: "Không tìm thấy sản phẩm" });
        }

        res.json({ message: "Xóa sản phẩm thành công" });
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});
module.exports = router;