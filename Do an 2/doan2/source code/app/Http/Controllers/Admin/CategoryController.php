<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Models\Category;
use App\Http\Requests\Admin\CategoryRequest;

class CategoryController extends Controller
{
    public function __construct() {
        $this->middleware('category');
    }

    //Trả về trang danh sách category
    public function index()
    {
        //Lấy ra tất cả category
        $categories = Category::all();
        return view('admin.category.index', ['categories' => $categories]);
    }

    //Trả về trang tạo category
    public function create() {
        return view('admin.category.create');
    }

    //Thực hiện thêm category mới
    public function store(CategoryRequest $request) {
        //Tạo category mới
        $category = new Category;
        $category->name = $request->name;
        //Thực hiện lưu vào CSDL
        $category->save();
        return redirect()->back()->with(['level_message' => 'success', 'content_message' => 'Thêm chuyên mục thành công']);
    }

    //Trả về trang sửa thông tin category
    public function edit($id) {
        $category = Category::findOrFail($id);
        return view('admin.category.edit', ['category' => $category]);
    }

    //Tiến hành cập nhật category
    public function update(CategoryRequest $request, $id) {
        $category = Category::findOrFail($id);
        $category->name = $request->name;
        //Lưu cập nhật vào CSDL
        $category->save();
        return redirect()->route('admin.category.index')->with(['level_message' => 'success', 'content_message' => "Sửa thông tin chuyên mục thành công"]);
    }

    //Tiến hành xóa category
    public function destroy($id) {
        $category = Category::findOrFail($id);
        //Xóa category
        $category->delete();
        return redirect()->back()->with(['level_message' => 'success', 'content_message' => "Đã xóa chuyên mục '$category->name'"]);
        
    }
}
