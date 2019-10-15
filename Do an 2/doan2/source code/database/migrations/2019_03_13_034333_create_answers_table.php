<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateAnswersTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('answers', function (Blueprint $table) {
            //Cấu hình lưu trữ và kiểu kí tự cho bảng
           $table->engine = 'InnoDB';
           $table->charset = 'utf8';
           $table->collation = 'utf8_unicode_ci';
           //Cấu hình các cột của bảng
           $table->increments('id');
           $table->string('content', 150);
           $table->boolean('correct');
           $table->integer('id_question')->unsigned();
           $table->timestamps();
           //Tạo khóa ngoại
           $table->foreign('id_question')->references('id')->on('questions')->onUpdate('cascade')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('answers');
    }
}
