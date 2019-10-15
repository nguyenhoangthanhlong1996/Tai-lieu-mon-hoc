<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Http\Requests\Admin\LoginRequest;
use Illuminate\Support\Facades\Auth;


class LoginController extends Controller
{
 
    public function index()
    {
        if (Auth::check()) {
            $user = Auth::user();
            if ($user->isSuperAdmin() || $user->isAdmin()) {
                return redirect()->route('admin.home.index');
            } else {
                return view('admin.login');
            }
        } else {
            return view('admin.login');
        }
    }

    public function store(LoginRequest $request)
    {
        $credentials = $request->only('username', 'password');

        if (Auth::attempt($credentials)) {
            $user = Auth::user();
            if ($user->isSuperAdmin() || $user->isAdmin()) {
                return redirect()->route('admin.home.index');
            } else {
                return redirect()->back()->withInput()->with(['level_message' => 'info', 'content_message' => 'Tên tài khoản hoặc mật khẩu không đúng']);
            }
        } else {
            return redirect()->back()->withInput()->with(['level_message' => 'info', 'content_message' => 'Tên tài khoản hoặc mật khẩu không đúng']);
        }
    }

    public function create() {
        Auth::logout();
        return redirect()->route('admin.login.index');
    }

}
