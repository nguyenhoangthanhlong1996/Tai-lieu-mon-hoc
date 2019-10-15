<?php

namespace App\Http\Requests\Admin;

use Illuminate\Foundation\Http\FormRequest;

class LoginRequest extends FormRequest
{
   
    public function authorize()
    {
        return true;
    }

    
    public function rules()
    {
        return [
            'username' => 'required|max:50',
            'password' => 'required|max:50'
        ];
    }

    public function messages()
    {
        return [
            'username.required' => 'Tên tài khoản không được bỏ trống',
            'username.max' => 'Tên tài khoản tối đa 50 kí tự',
            'password.required' => 'Mật khẩu không được bỏ trống',
            'password.max' => 'Mật khẩu tối đa 50 kí tự'
        ];
    }
}
