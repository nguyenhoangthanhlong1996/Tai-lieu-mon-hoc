<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * App\Models\Answer
 *
 * @property-read \App\Models\Question $question
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\Answer newModelQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\Answer newQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\Answer query()
 * @mixin \Eloquent
 * @property int $id
 * @property string $content
 * @property int $correct
 * @property int $id_question
 * @property \Illuminate\Support\Carbon|null $created_at
 * @property \Illuminate\Support\Carbon|null $updated_at
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\Answer whereContent($value)
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\Answer whereCorrect($value)
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\Answer whereCreatedAt($value)
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\Answer whereId($value)
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\Answer whereIdQuestion($value)
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\Answer whereUpdatedAt($value)
 */
class Answer extends Model
{
    /* Phần thiết lập tương ứng giữa table và model */

    //Chỉ định tên bảng
    protected $table = 'answers';

    //Chỉ định các cột lấy về
    protected $fillable = [
        'id', 'content', 'correct', 'id_question'
    ];

    /* Phần liên kết để lấy dữ liệu */

    //Thuộc về một Question
    public function question() {
        return $this->belongsTo('App\Models\Question', 'id_question', 'id');
    }
}
