<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * App\Models\Question
 *
 * @property-read \Illuminate\Database\Eloquent\Collection|\App\Models\Answer[] $answers
 * @property-read \App\Models\Category $category
 * @property-read \App\Models\User $user
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\Question newModelQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\Question newQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\Question query()
 * @mixin \Eloquent
 * @property int $id
 * @property string $content
 * @property string|null $note
 * @property int $id_category
 * @property int $id_user
 * @property \Illuminate\Support\Carbon|null $created_at
 * @property \Illuminate\Support\Carbon|null $updated_at
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\Question whereContent($value)
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\Question whereCreatedAt($value)
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\Question whereId($value)
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\Question whereIdCategory($value)
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\Question whereIdUser($value)
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\Question whereNote($value)
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\Question whereUpdatedAt($value)
 */
class Question extends Model
{
    /* Phần thiết lập tương ứng giữa table và model */

    //Chỉ định tên bảng
    protected $table = 'questions';

    //Chỉ định các cột lấy về
    protected $fillable = [
        'id', 'content', 'id_category', 'id_user'
    ];

    /* Phần liên kết để lấy dữ liệu */
    
    //Thuộc về một Category
    public function category() {
        return $this->belongsTo('App\Models\Category', 'id_category', 'id');
    }

    //Thuộc về một User
    public function user() {
        return $this->belongsTo('App\Models\User', 'id_user', 'id');
    }

    //Có nhiều Answer
    public function answers() {
        return $this->hasMany('App\Models\Answer', 'id_question', 'id');
    }

}
