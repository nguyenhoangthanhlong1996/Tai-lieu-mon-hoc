<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateQuestionsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('questions', function (Blueprint $table) {
           //Cấu hình lưu trữ và kiểu kí tự cho bảng
           $table->engine = 'InnoDB';
           $table->charset = 'utf8';
           $table->collation = 'utf8_unicode_ci';
           //Cấu hình các cột của bảng
           $table->increments('id');
           $table->string('content',250)->unique();
           $table->string('note', 100)->nullable();
           $table->integer('id_category')->unsigned();
           $table->integer('id_user')->unsigned();
           $table->timestamps();
           //Tạo khóa ngoại
           $table->foreign('id_category')->references('id')->on('categories')->onUpdate('cascade')->onDelete('cascade');
           $table->foreign('id_user')->references('id')->on('users')->onUpdate('cascade')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('questions');
    }
}
