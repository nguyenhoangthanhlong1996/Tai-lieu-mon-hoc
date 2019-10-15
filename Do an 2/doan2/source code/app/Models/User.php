<?php

namespace App\Models;

use Illuminate\Notifications\Notifiable;
use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Foundation\Auth\User as Authenticatable;

/**
 * App\Models\User
 *
 * @property-read \Illuminate\Notifications\DatabaseNotificationCollection|\Illuminate\Notifications\DatabaseNotification[] $notifications
 * @property-read \App\Models\Permission $permission
 * @property-read \Illuminate\Database\Eloquent\Collection|\App\Models\User[] $questions
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\User newModelQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\User newQuery()
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\User query()
 * @mixin \Eloquent
 * @property int $id
 * @property string $username
 * @property string $password
 * @property string $name
 * @property int $id_permission
 * @property \Illuminate\Support\Carbon|null $created_at
 * @property \Illuminate\Support\Carbon|null $updated_at
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\User whereCreatedAt($value)
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\User whereId($value)
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\User whereIdPermission($value)
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\User whereName($value)
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\User wherePassword($value)
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\User whereUpdatedAt($value)
 * @method static \Illuminate\Database\Eloquent\Builder|\App\Models\User whereUsername($value)
 */
class User extends Authenticatable
{
    use Notifiable;

    /* Phần thiết lập tương ứng giữa table và model */

    //Chỉ định tên bảng
    protected $table = 'users';

    //Chỉ định các cột sẽ lấy về
    protected $fillable = [
        'id', 'username', 'password', 'id_permission'
    ];

    //Chỉ định các cột không lấy về
    protected $hidden = [
        'password',
    ];

    /* Phần liên kết để lấy dữ liệu */

    //Thuộc về một Permission
    public function permission() {
        return $this->belongsTo('App\Models\Permission', 'id_permission', 'id');
    }

    //Có nhiều Question
    public function questions() {
        return $this->hasMany('App\Models\User', 'id_user', 'id');
    }

    /* Các hàm kiểm tra */

    //Hàm kiểm tra xem User hiện tại có phải là Super Amin hay không
    public function isSuperAdmin() {
        return ($this->permission()->first()['id'] == 1);
    }

    //Hàm kiểm tra xem User hiện tại có phải là Amin hay không
    public function isAdmin() {
        return ($this->permission()->first()['id'] == 2);
    }
}
