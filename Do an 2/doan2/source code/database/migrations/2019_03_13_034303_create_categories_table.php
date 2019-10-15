<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateCategoriesTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('categories', function (Blueprint $table) {
             //Cấu hình lưu trữ và kiểu kí tự cho bảng
             $table->engine = 'InnoDB';
             $table->charset = 'utf8';
             $table->collation = 'utf8_unicode_ci';
             //Cấu hình các cột của bảng
             $table->increments('id');
             $table->string('name',100)->unique();
             $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('categories');
    }
}
