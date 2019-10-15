<?php

namespace App\Http\Controllers\Front;
use App\Models\Category;
use App\Models\Question;
use App\Http\Controllers\Controller;
use Illuminate\Http\Request;

class FrontController extends Controller
{
    public function home() {
        return view('front.trangchu');
    }

    public function option() {
        $categories = Category::all();
        return view('front.thietlapthithu', ['categories'=>$categories]);
    }

    public function doExam(Request $request) {
        //Số lượng câu hỏi
        $totalQuestion = 20;
        //Thời gian làm bài (tính bằng giây - trung bình 1 câu 30 giây)
        $time = $totalQuestion * 30;
        //Lấy ra lựa chọn trình độ
        $optionLevel = $request->input('optionLevel');
        if ($optionLevel) {
            $questions = Question::where('id_category', $optionLevel)->get()->random($totalQuestion);
            return view('front.lambai', ['questions' => $questions, 'time' => $time]);
        }
        return redirect()->route('front.option');
    }

    public function result(Request $request) {
        //Lấy ra mảng các câu hỏi và đáp án đã chọn cho từng câu hỏi (question_id => answer_id)
        $chooseAnswers = $request->except('_token');
        //Lấy ra mảng question_id
        $arrQuestionId = array_keys($chooseAnswers);
        //Lấy ra danh sách các câu hỏi trên
        $questions = Question::whereIn('id', $arrQuestionId)->get();
        return view('front.ketqua', ['chooseAnswers' => $chooseAnswers, 'questions' => $questions]);
    }
}
