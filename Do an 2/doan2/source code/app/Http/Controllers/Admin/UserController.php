<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Models\User;
use App\Models\Permission;
use App\Http\Requests\Admin\UserRequest;
use Illuminate\Support\Facades\Auth;

class UserController extends Controller
{
    public function __construct() {
        $this->middleware('user');
    }

    //Trả về trang danh sách tài khoản
    public function index()
    {
        //Lấy ra tất cả user
        $users = User::all();
        return view('admin.account.index', ['users' => $users]);
    }

    //Trả về trang tạo tài khoản
    public function create() {
        //Lấy ra tất cả permission ngoại trừ Super Admin
        $permissions = Permission::where('id','!=',1)->get();
        return view('admin.account.create', ['permissions' => $permissions]);
    }

    //Thực hiện thêm tài khoản mới
    public function store(UserRequest $request) {
        //Tạo user mới
        $user = new User;
        $user->username = $request->username;
        $user->password =  bcrypt($request->password);
        $user->name =  $request->name;
        $user->id_permission =  $request->id_permission;
        //Thực hiện lưu vào CSDL
        $user->save();
        return redirect()->back()->with(['level_message' => 'success', 'content_message' => 'Thêm tài khoản thành công']);
    }

    //Trả về trang sửa thông tin tài khoản
    public function edit($id) {
        $user = User::findOrFail($id);
        //Lấy ra tất cả permission ngoại trừ Super Admin
        $permissions = Permission::where('id','!=',1)->get();
        return view('admin.account.edit', ['permissions' => $permissions, 'user' => $user]);
    }

    //Tiến hành cập nhật user
    public function update(UserRequest $request, $id) {
        $currUser = Auth::user();
        $user = User::findOrFail($id);
        if ($currUser->id == $id) { //TH user đổi thông tin của chính mình
            if ($request->changePassword) {// Có đổi mật khẩu
                $user->password =  bcrypt($request->password);
                $user->name =  $request->name;
            } else {
                $user->name =  $request->name;
            }
        } else { //TH SuperAdmin đổi thông tin của các user có quyền hạn thấp hơn
            if ($request->changePassword) {// Có đổi mật khẩu
                $user->password =  bcrypt($request->password);
                $user->name =  $request->name;
                $user->id_permission =  $request->id_permission;
            } else {
                $user->name =  $request->name;
                $user->id_permission =  $request->id_permission;
            }
        }
        $user->save();
        return redirect()->route('admin.account.index')->with(['level_message' => 'success', 'content_message' => "Cập nhật thông tin tài khoản '$user->username' thành công"]);
    }

    //Tiến hành xóa user
    public function destroy($id) {
        $user = User::findOrFail($id);
        if ($user->isSuperAdmin()) {
            return redirect()->back()->with(['level_message' => 'error', 'content_message' => 'Không thể xóa tài khoản thuộc quyền Super Admin']);
        } else {
            $user->delete();
            return redirect()->back()->with(['level_message' => 'success', 'content_message' => "Đã xóa tài khoản '$user->username'"]);
        }
    }
}
