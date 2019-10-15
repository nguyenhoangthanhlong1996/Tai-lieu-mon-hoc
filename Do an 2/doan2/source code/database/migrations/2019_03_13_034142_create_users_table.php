<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateUsersTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('users', function (Blueprint $table) {
            //Cấu hình lưu trữ và kiểu kí tự cho bảng
            $table->engine = 'InnoDB';
            $table->charset = 'utf8';
            $table->collation = 'utf8_unicode_ci';
            //Cấu hình các cột của bảng
            $table->increments('id');
            $table->string('username', 50)->unique();
            $table->string('password');
            $table->string('name', 50);
            $table->integer('id_permission')->unsigned();
            $table->timestamps();
            //Tạo khóa ngoại
            $table->foreign('id_permission')->references('id')->on('permissions')->onUpdate('cascade')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('users');
    }
}
