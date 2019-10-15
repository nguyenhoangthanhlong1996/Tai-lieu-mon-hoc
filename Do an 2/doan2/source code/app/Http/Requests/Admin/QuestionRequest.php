<?php

namespace App\Http\Requests\Admin;

use Illuminate\Foundation\Http\FormRequest;
use Illuminate\Support\Facades\Route;
use Illuminate\Support\Facades\Auth;

class QuestionRequest extends FormRequest
{
   
    public function authorize()
    {
        return true;
    }

    public function rules()
    {
        $id = $this->route('question');
        return [
            'id_category' => 'required|exists:categories,id',
            'content' => "required|unique:questions,content,$id|max:250",
            'answers' => 'required',
            'answers.*' => 'required|distinct|max:150',
            'checkCorrects.*' => 'required',
        ];
    }

    public function messages()
    {
        $messages = [
            'id_category.required' => 'Phải chọn chuyên mục cho câu hỏi',
            'id_category.exist' => 'Chuyên mục không tồn tại',
            'content.required' => 'Nội dung câu hỏi không được bỏ trống',
            'content.unique' => 'Nội dung câu hỏi đã tồn tại',
            'content.max' => 'Nội dung câu hỏi chứa tối đa 250 kí tự',
            'answers.required' => 'Phải có đáp án cho câu hỏi này',
            'checkCorrects.*.required' => 'Phần đánh dấu đáp án đúng không được bỏ trống',
        ];
        $answers = $this->request->get('answers');
        
        if ($answers) {
            foreach($this->request->get('answers') as $i => $val) {
                $messages['answers.'.$i.'.required'] = 'Nội dung đáp án số '.($i+1).' không được bỏ trống';
                $messages['answers.'.$i.'.distinct'] = 'Nội dung đáp án số '.($i+1).' bị trùng lặp';
                $messages['answers.'.$i.'.max'] = 'Nội dung đáp án số '.($i+1).' chứa tối đa 150 kí tự';
            }
        }
        return $messages;
    }
}
