<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Auth;
use App\Models\Question;
use App\Models\Answer;
use App\Models\Category;
use App\Http\Requests\Admin\QuestionRequest;

class QuestionController extends Controller
{
    public function __construct() {
        // $this->middleware('');
    }

    //Trả về trang danh sách question
    public function index()
    {
        //Lấy ra tất cả question
        $questions = Question::all();
        return view('admin.question.index', ['questions' => $questions]);
    }

    //Trả về trang tạo question
    public function create() {
        //Lấy tất cả chuyên mục
        $categories = Category::all();
        return view('admin.question.create', ['categories' => $categories]);
    }

    //Thực hiện thêm question mới
    public function store(QuestionRequest $request) {
        //Lấy ra tất cả câu trả lời
        $answers = $request->answers;
        //Lấy ra tất cả giá trị của ô đánh dấu đúng
        $corrects = $request->checkCorrects;
        $count = count($answers);
        //Vòng lặp kiểm tra ít nhất phải có 1 đáp án đúng
        $leastOneAnswerCorrect = false; 
        for($i=0; $i<$count; $i++) {
            if ($corrects[$i]) {
                $leastOneAnswerCorrect = true;
                break;
            }
        }
        //Nếu hợp lệ thì tiến hành lưu câu hỏi và các câu trả lời
        if ($leastOneAnswerCorrect) {
            //Tạo question mới
            $question = new Question;
            $question->content = nl2br($request->content);
            $question->note = $request->note;
            $question->id_category = $request->id_category;
            $question->id_user = Auth::user()->id;
            //Thực hiện lưu question vào CSDL
            $question->save();
            //Tạo các answer cho question này
            $idQuestion = $question->id;
            for($i=0; $i<$count; $i++) {
                $answer = new Answer;
                $answer->content = $answers[$i];
                $answer->correct = $corrects[$i];
                $answer->id_question = $idQuestion;
                //Thực hiện lưu answer vào CSDL
                $answer->save();
            }
            return redirect()->back()->with(['level_message' => 'success', 'content_message' => 'Thêm câu hỏi thành công']);
        } else {
            return redirect()->back()->withInput()->withErrors(['Phải có ít nhất 1 đáp án đúng cho câu hỏi này']);
        }
    }

    //Trả về trang sửa thông tin question
    public function edit($id) {
        //Lấy câu hỏi
        $question = Question::findOrFail($id);
        //Chỉ có người tạo ra câu hỏi này hoặc superadmin mới được sửa
        if ($question->id_user == Auth::user()->id || Auth::user()->isSuperAdmin()) {
            //Lấy tất cả chuyên mục
            $categories = Category::all();
            //Lấy tất cả đáp án của câu hỏi này
            $answers = $question->answers()->get();
            return view('admin.question.edit', ['categories' => $categories,'question' => $question, 'answers' => $answers]);
        } else {
            return redirect()->back()->with(['level_message' => 'error', 'content_message' => "Bạn không thể sửa câu hỏi do người khác tạo"]);
        }
    }

    //Tiến hành cập nhật question
    public function update(QuestionRequest $request, $id) {
       //Lấy ra tất cả câu trả lời
       $answers = $request->answers;
       //Lấy ra tất cả giá trị của ô đánh dấu đúng
       $corrects = $request->checkCorrects;
       $count = count($answers);
       //Vòng lặp kiểm tra ít nhất phải có 1 đáp án đúng
       $leastOneAnswerCorrect = false; 
       for($i=0; $i<$count; $i++) {
           if ($corrects[$i]) {
               $leastOneAnswerCorrect = true;
               break;
           }
       }
       //Nếu hợp lệ thì tiến hành lưu câu hỏi và các câu trả lời
       if ($leastOneAnswerCorrect) {
           //Lấy ra question hiện tại và sửa thông tin
           $question = Question::findOrFail($id);
           $question->content = nl2br($request->content);
           $question->note = $request->note;
           $question->id_category = $request->id_category;
           $question->id_user = Auth::user()->id;
           //Thực hiện cập nhật question vào CSDL
           $question->save();
           //Xóa tất cả answer cũ
           Answer::where('id_question','=',$id)->delete();
           //Tạo các answer cho question này
           $idQuestion = $question->id;
           for($i=0; $i<$count; $i++) {
               $answer = new Answer;
               $answer->content = $answers[$i];
               $answer->correct = $corrects[$i];
               $answer->id_question = $idQuestion;
               //Thực hiện lưu answer vào CSDL
               $answer->save();
            }
            return redirect()->route('admin.question.index')->with(['level_message' => 'success', 'content_message' => "Sửa thông tin câu hỏi thành công"]);
       } else {
           return redirect()->back()->withInput()->withErrors(['Phải có ít nhất 1 đáp án đúng cho câu hỏi này']);
       }
    }

    //Tiến hành xóa question
    public function destroy($id) {
        $question = Question::findOrFail($id);
         //Chỉ có người tạo ra câu hỏi này hoặc superadmin mới được xóa
         if ($question->id_user == Auth::user()->id || Auth::user()->isSuperAdmin()) {
            //Xóa question
            $question->delete();
            return redirect()->back()->with(['level_message' => 'success', 'content_message' => "Đã xóa câu hỏi"]);
         } else {
            return redirect()->back()->with(['level_message' => 'error', 'content_message' => "Bạn không thể xóa câu hỏi do người khác tạo"]);
         }        
    }
}
