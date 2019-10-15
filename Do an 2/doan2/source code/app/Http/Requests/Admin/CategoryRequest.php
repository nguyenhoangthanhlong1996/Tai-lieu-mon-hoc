<?php

namespace App\Http\Requests\Admin;

use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Support\Facades\Route;
use Illuminate\Support\Facades\Auth;

class CategoryRequest extends FormRequest
{
   
    public function authorize()
    {
        return true;
    }

    public function rules()
    {
        return [
            'name' => 'required|max:100|unique:categories,name'
        ];
    }

    public function messages()
    {
        return [
            'name.required' => 'Tên chuyên mục không được bỏ trống',
            'name.max' => 'Tên chuyên mục tối đa 100 kí tự',
            'name.unique' => 'Tên chuyên mục này đã tồn tại'
        ];
    }
}
