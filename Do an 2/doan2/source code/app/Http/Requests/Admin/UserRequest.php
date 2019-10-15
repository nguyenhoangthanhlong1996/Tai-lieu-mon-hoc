<?php

namespace App\Http\Requests\Admin;

use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Support\Facades\Route;
use Illuminate\Support\Facades\Auth;

class UserRequest extends FormRequest
{
   
    public function authorize()
    {
        return true;
    }

    public function rules()
    {
        $idUser = $this->route()->parameter('account');
        $currUser = Auth::user();
        switch($this->method())
        {
            case 'GET':
            case 'DELETE': {
                return [];
            }
            case 'POST': {
                return [
                    'username' => 'required|unique:users,username|max:30|alpha_num',
                    'name' => 'required|max:30',
                    'id_permission' => 'required|exists:permissions,id',
                    'password' => 'required|max:30|alpha_num',
                    'retypePassword' => 'required_with:password|same:password'
                ];
            }
            case 'PUT':
            case 'PATCH': {
                //TH user đổi thông tin của chính mình
                if ($currUser->id == $idUser) {
                    if ($this->input('changePassword')) { //Có đổi mật khẩu
                        return [
                            'name' => 'required|max:30',
                            'password' => 'required|max:30|alpha_num',
                            'retypePassword' => 'required_with:password|same:password'
                        ];
                    } else {
                        return [
                            'name' => 'required|max:30'
                        ];
                    }
                } else { //TH SuperAdmin đổi thông tin của các user có quyền hạn thấp hơn
                    if ($this->input('changePassword')) { //Có đổi mật khẩu
                        return [
                            'name' => 'required|max:30',
                            'id_permission' => 'required|exists:permissions,id',
                            'password' => 'required|max:30|alpha_num',
                            'retypePassword' => 'required_with:password|same:password'
                        ];
                    } else {
                        return [
                            'name' => 'required|max:30',
                            'id_permission' => 'required|exists:permissions,id'
                        ];
                    }
                }
            }
            default: {
                return [];
            }
        }
    }

    public function messages()
    {
        return [
            'username.required' => 'Tên tài khoản không được bỏ trống',
            'username.unique' => 'Tên tài khoản này đã tồn tại',
            'username.max' => 'Tên tài khoản chứa tối đa 30 kí tự',
            'username.alpha_num' => 'Tên tài khoản chỉ có thể chữ cái và số',
            'name.required' => 'Tên người dùng không được bỏ trống',
            'name.max' => 'Tên người dùng chứa tối đa 30 kí tự',
            'id_permission.required' => 'Quyền hạn không được bỏ trống',
            'id_permission.exists' => 'Quyền hạn này không tồn tại trong hệ thống',
            'password.required' => 'Mật khẩu không được bỏ trống',
            'password.max' => 'Mật khẩu chứa tối đa 30 kí tự',
            'password.alpha_num' => 'Mật khẩu chỉ có thể chữ cái và số',
            'retypePassword.required_with' => 'Nhập lại mật khẩu không được bỏ trống',
            'retypePassword.same' => 'Nhập lại mật khẩu chưa đúng'
        ];
    }
}
